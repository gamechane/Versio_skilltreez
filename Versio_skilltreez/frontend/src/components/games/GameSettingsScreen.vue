<script setup>
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import SubmitButton from "@/components/shared/SubmitButton.vue";
import BackButton from "@/components/shared/BackButton.vue";
import { useRouter } from "vue-router";
import { createGameStore } from "@/views/game/createGameStore.js";
import { defineProps } from "vue";
const router = useRouter();

defineProps({
  onSave: {
    type: Function,
    required: true,
  },
  onPreview: {
    type: Function,
    required: true,
  },
});
</script>

<template>
  <AppLayout>
    <div class="container">
      <div class="icons">
        <BackButton class="terugButton" />
        <div class="game-info">
          <h1>{{ createGameStore.title }}</h1>
          <p>{{ createGameStore.selectedGame }}</p>
        </div>

        <div class="settings-container">
          <textarea
            class="text"
            v-model="createGameStore.description"
            placeholder="omschrijving"
          ></textarea>
          <slot></slot>
        </div>
        <div class="button-container">
          <submit-button inner="opslaan" @click="onSave"></submit-button>
          <submit-button inner="preview" @click="onPreview"></submit-button>
        </div>
      </div>
    </div>
  </AppLayout>
</template>

<style scoped>
.container {
  justify-content: center;
  background-color: var(--secondary-light);
  width: 98%;
  height: 98%;
  position: relative;
  display: flex;
  padding: 10px;
}

.icons {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 60vw;
}
.icons h1,
.icons p {
  margin: 0;
}

.icons h1 {
  margin-top: 30px;
}

.text {
  width: 100%;
  height: 100px;
  resize: none;
}

.button-container {
  display: flex;
  justify-content: space-between;
  gap: 20px;
}

.terugButton {
  position: sticky;
  top: 50%;
  left: 0px; /* Zet het helemaal naar links */
  align-self: flex-start; /* Verplaats de prullenbakknop naar het begin van de container */
}

.game-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.settings-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  width: 80%;
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>
