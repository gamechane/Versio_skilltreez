<!--Dit is het component voor het scherm waar de docent de instellingen van een spel kan regelen-->
<template>
  <AppLayout>
    <CreateGameSettings title="titel" text="" route="/games/classify-game/preview"></CreateGameSettings>
  </AppLayout>
</template>

<script>


import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import CreateGameSettings from "@/components/shared/CreateGameSettings.vue";
import CreateCombinationGameSettings from "@/components/shared/CreateGameSettings.vue";

export default {
  name: "RubriekSpelAanmakenInstellingen",
  components: {CreateCombinationGameSettings, CreateGameSettings, AppLayout}
}
</script>

<style scoped>

</style>
