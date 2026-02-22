<script setup>
import { ref, onMounted } from 'vue';
import { jsPlumb } from 'jsplumb';
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import moreInfoCard from "@/components/moreinfo/moreInfoCard.vue";
import moreInfo from "@/assets/moreinfo.png";
import FormComponent from '@/components/skilltree/FormComponent.vue';

const blocks = ref(Array.from({ length: 25 }, () => ({
  title: '',
  game: '',
  points: 0,
  requiredPoints: 0,
  mandatoryTasks: '',
  unlockables: ''
})));

const currentBlock = ref(null);
let instance = null;

const openForm = (index) => {
  currentBlock.value = {...blocks.value[index], index};
};

const saveBlock = (data) => {
  if (currentBlock.value !== null) {
    const {index} = currentBlock.value;
    blocks.value[index] = {...data};
    currentBlock.value = null; // Close form
  }
};

const clearBlock = (index) => {
  blocks.value[index] = {
    title: '',
    game: '',
    points: 0,
    requiredPoints: 0,
    mandatoryTasks: '',
    unlockables: ''
  };
};

const initializeJsPlumb = () => {
  instance = jsPlumb.getInstance({
    Connector: 'Straight',
    PaintStyle: {stroke: '#4caf50', strokeWidth: 4},
    Endpoint: 'Dot',
    EndpointStyle: {fill: '#4caf50', radius: 3},
    Anchors: ['BottomCenter', 'TopCenter'],
    Overlays: [
      ["Arrow", {width: 30, length: 20, location: 1}]
    ]
  });

  instance.batch(() => {
    blocks.value.forEach((_, index) => {
      instance.makeSource(`block-${index}`, {
        filter: '.circle',
        anchor: 'Continuous',
        connectorStyle: {stroke: '#4caf50', strokeWidth: 4},
        extract: {
          action: 'the-action'
        }
      });

      instance.makeTarget(`block-${index}`, {
        dropOptions: {hoverClass: 'dragHover'},
        anchor: 'Continuous',
        allowLoopback: false
      });
    });

    instance.bind('connection', (info) => {
      info.connection.bind('dblclick', () => {
        instance.deleteConnection(info.connection);
      });
    });
  });
};

onMounted(() => {
  initializeJsPlumb();
});
</script>

<template>
  <AppLayout>
    <div class="container">
      <div class="sidebar">
        <FormComponent v-if="currentBlock !== null" :block="currentBlock" @saveBlock="saveBlock"/>
      </div>
      <div class="content">
        <div class="info-icon">
          <moreInfoCard :icon-src="moreInfo" button-text="Meer informatie"/>
        </div>
        <div class="grid">
          <div v-for="(block, index) in blocks" :key="index" :id="'block-' + index" class="block"
               @dblclick="openForm(index)">
            <div v-if="block.title === ''">
              <button class="plus-button" @click="openForm(index)">+</button>
            </div>
            <div v-else class="block-content">
              <button class="close-button" @click="clearBlock(index)">×</button>
              <div class="title">{{ block.title }}</div>
              <div class="game">{{ block.game }}</div>
              <div class="points">{{ block.points }}</div>
            </div>
            <div class="circle" :style="{ top: '-5px', left: '50%', transform: 'translateX(-50%)' }"></div>
            <div class="circle" :style="{ right: '-5px', top: '50%', transform: 'translateY(-50%)' }"></div>
            <div class="circle" :style="{ bottom: '-5px', left: '50%', transform: 'translateX(-50%)' }"></div>
            <div class="circle" :style="{ left: '-5px', top: '50%', transform: 'translateY(-50%)' }"></div>
          </div>
        </div>
      </div>
    </div>
  </AppLayout>
</template>

<style scoped>
.container {
  display: flex;
  width: 100%;
  height: 100%;
  background-color: #d8f5d2;
}

.sidebar {
  width: 300px; /* Adjusted width for the form */
  background-color: #d8f5d2;
  padding: 20px;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}

.content {
  flex: 1;
  padding: 20px;
  position: relative; /* Ensure positioning for info icon */
}

.info-icon {
  position: absolute;
  top: 10px;
  right: 10px;
}

.grid {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  grid-gap: 60px;
  width: 100%;
  position: relative; /* Ensure grid is relative for absolute positioning */
}

.block {
  background-color: #a6e3a1;
  width: 100px;
  height: 100px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #2d9c4e;
  box-sizing: border-box;
  flex-direction: column;
  position: relative; /* Ensure block is relative for positioning circles */
}

.plus-button {
  background-color: white;
  border: none;
  color: #6ab47b;
  font-size: 24px;
  font-weight: bold;
  cursor: pointer;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.close-button {
  position: absolute;
  top: 5px;
  right: 5px;
  background: white;
  border: none;
  font-size: 20px;
  cursor: pointer;
  color: black;
  border-radius: 50%;
}

.block-content {
  text-align: center;
}

.block-content .game {
  font-size: 13px;
  font-weight: bold;
  color: #000;
}

.block-content .requiredPoints,
.block-content .mandatoryTasks,
.block-content .unlockables {
  font-size: 12px;
  font-weight: bold;
  color: #333;
}

.block-content .title,
.block-content .points {
  font-size: 18px;
  font-weight: bold;
  color: #000;
}

.circle {
  width: 10px;
  height: 10px;
  background-color: #2d9c4e;
  border-radius: 50%;
  position: absolute;
}
</style>
