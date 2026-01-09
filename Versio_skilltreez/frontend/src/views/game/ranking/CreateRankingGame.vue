<script setup>
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import { ref } from "vue";
import addIcon from "@/assets/addIcon.svg";
import binIcon from "@/assets/bin.svg";
import moreInfoIcon from "@/assets/moreinfo.png";
import nextImg from "@/assets/arrow.svg";
import addImageIcon from "@/assets/add-image.svg";
import sorteerPijlIcon from "@/assets/sorteer-pijl.svg";
import { rankingStore } from "./rankingStore.js";
import router from "@/router/index.js";

const selectedCard = ref(null);

const addCard = () => {
  rankingStore.cards.push({
    id: new Date().getTime(),
    index: rankingStore.cards.length,
    iconSrc: null,
    text: "",
  });
};

const moveCardIndex = (index, direction) => {
  const newIndex = index + direction;
  if (newIndex < 0 || newIndex >= rankingStore.cards.length) return;

  const card = rankingStore.cards[index];
  card.index = newIndex;
  rankingStore.cards.splice(index, 1);
  rankingStore.cards.splice(newIndex, 0, card);
};

const handleImageUpload = (event, card) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      card.iconSrc = e.target.result;
    };
    reader.readAsDataURL(file);
  }
};

const selectCard = (card) => {
  if (selectedCard.value === card) {
    selectedCard.value = null;
    return;
  }
  selectedCard.value = card;
};

const deleteCard = () => {
  if (!selectedCard.value) return;

  const isCardInExtraCards = rankingStore.extraCards.includes(
    selectedCard.value
  );
  if (isCardInExtraCards) {
    const card = selectedCard.value;
    const index = rankingStore.extraCards.indexOf(card);
    rankingStore.extraCards.splice(index, 1);
    return;
  }

  if (rankingStore.cards.length === 1)
    return alert("Je moet minimaal 1 kaart hebben");
  const card = selectedCard.value;
  const index = rankingStore.cards.indexOf(card);
  rankingStore.cards.splice(index, 1);
};

//Extra rankingStore.cards
const addExtraCard = () => {
  rankingStore.extraCards.push({
    id: new Date().getTime(),
    index: null,
    iconSrc: null,
    text: "",
    extra: true,
  });
};

const handleExtraImageUpload = (event, card) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      card.iconSrc = e.target.result;
    };
    reader.readAsDataURL(file);
  }
};

const validateCards = () => {
  var cards = rankingStore.cards;
  var extraCards = rankingStore.extraCards;

  for (var i = 0; i < cards.length; i++) {
    if (cards[i].text === "") {
      return alert("Vul alle kaarten in");
    }
  }

  for (var i = 0; i < extraCards.length; i++) {
    if (extraCards[i].text === "") {
      return alert("Vul alle extra kaarten in");
    }
  }

  return true;
};

const nextPage = () => {
  if (!validateCards()) return;
  console.log({
    cards: rankingStore.cards,
    extraCards: rankingStore.extraCards,
  });
  router.push("/games/create/ranking-game/settings");
};

if (!rankingStore.cards.length) {
  addCard();
}
</script>

