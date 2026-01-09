<script>
import { computed } from "vue";
import { store } from "../../store.js";

export default {
  props: {
    id: {
      type: String,
      required: true,
    },
    buttonText: {
      type: String,
      required: true,
    },
  },
  setup(props) {
    const isSelected = computed(() => store.selectedCard === props.id);

    const handleClick = () => {
      store.selectedCard = props.id;
    };

    return {
      isSelected,
      handleClick,
    };
  },
};
</script>

<template>
  <div :class="{ SelectieSpelCard: true, selected: isSelected }" @click="handleClick">
    <span class="text">{{ buttonText }}</span>
  </div>
</template>

<style scoped>
.SelectieSpelCard {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100px;
  height: 150px;
  border-radius: 5%;
  background-color: var(--primary-light);
  cursor: pointer;
  transition: background-color 0.3s ease;
}



.text {
  font-size: 14px;
}

.feedback {
  font-size: 14px;
  color: gray;
}

.SelectieSpelCard.selected {
  background-color: green; /* Verander de achtergrondkleur wanneer geselecteerd */
}
</style>
