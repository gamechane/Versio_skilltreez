<script setup>
import { ref, onMounted } from 'vue';
import AanmaakSelectieSpelVraag from "@/components/selectieSpel/AanmaakSelectieSpelVraag.vue";
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import AanmaakSelectieSpelCard from "@/components/selectieSpel/AanmaakSelectieSpelCard.vue";
import AanmaakSelectieSpelVoegCardToe from "@/components/selectieSpel/AanmaakSelectieSpelVoegCardToe.vue";
import BackButton from "@/components/shared/BackButton.vue";
import NextButton from "@/components/selectieSpel/NextButton.vue";

let questionText = ref("");
let questions = ref([]);

onMounted(() => {
  // Fetch the questions from local storage when the component is mounted
  const storedQuestions = localStorage.getItem('questions');
  if (storedQuestions) {
    questions.value = JSON.parse(storedQuestions);
  }
});

let selectedCards = ref([]); // variable to hold the selected cards

const selectCard = (iconIndex, questionIndex) => {
  const cardIndex = selectedCards.value.findIndex(card => card.iconIndex === iconIndex && card.questionIndex === questionIndex);
  if (cardIndex !== -1) {
    selectedCards.value.splice(cardIndex, 1); // deselect the card if it's already selected
  } else {
    selectedCards.value.push({ iconIndex, questionIndex }); // select the card
  }
};

const isCardSelected = (iconIndex, questionIndex) => {
  return selectedCards.value.some(card => card.iconIndex === iconIndex && card.questionIndex === questionIndex);
};

const removeCards = () => {
  for (const selectedCard of selectedCards.value) {
    questions.value[selectedCard.questionIndex].icons.splice(selectedCard.iconIndex, 1);
  }
  selectedCards.value = []; // reset selected cards
};
const addCard = (buttonText, questionIndex) => {
  questions.value[questionIndex].icons.push({ buttonText: buttonText });
};

const addQuestion = () => {
  questions.value.push({ questionText: questionText.value, icons: [] }); // add a new question with an empty set of cards
  questionText.value = ""; // reset the question text
};

const openDialog = (questionIndex) => {
  let buttonText = window.prompt("Wat voor antwoord moet het zijn?");
  if (buttonText) {
    addCard(buttonText, questionIndex);
  }
};

const saveAndNavigate = () => {
  localStorage.setItem('questions', JSON.stringify(questions.value));
  questionText.value = ""; // reset the question text
};
</script>

<template>
  <AppLayout :showBackButton="true">
    <div class="container">
      <button class="add-question-button" @click="addQuestion">Voeg een vraag toe</button>
      <img src="../../../assets/prullenbak.png" class="trash-bin" @click="removeCards" />
      <BackButton class="terugButton"/>
      <NextButton class="nextButton" route="/games/create/selection-game/settings" @click="saveAndNavigate" />
      <div v-for="(question, questionIndex) in questions" :key="questionIndex">
        <AanmaakSelectieSpelVraag class="aanmaak-selectie-spel-vraag" v-model="question.questionText" />
        <div class="icons">
          <AanmaakSelectieSpelCard v-for="(icon, iconIndex) in question.icons" :key="iconIndex" :button-text="icon.buttonText" :index="iconIndex" :class="{ 'selected-card': isCardSelected(iconIndex, questionIndex) }" @click="() => selectCard(iconIndex, questionIndex)" />
          <AanmaakSelectieSpelVoegCardToe class="vraag" @click="() => openDialog(questionIndex)"/>
        </div>
      </div>
    </div>
  </AppLayout>
</template>

<style scoped>



.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: var(--secondary-light);
  width: 100%;
  position: absolute;
}

.add-question-button {
  margin-top: 10px;
  align-self: flex-start;
  margin-left: 20px;
  padding: 10px 20px;
  background-color: #42d742;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.add-question-button:hover {
  background-color: #216c21;
}

.trash-bin {
  width: 50px;
  height: 50px;
  position: sticky;
  top: 60px;
  align-self: flex-start;
  margin-left: 90px;
  cursor: pointer;
}
.aanmaak-selectie-spel-vraag {
  margin-top: 60px;
  margin-bottom: 60px;
  margin-left: 95px;
}

.icons {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
  overflow: auto;
  max-height: 90vh;
}

.vraag {
  margin-bottom: 100px;
}

.terugButton {
  position: sticky;
  top: 50%;
  left: 0px;
  align-self: flex-start;
}

.nextButton {
  position: sticky;
  top: 50%;
  right: 0px;
  align-self: flex-end;
}

.selected-card {
  background-color: #216c21; /* change this to the color you want for the selected cards */
}
</style>
