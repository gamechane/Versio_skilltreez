<script setup>
import imageUrl from "@/assets/skilltreez_logo_dark.svg";
import router from "@/router/index.js";
import {AuthenticationService} from "@/services/authentication.js";

function goToHome() {
  let link = "/"

  if (AuthenticationService.authenticated()) {
        link = "/dashboard";
  }

  router.push(link);
}
</script>

<template>
  <header>
    <img :src="imageUrl" alt="Skilltreez Logo" @click="goToHome()"/>
  </header>
</template>

<style scoped>
header {
  background-color: var(--primary);
  width: 100%;
  display: flex;
  justify-content: start;
  align-items: center;
}

img {
  height: 20px;
  margin: 10px;
  object-fit: contain;
}
</style>