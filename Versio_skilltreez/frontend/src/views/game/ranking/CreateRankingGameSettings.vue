<script setup>
import MistakeMarginSetting from "@/components/games/MistakeMarginSetting.vue";
import TimeLimitSetting from "@/components/games/TimeLimitSetting.vue";
import EndMessageSetting from "@/components/games/EndMessageSetting.vue";
import GameSettingsScreen from "@/components/games/GameSettingsScreen.vue";
import ShowAmountOfCardSetting from "@/components/games/ShowAmountOfCardSetting.vue";
import router from "@/router/index.js";

const onSave = () => {
  console.log("Saved");
};

const onPreview = () => {
  router.push("/games/ranking-game/preview");
};
</script>

<template>
  <GameSettingsScreen :onSave="onSave" :onPreview="onPreview">
    <MistakeMarginSetting />
    <TimeLimitSetting />
    <ShowAmountOfCardSetting />
    <EndMessageSetting />
  </GameSettingsScreen>
</template>

<style scoped></style>
