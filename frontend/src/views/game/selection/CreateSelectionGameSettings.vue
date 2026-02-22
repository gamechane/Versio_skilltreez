<template>
  <AppLayout :showBackButton="true">
    <div class="container">
      <BackButton class="terugButton"/>
      <CreateGameSettings title="Zwemmende dieren" text="Selectiespel" route="/games/selection-game/preview"/>
    </div>
  </AppLayout>
</template>

<script setup>
import CreateGameSettings from "@/components/shared/CreateGameSettings.vue";
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import BackButton from "@/components/shared/BackButton.vue";
</script>
