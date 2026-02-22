<script setup>
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import BackButton from "@/components/shared/BackButton.vue";
import NextButton from "@/components/selectieSpel/NextButton.vue";
import voegToeKnop from "@/assets/voegToeKnop.svg";
import { ref } from "vue";
import binIcon from "@/assets/bin.svg";
import SpelCard from "@/components/selectieSpel/SpelCard.vue";

let questions = ref([
  {
    name: "Vervoersmiddelen",
    icons: [
      { buttonText: "Fiets" },
      { buttonText: "Auto" },
      { buttonText: "Vrachtwagen" },
      { buttonText: "brommer" },
    ],
  },
]); // array to hold questions and their cards

const addColumn = () => {
  let columnName = window.prompt("Wat is de naam van deze column?");
  if (columnName) {
    questions.value.push({ name: columnName, icons: [] });
  }
};

const openDialog = (questionIndex) => {
  let buttonText = window.prompt("Wat voor antwoord moet het zijn?");
  if (buttonText) {
    addCard(buttonText, questionIndex);
  }
};

const addCard = (buttonText, questionIndex) => {
  questions.value[questionIndex].icons.push({ buttonText: buttonText });
};

let selectedCard = ref(null); // variable to hold the selected card

const selectCard = (iconIndex, questionIndex) => {
  selectedCard.value = { iconIndex, questionIndex };
};

const removeCard = () => {
  if (selectedCard.value) {
    questions.value[selectedCard.value.questionIndex].icons.splice(
        selectedCard.value.iconIndex,
        1
    );
    selectedCard.value = null; // reset selected card
  }
};

const saveAndNavigate = () => {
  localStorage.setItem("questions", JSON.stringify(questions.value));
};
</script>

<template>
  <AppLayout :showBackButton="false">
    <div class="container">
      <img :src="binIcon" class="trash-bin" @click="removeCard" />
      <BackButton class="terugButton" />
      <NextButton
        class="nextButton"
        route="/games/create/combination-game/settings"
        @click="saveAndNavigate">
      </NextButton>

      <img
          :src="voegToeKnop"
          alt="voeg column toe"
          @click="addColumn"
          class="addButton"
      />

      <div class="icons">
        <div
            v-for="(question, questionIndex) in questions"
            :key="questionIndex"
            class="column"
        >
          <div class="column-header">{{ question.name }}</div>
          <div class="cards-container">
            <SpelCard
                v-for="(icon, iconIndex) in question.icons"
                :key="iconIndex"
                :button-text="icon.buttonText"
                :index="iconIndex"
                @click="() => selectCard(iconIndex, questionIndex)"
            />
          </div>
          <div class="add-item-container">
            <img
                :src="voegToeKnop"
                alt="voeg kaart toe"
                @click="() => openDialog(questionIndex)"
                class="addColumnButton"
            />
          </div>
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
  width: 96%;
  height: 98%;
  position: absolute;
}

.terugButton {
  position: sticky;
  top: 50%;
  left: 0px;
  align-self: flex-start;
}

.trash-bin {
  width: 50px;
  height: 50px;
  position: absolute;
  top: 20px;
  left: 20px;
  margin: 0;
  cursor: pointer;
}

.nextButton {
  position: sticky;
  top: 50%;
  right: 0px;
  align-self: flex-end;
}

.icons {
  display: flex;
  flex-wrap: nowrap;
  overflow-x: auto;
  gap: 20px;
  max-width: 100%;
  margin-top: 20px;
}

.column {
  flex: 0 0 auto;
  min-width: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  height: 300px; /* Fixed height for the column */
  overflow: hidden; /* Hide overflow */
  position: relative;
}

.column-header {
  font-weight: bold;
  margin-bottom: 10px;
}

.cards-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  overflow-y: auto; /* Enable vertical scrolling */
  flex-grow: 1; /* Allow the container to grow and take available space */
  padding-bottom: 40px; /* Make space for the Add Item button */
}

.add-item-container {
  position: absolute;
  bottom: 10px; /* Position the button 10px from the bottom */
  width: 100%;
  display: flex;
  justify-content: center;
}

.addColumnButton {
  margin-top: auto;
}
</style>
