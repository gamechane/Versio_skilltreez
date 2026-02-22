<script setup>
 import trophyImg from "@/assets/status/trophy.svg";
 import router from "@/router/index.js";

 const props = defineProps({
   skillTreeInfo: {
     type: Object,
     default(rawProps) {
       return {
         // earnedPoints: rawProps.earnedPoints,
         // maxPoints: rawProps.maxPoints,
         // message: rawProps.message,
         // achievement: rawProps.achievement,

         // Dummy data
         earnedPoints: 150,
         maxPoints: 150,
         message: "Goedzo! Maak nu de oefentoets van hoofdstuk 6.",
         achievement: "Een goed begin",
       }
     },
   }
 });

 function proceed(event) {
   router.push('/skilltree');
 }
</script>

<template>
  <div class="container">
    <h1>Skill tree uitgespeeld!</h1>
    <img id="trophyImg" :src="trophyImg" alt="Trofee">
    <div class="points bold">{{ props.skillTreeInfo.earnedPoints }} / {{ props.skillTreeInfo.maxPoints }} punten</div>
    <div class="bold">{{ props.skillTreeInfo.message }}</div>
    <div v-if="props.skillTreeInfo.achievement" class="bold">Beloning behaald: '{{ props.skillTreeInfo.achievement }}'</div>
    <button v-on:click="proceed" class="proceedButton">Verder</button>
  </div>
</template>

<style scoped>
  .container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 1.5em;
    height: 100%;
  }

  #trophyImg {
    height: 25em;
  }

  .points {
    font-size: larger;
  }

  .bold {
    font-weight: bold;
  }

  .proceedButton {
    background-color: var(--primary);
    border: none;
    color: white;
    font: var(--offwhite);
    padding: 0.5rem 4rem;
    border-radius: 2px;
    cursor: pointer;
  }
</style>