<template>
  <AppLayout>
    <div class="container">
      <div class="gameContainer">
        <div class="card-container">
          <div
            class="card"
            v-for="(card, index) in rankingStore.cards"
            :key="card.id"
            :class="{ selected: card === selectedCard }"
            @click="selectCard(card)"
          >
            <div class="card-top">
              <span class="card-id">{{ index + 1 }}</span>

              <label for="file-input" class="add-image-icon">
                <img
                  :src="addImageIcon"
                  class="addImageIcon"
                  alt="addImageIcon"
                />
              </label>
              <input
                type="file"
                id="file-input"
                name="file-input"
                :ref="`fileInput${card.id}`"
                class="file-input"
                @change="(event) => handleImageUpload(event, card)"
              />
            </div>

            <div class="card-center">
              <input
                class="text-input"
                v-model="card.text"
                placeholder="Voer text in"
              />
            </div>

            <div class="card-footer">
              <button
                class="sort-button"
                :disabled="index == 0"
                @click="
                  (e) => {
                    e.stopPropagation();
                    moveCardIndex(index, -1);
                  }
                "
              >
                <img
                  :src="sorteerPijlIcon"
                  class="sorteerPijlIcon"
                  alt="sorteerPijlIcon"
                />
              </button>
              <button
                class="sort-button reverse"
                :disabled="index == rankingStore.cards.length - 1"
                @click="
                  (e) => {
                    e.stopPropagation();
                    moveCardIndex(index, 1);
                  }
                "
              >
                <img
                  :src="sorteerPijlIcon"
                  class="sorteerPijlIcon"
                  alt="sorteerPijlIcon"
                />
              </button>
            </div>
          </div>
          <button class="add-card" @click="addCard">
            <img :src="addIcon" class="addIcon" alt="addIcon" />
          </button>
        </div>
        <div class="card-container">
          <div
            class="card"
            v-for="(card, index) in rankingStore.extraCards"
            :key="card.id"
            :class="{ selected: card === selectedCard }"
            @click="selectCard(card)"
          >
            <div class="card-top">
              <span class="card-id">Extra</span>

              <label for="file-input" class="add-image-icon">
                <img
                  :src="addImageIcon"
                  class="addImageIcon"
                  alt="addImageIcon"
                />
              </label>
              <input
                type="file"
                id="file-input"
                name="file-input"
                :ref="`fileInput${card.id}`"
                class="file-input"
                @change="(event) => handleExtraImageUpload(event, card)"
              />
            </div>

            <div class="card-center">
              <input
                class="text-input"
                v-model="card.text"
                placeholder="Voer text in"
              />
            </div>

            <div class="card-footer"></div>
          </div>
          <button class="add-card" @click="addExtraCard">
            <img :src="addIcon" class="addIcon" alt="addIcon" />
          </button>
        </div>
      </div>

      <button class="trashButton" @click="deleteCard">
        <img :src="binIcon" class="trashIcon" alt="trashIcon" />
      </button>

      <button class="moreInfo" @click="console.log(questions)">
        <img :src="moreInfoIcon" class="moreInfoIcon" alt="moreInfoIcon" />
      </button>

      <button class="nextButton" @click="nextPage">
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
  width: max(60vw, 200px);
  display: flex;
  align-items: center;
  flex-direction: column;
  padding-top: 40px;

  gap: 60px;
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

.addIcon {
  width: 28px;
  height: 28px;
}

.trashIcon {
  width: 45px;
  height: 45px;
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

.card-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  flex-wrap: wrap;
  width: 100%;
  gap: 20px;
}

.card {
  width: 148px;
  height: 206px;
  border-radius: 8px;
  background-color: white;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.card-top {
  background-color: var(--secondary);
  padding: 8px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-center {
  padding: 8px;
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.card-footer {
  background-color: var(--secondary);
  display: flex;
  justify-content: space-between;
  padding: 8px;
  height: 30px;
}

.file-input {
  display: none;
}

.addImageIcon {
  width: 32px;
  height: 32px;
  cursor: pointer;
}

.addImageIcon:hover {
  opacity: 0.8;
}

.card-id {
  font-weight: bold;
  font-size: 1.6rem;
}

.text-input {
  width: 100%;
  height: 100%;
  text-align: center;
  border: none;
  font-size: 1.3rem;
}

.sort-button {
  padding: 8px;
  border-radius: 5px;
  background-color: white;
  justify-content: center;
  align-items: center;
  display: flex;
}

.sort-button:disabled {
  opacity: 0;
  cursor: default;
}

.sort-button:hover {
  filter: brightness(0.8);
}

.sort-button.reverse {
  transform: rotate(180deg);
}

.add-card {
  width: 148px;
  height: 206px;
  border-radius: 8px;
  background-color: var(--secondary);
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  overflow: hidden;
  cursor: pointer;
}

.add-card:hover {
  filter: brightness(0.9);
}

.card.selected {
  outline: 4px solid var(--primary);
}
</style>
