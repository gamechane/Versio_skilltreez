<script setup>
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import SubmitButton from "@/components/shared/SubmitButton.vue";

import BackButton from "@/components/shared/BackButton.vue";

const props = defineProps({
  title: {
    type: String,
    required: true
  },
  text: {
    type: String,
    required: true
  },
  route: {
    type: String,
    required: true
  }
});

import { useRouter } from 'vue-router';

const router = useRouter();

const navigateToSelectieSpelView = () => {
  router.push(props.route);
};

const previewClick = () => {
  const event = new CustomEvent('preview-click', { bubbles: true });
  document.dispatchEvent(event);
};

import { onMounted, onUnmounted } from 'vue';

onMounted(() => {
  document.addEventListener('preview-click', navigateToSelectieSpelView);
});

onUnmounted(() => {
  document.removeEventListener('preview-click', navigateToSelectieSpelView);
});
</script>

<template>
  <AppLayout>
    <div class="container">
      <div class="icons">
        <BackButton class="terugButton"/>
        <h1>{{ title }}</h1>
        <p>{{ text }}</p>
        <textarea class="text" placeholder="omschrijving"></textarea>
        <div class="button-container">
          <submit-button inner="opslaan"></submit-button>
          <submit-button inner="preview" @click="previewClick"></submit-button>        </div>
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

.icons{
  display: flex;

  flex-direction: column;
  align-items: center;
  width: 60vw;
  height: 80vh;

}
.icons h1, .icons p {
  margin: 0;
}

.icons h1{
  margin-top: 30px;
}

.text{
  width: 25vw;
  height: 10vh;
  margin-top: 30px;
}

.button-container {
  display: flex;
  justify-content: space-between;
  position: absolute;
  bottom: 30px;
  width: 25vw;
}

.terugButton{
  position: sticky;
  top: 50%;
  left: 0; /* Zet het helemaal naar links */
  align-self: flex-start; /* Verplaats de prullenbakknop naar het begin van de container */
}
</style>
