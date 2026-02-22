<script setup>
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import InputField from "@/components/games/InputField.vue";
import { ref } from "vue";
import addIcon from "@/assets/addIcon.svg";
import binIcon from "@/assets/bin.svg";
import moreInfoIcon from "@/assets/moreinfo.png";
import nextImg from "@/assets/arrow.svg";
import router from "@/router/index.js";
import { answerGameStore } from "./answerGameStore.js";

const addQuestion = () => {
  const id = new Date().getTime();

  const newQuestion = {
    id,
    question: "",
    answers: [],
    correctness: 5,
  };

  answerGameStore.questions = {
    ...answerGameStore.questions,
    [id]: newQuestion,
  };

  addAnswer(id);
};

const updateQuestion = (id, value) => {
  answerGameStore.questions = {
    ...answerGameStore.questions,
    [id]: {
      ...answerGameStore.questions[id],
      question: value,
    },
  };
};

const removeQuestion = (id) => {
  if (Object.keys(answerGameStore.questions).length === 1) {
    return alert("Je moet minimaal 1 vraag hebben");
  }
  const { [id]: _, ...rest } = answerGameStore.questions;
  answerGameStore.questions = rest;
};

const addAnswer = (questionId) => {
  const id = new Date().getTime();

  const newAnswer = {
    id,
    answer: "",
  };

  answerGameStore.questions = {
    ...answerGameStore.questions,
    [questionId]: {
      ...answerGameStore.questions[questionId],
      answers: [...answerGameStore.questions[questionId].answers, newAnswer],
    },
  };
};

const updateAnswer = (questionId, answerId, value) => {
  answerGameStore.questions = {
    ...answerGameStore.questions,
    [questionId]: {
      ...answerGameStore.questions[questionId],
      answers: answerGameStore.questions[questionId].answers.map((answer) =>
        answer.id === answerId ? { ...answer, answer: value } : answer
      ),
    },
  };
};

const removeAnswer = (questionId, answerId) => {
  if (answerGameStore.questions[questionId].answers.length === 1) {
    return alert("Je moet minimaal 1 antwoord hebben");
  }
  answerGameStore.questions = {
    ...answerGameStore.questions,
    [questionId]: {
      ...answerGameStore.questions[questionId],
      answers: answerGameStore.questions[questionId].answers.filter(
        (answer) => answer.id !== answerId
      ),
    },
  };
};

const updateCorrectness = (questionId, value) => {
  if (value < 0 || value > 100) {
    answerGameStore.questions = {
      ...answerGameStore.questions,
      [questionId]: {
        ...answerGameStore.questions[questionId],
        correctness: 50,
      },
    };
    return alert("Nauwkeurigheid moet tussen 0 en 100 liggen");
  }

  answerGameStore.questions = {
    ...answerGameStore.questions,
    [questionId]: {
      ...answerGameStore.questions[questionId],
      correctness: value,
    },
  };
};

const validate = () => {
  for (const question of Object.values(answerGameStore.questions)) {
    if (question.question.trim() === "") {
      return alert("Vul alle vragen in");
    }

    for (const answer of question.answers) {
      if (answer.answer.trim() === "") {
        return alert("Vul alle antwoorden in");
      }
    }
  }

  router.push("/games/create/answer-game/settings");
};

if (Object.keys(answerGameStore.questions).length === 0) {
  addQuestion();
}
</script>

