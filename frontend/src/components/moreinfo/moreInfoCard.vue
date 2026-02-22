<template>
  <div class="moreInfoCard" @click="handleClick">
    <img :src="iconSrc" alt="Icon" class="icon" />
    <span class="text">{{ buttonText }}</span>
  </div>
</template>

<script>
export default {
  props: {
    iconSrc: {
      type: String,
      required: true,
    }
  },
  data() {
    return {
      isHovered: false,
    };
  },
  methods: {
    handleClick() {
      console.log("Button clicked!");
    },
  },
};
</script>

<style scoped>
.moreInfoCard {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
  border-radius: 10%;
  background-color: var(--primary-light);
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.moreInfoCard:hover {
  background-color: gray;
}

.icon {
  width: 40px;
  height: 40px;
  margin: 1px;
}

.text {
  font-size: 14px;
  font-weight: bold;
}

.feedback {
  font-size: 14px;
  color: gray;
}
</style>