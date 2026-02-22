<template>
  <div class="dashboardCard" @click="handleClick">
    <img :src="iconSrc" alt="Icon" class="icon" />
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
  width: 40px;
  height: 40px;
  margin-bottom: 10px;
}

</style>

<script>
import iconSrc from "@/assets/voegToeKnop.svg";

export default {
  data() {
    return {
      isHovered: false,
      iconSrc: iconSrc
    };
  },
  methods: {
    handleClick() {
      console.log("Button clicked!");
    },
  },
};
</script>