<template>
  <AppLayout>
    <div class="container">
      <div class="gameContainer">
        <div
          class="questionContainer"
          v-for="question in answerGameStore.questions"
          :key="question.id"
        >
          <div class="inputContainer">
            <InputField
              :value="question.question"
              :onChange="(value) => updateQuestion(question.id, value)"
              placeholder="Vraag"
            />
            <button
              class="removeButton"
              @click="() => removeQuestion(question.id)"
            >
              <img :src="addIcon" class="removeIcon" alt="addIcon" />
            </button>
          </div>

          <div class="answerContainer">
            <div
              class="inputContainer"
              v-for="answer in question.answers"
              :key="answer.id"
            >
              <InputField
                class="inputField"
                :value="answer.answer"
                :onChange="
                  (value) => updateAnswer(question.id, answer.id, value)
                "
                placeholder="Antwoord"
              />
              <button
                class="removeButton"
                @click="() => removeAnswer(question.id, answer.id)"
              >
                <img :src="addIcon" class="removeIcon" alt="removeIcon" />
              </button>
            </div>

            <button
              class="addButton addAnswerButton"
              @click="() => addAnswer(question.id)"
            >
              <img :src="addIcon" class="addIcon" alt="addIcon" />
            </button>
          </div>

          <div class="correctnessContainer">
            <label for="correctness"> Nauwkeurigheid </label>
            <input
              name="correctness"
              class="correctnessInput"
              type="number"
              @input="
                (event) => updateCorrectness(question.id, event.target.value)
              "
              min="0"
              max="100"
            />
          </div>
        </div>

        <button class="addButton" @click="addQuestion">
          <img :src="addIcon" class="addIcon" alt="addIcon" />
        </button>
      </div>

      <button class="trashButton" @click="console.log(questions)">
        <img :src="binIcon" class="trashIcon" alt="trashIcon" />
      </button>

      <button class="moreInfo" @click="console.log(questions)">
        <img :src="moreInfoIcon" class="moreInfoIcon" alt="moreInfoIcon" />
      </button>

      <button class="nextButton" @click="validate">
        <img :src="nextImg" class="nextIcon" alt="nextIcon" />
      </button>
    </div>
  </AppLayout>
</template>

<style scoped>
.container {
  background-color: var(--secondary-light);
  width: 100%;
  min-height: 100%;
  position: relative;

  display: flex;
  align-items: center;
  flex-direction: column;
  padding-top: 40px;
  padding-bottom: 40px;

  gap: 100px;
}

.gameContainer {
  position: relative;
  width: max(40vw, 200px);
  display: flex;
  align-items: center;
  flex-direction: column;
  padding-top: 40px;

  gap: 100px;
}

.questionContainer {
  display: flex;
  flex-direction: column;
  gap: 20px;
  position: relative;
}

.answerContainer {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.inputContainer {
  position: relative;
}

.removeButton {
  border: none;
  width: 36px;
  height: 36px;
  padding: 0;
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: transparent;
  transform: rotate(45deg);
  cursor: pointer;

  position: absolute;
  top: -18px;
  right: -18px;
}

.moreInfo {
  position: absolute;
  top: 10px;
  right: 10px;
  background-color: transparent;
  border: none;
  padding: 10px;
  border-radius: 8px;
  cursor: pointer;
}

.moreInfoIcon {
  width: 32px;
  height: 32px;
}

.removeButton:hover {
  filter: brightness(0.8);
}

.removeIcon {
  width: 28px;
  height: 28px;
}

.addButton {
  border: none;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  width: 100%;
  padding: 6px 10px;
  background-color: white;
  box-sizing: content-box;
}

.addButton:hover {
  filter: brightness(0.9);
}

.addAnswerButton {
  padding: 6px 0px;
}

.addIcon {
  width: 28px;
  height: 28px;
}

.trashIcon {
  width: 45px;
  height: 45px;
}

.correctnessContainer {
  position: absolute;
  top: -10px;
  right: -140px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 8px;
}

.trashButton {
  padding: 0;
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;

  max-width: fit-content;

  background-color: white;
  padding: 28px 18px;
  border-radius: 8px;
  cursor: pointer;
  position: absolute;
  top: 10px;
  left: 10px;
}

.correctnessInput {
  padding: 8px 10px;
  border: 1px solid black;
  border-radius: 0;
  border-radius: 4px;
  width: 70%;
  font-weight: bold;
  font-size: 1.5rem;
}

.nextButton {
  position: absolute;
  top: 50%;
  right: 10px;
  background-color: transparent;
  border: none;
  padding: 10px;
  border-radius: 8px;
  cursor: pointer;
  transform: rotate(180deg);
}
</style>
