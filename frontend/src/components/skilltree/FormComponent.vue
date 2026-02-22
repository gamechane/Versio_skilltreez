<template>
  <div class="form-container">
    <h2>Tegelinformatie</h2>
    <div>
      <label for="title">Tegeltitel</label>
      <input v-model="title" id="title" type="text" />
    </div>
    <div>
      <label for="game">Spel</label>
      <select v-model="game" id="game">
        <option>Dieren, voedsel en voertuigen</option>
        <option>Voedsel</option>
        <option>Voertuigen</option>
      </select>
    </div>
    <div>
      <label for="points">Punten</label>
      <input v-model="points" id="points" type="number" />
    </div>
    <a @click.prevent="toggleAdvancedOptions" href="#">
      {{ showAdvancedOptions ? 'Geavanceerde opties verbergen' : 'Geavanceerde opties weergeven' }}
    </a>
    <div v-if="showAdvancedOptions" class="advanced-options">
      <h3>Voorwaarden</h3>
      <div>
        <label for="requiredPoints">Punten vereist</label>
        <input v-model="requiredPoints" id="requiredPoints" type="number" />
      </div>
      <div>
        <label for="mandatoryTasks">Verplichte taken</label>
        <select v-model="mandatoryTasks" id="mandatoryTasks">
          <option>Vogels</option>
        </select>
      </div>
      <div>
        <label for="unlockables">Ontgrendelingen</label>
        <select v-model="unlockables" id="unlockables">
          <option>Vogels</option>
        </select>
      </div>
    </div>
    <button @click="save">Opslaan</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const props = defineProps(['block']);
const emit = defineEmits(['saveBlock']);

const title = ref(props.block.title || '');
const game = ref(props.block.game || '');
const points = ref(props.block.points || 0);
const requiredPoints = ref(props.block.requiredPoints || 0);
const mandatoryTasks = ref(props.block.mandatoryTasks || '');
const unlockables = ref(props.block.unlockables || '');

const showAdvancedOptions = ref(false);

const toggleAdvancedOptions = () => {
  showAdvancedOptions.value = !showAdvancedOptions.value;
};

const save = () => {
  emit('saveBlock', {
    title: title.value,
    game: game.value,
    points: points.value,
    requiredPoints: requiredPoints.value,
    mandatoryTasks: mandatoryTasks.value,
    unlockables: unlockables.value
  });
};
</script>

<style scoped>
.form-container {
  background-color: #d8f5d2;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
  display: block;
  margin: 10px 0 5px;
}

input,
select {
  width: 100%;
  padding: 5px;
  margin-bottom: 10px;
}

button {
  background-color: #6ab47b;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}

button:hover {
  background-color: #5a9a67;
}

a {
  display: block;
  color: #007bff;
  cursor: pointer;
  text-decoration: underline;
  margin-top: 10px;
}

a:hover {
  color: #0056b3;
}

.advanced-options {
  margin-top: 20px;
}

h3 {
  margin-bottom: 10px;
}
</style>
