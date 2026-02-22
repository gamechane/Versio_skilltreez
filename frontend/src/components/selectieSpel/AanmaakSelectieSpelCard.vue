<template>
  <div class="dashboardCard" @click="handleClick">
    <img :src="iconSrc" alt="Icon" class="icon" />
    <span class="text">{{ buttonText }}</span>
  </div>
</template>

<style scoped>
.dashboardCard {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100px;
  height: 150px;
  border-radius: 10%;
  background-color: var(--primary-light);
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.dashboardCard:hover {
  background-color: lightblue;
}

.icon {
  width: 25px;
  height: 25px;
  margin-bottom: 10px;
}

.text {
  background-color: white;
  font-size: 14px;
  font-weight: bold;
}

</style>
<script>
import iconSrc from "@/assets/afbeeldingBergSelectieSpelAanmaak.png";

export default {
  props: {
    buttonText: {
      type: String,
      required: true,
    },
    index: {
      type: Number,
      required: true,
    }
  },
  data() {
    return {
      isHovered: false,
      iconSrc: iconSrc,
      clickCount: 0
    };
  },
  methods: {
    handleClick() {
      this.clickCount++;
      if (this.clickCount >= 2) {
        this.$emit('remove-card', this.index);
        this.clickCount = 0; // reset click count
      }
    },
  },
};
</script>