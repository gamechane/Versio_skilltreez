<template>
  <div class="game-select">
    <h2 class="text-sm title">{{ title }}</h2>
    <button
      class="game-card"
      @mouseover="isHovered = true"
      @mouseleave="isHovered = false"
      @click="() => click(!value)"
      :class="{ selected: value }"
    >
      <div class="image-container">
        <img v-if="isHovered" :src="gifUrl" alt="GIF" />
        <img v-else :src="imageUrl" alt="image" />
      </div>
    </button>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      required: true,
    },
    imageUrl: {
      type: String,
      required: true,
    },
    gifUrl: {
      type: String,
      required: true,
    },
    value: {
      type: Boolean,
      required: true,
    },
    click: {
      type: Function,
      required: true,
    },
  },
  data() {
    return {
      isHovered: false,
      isSelected: false,
    };
  },
};
</script>

<style scoped>
.title {
  color: var(--foreground);
  font-size: 14px;
  font-weight: 500;
}

.game-select {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2px;
}
.game-card {
  width: 120px;
  border: 4px solid var(--primary-light);
  transition: border-color 0.3s ease;
  cursor: pointer;
  background-color: transparent;
  padding: 0px;
}

.game-card.selected {
  border-color: var(--primary-dark);
  border-width: 4px;
}

.image-container {
  width: 100%;
  height: 100px;
  overflow: hidden;
}

img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: opacity 0.3s ease;
}
</style>
