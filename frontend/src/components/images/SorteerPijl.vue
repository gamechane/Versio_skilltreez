<template>
  <div class="icon">
    <img :src="icon" alt="sorteer icon" />
  </div>
</template>

<script setup>
import icon from "@/assets/Sorteerpijl.png";
</script>

<style scoped>
.icon {
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.icon > img {
  height: 100%;
  width: 100%;
}
</style>
