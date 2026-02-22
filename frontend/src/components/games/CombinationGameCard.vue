<template>
  <div class="card" @click="click" :class="{ selected: selected }">
    <div class="card-header">{{ title }}</div>
    <div class="card-body"></div>
  </div>
</template>

<script>
export default {
  props: {
    set: {
      type: String,
      required: true,
    },
    title: {
      type: String,
      required: true,
    },
    selected: {
      type: Boolean,
    },
    click: {
      type: Function,
      required: true,
    },
  },
};
</script>

<style scoped>
.card {
  transition: transform 0.4s;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: var(--primary-light);
  border-radius: 5px;
  width: 150px;
  height: 200px;
  cursor: pointer;
}
.card.selected {
  background-color: var(--primary);
}

.card:hover {
  transform: scale(1.1);
  transition: transform 0.4s;
}
</style>
