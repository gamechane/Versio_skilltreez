<script setup>
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import VervolgSelectieSpel from "@/components/selectieSpel/VervolgSelectieSpel.vue";
import CombinatiespelDocentInstelingen from "@/views/CombinatiespelDocentInstelingen.vue";
import {createGameStore} from "@/views/games/createGameStore.js";

</script>

<!--Dit is het component voor het scherm waar de docent de instellingen van een spel kan regelen-->
<template>
    <AppLayout>
       <combinatiespel-docent-instelingen :title="createGameStore.title" :text="createGameStore.description"></combinatiespel-docent-instelingen>
    </AppLayout>
</template>



<style scoped>

</style>