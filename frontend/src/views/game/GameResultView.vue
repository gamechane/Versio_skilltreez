<script setup>
import TopNav from "@/components/shared/AppLayout/TopNav.vue";
import {computed} from "vue";
import brokenHeartImg from "@/assets/status/broken-heart.svg";
import heartImg from "@/assets/status/heart.svg";
import timeImg from "@/assets/status/time.svg";
import trophyImg from "@/assets/status/trophy.svg";
import router from "@/router/index.js";

const props = defineProps({
  gameInfo: {
    type: Object,
    default(rawProps) {
      return {
        // points: rawProps.points,
        // lapsedTime: rawProps.lapsedTime,
        // maxTime: rawProps.maxTime,
        // heartsLeft: rawProps.heartsLeft,
        // maxHearts: rawProps.maxHearts,
        // successMessage: rawProps.successMessage,
        // failureMessage: rawProps.failureMessage,

        // Temporary success case for developing purposes
        points: 10,
        lapsedTime: "09:30",
        maxTime: "10:00",
        heartsLeft: 3,
        maxHearts: 5,
        successMessage: "Goed gedaan!",
        failureMessage: "Helaas!",
      }
    },
  },
  skillTreeId: String,
})

const success = computed(() => {
  return props.gameInfo.heartsLeft > 0 && props.gameInfo.lapsedTime <= props.gameInfo.maxTime;
})

function proceed() {
  router.push(`/skilltree/${props.skillTreeId}`);
}
</script>

<template>
  <TopNav />
  <div class="container">
    <div class="points" v-if="success">+{{ props.gameInfo.points }} punten</div>
    <div class="stats-container">
      <div class="stat-container">
        <img class="stat-img" :src="timeImg" alt="Klok">
        <span class="stat">{{ props.gameInfo.lapsedTime }} / {{ props.gameInfo.maxTime }}</span>
      </div>
      <img v-if="success" class="resultImg" :src="trophyImg" alt="Gebroken hart">
      <img v-else class="resultImg" :src="brokenHeartImg" alt="Trofee">
      <div class="stat-container">
        <img class="stat-img" :src="heartImg" alt="Hart">
        <span class="stat">{{ props.gameInfo.heartsLeft }} / {{ props.gameInfo.maxHearts }}</span>
      </div>
    </div>
    <div class="message" v-if="success">{{ props.gameInfo.successMessage }}</div>
    <div class="message" v-else>{{ props.gameInfo.failureMessage }}</div>
    <button @click="proceed" class="proceedButton">Verder</button>
  </div>


</template>

<style scoped>
  .container {
    display: flex;
    flex-direction: column;
    gap: 2em;
    align-items: center;
    height: calc(100% - 40px - 4em);
    position: relative;
    top: 4em;
  }

  .points {
    font-size: 1.5em;
  }

  .stats-container {
    display: flex;
    justify-content: space-evenly;
    width: 100%;
  }

  .stat-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 1.5em;
    width: 10em;
  }

  .stat-img {
    height: 4em;
  }

  .stat {
    font-weight: bold;
  }

  .resultImg {
    width: 20em;
  }

  .message {
    font-weight: bold;
    font-size: 2.5em;
  }

  .proceedButton {
    background-color: var(--primary);
    border: none;
    color: white;
    font: var(--offwhite);
    padding: 0.75rem 5.5rem;
    border-radius: 2px;
    cursor: pointer;
  }
</style>


