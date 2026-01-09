<script setup>
import { createGameStore } from "@/views/game/createGameStore.js";
import MistakeMarginSetting from "@/components/games/MistakeMarginSetting.vue";
import TimeLimitSetting from "@/components/games/TimeLimitSetting.vue";
import EndMessageSetting from "@/components/games/EndMessageSetting.vue";
import GameSettingsScreen from "@/components/games/GameSettingsScreen.vue";
import router from "@/router/index.js";

const onSave = () => {
  console.log("Saved");
};

const onPreview = () => {
  router.push("/games/answer-game/preview");
};
</script>

<template>
  <GameSettingsScreen :onSave="onSave" :onPreview="onPreview">
    <MistakeMarginSetting />
    <TimeLimitSetting />
    <EndMessageSetting />
  </GameSettingsScreen>
</template>

<style scoped></style>
