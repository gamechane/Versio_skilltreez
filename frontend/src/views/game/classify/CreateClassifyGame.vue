<!--Dit is het component voor het scherm waar de docent een rubriek spel aankan maken en de kaarten voor het spel kan maken-->
<script setup>
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import NextButton from "@/components/selectieSpel/NextButton.vue";
import DocentGameCreationLayout from "@/components/shared/DocentGameCreationLayout.vue";
import RubriekCardCreate from "@/components/rubriekSpel/rubriekCardCreate.vue";
import {ref} from "vue";
import RubriekReceiver from "@/components/rubriekSpel/rubriekReceiver.vue";
import router from "@/router/index.js";
import trashBin from "@/assets/bin.svg";
import voegToeKnop from "@/assets/voegToeKnop.svg"



//data lijst met de kaarten, deze zal naar de backend gaan wanneer dat mogelijk is
const cards = ref([
  {}
]);
//data lijst met rubrieken, deze zal naar de backend gaan wanneer dat mogelijk is
const rubrieken = ref([
  {id: "1", rubriek: "vissen"}
])

//constants nodig voor functionaliteit
const showReceiverDialog = ref(false);
const newReceiverName = ref("");
const selected = ref(null);

//functie om een nieuwe kaart te genereren
const addCard = () => {
  cards.value.push({id: (new Date().getMilliseconds()).toString(),title: "", rubriek: ""});
}

//functie om de dialog te openen om een nieuwe receiver te genereren
const addReceiver = () => {
  showReceiverDialog.value = true;
};
//functie om de receiver toe te voegen
const saveReceiver = () => {
  if (newReceiverName.value.trim()) {
    rubrieken.value.push({id: (new Date().getMilliseconds()).toString(), rubriek: newReceiverName.value.trim() });
    newReceiverName.value = "";
    showReceiverDialog.value = false;
  }
};
//functie om de dialog te resetten
const cancelReceiver = () => {
  newReceiverName.value = "";
  showReceiverDialog.value = false;
};

//function om de kaarten in de lijst overeen te laten komen met het kaart object
const updateCard = (inputCard) => {
  const card = cards.value.find(card => card.id === inputCard.id);
  if (card) {
    card.title = inputCard.title;
    card.rubriek = inputCard.rubriek;
    card.imgSrc = inputCard.imgSrc;
  }
};

//dit is een functie om het spel tijdelijk op te slaan voordat het naar de database gaat (het scherm hierna heeft pas de daadwerkelijke opslaan functie)
const saveAndNavigate = () => {
  console.log()
  console.log(cards)
  console.log(rubrieken)
}


//prullenbak functie om het geselecteerde item te verwijderen
const removeSelected = () => {
  if (!selected.value) {
    return;
  }

  const cardIndex = cards.value.findIndex(card => card.id === selected.value.id);
  if (cardIndex !== -1) {
    cards.value.splice(cardIndex, 1);
  }

  const rubriekIndex = rubrieken.value.findIndex(rubriek => rubriek.id === selected.value.id);
  if (rubriekIndex !== -1) {
    rubrieken.value.splice(rubriekIndex, 1);
  }
  selected.value = null;
}

//functie om een item te selecteren
const handleClick = (card) => {
  selected.value = card;
  console.log(selected.value)
}

</script>

<template>
  <AppLayout :showBackButton="false">
    <docent-game-creation-layout>
      <div class="container">
        <img :src="trashBin" class="trash-bin" @click="removeSelected"/>
        <NextButton class="nextButton" route="/games/create/classify-game/settings" @click="saveAndNavigate" />
        <!--container voor de kaarten + de 'nieuwe' kaart knop-->
        <div class="rubriek-container">
          <div class="rubriek-card-container">
            <rubriek-card-create
                v-for="card in cards"
                @click="() => handleClick(card)"
                :key = "card.id"
                :title="card.title"
                :rubriek="card.rubriek"
                :imgSrc="card.imgSrc"
                :rubrieken = "rubrieken"
                @update:title="(newTitle) => updateCard({...card, title: newTitle})"
                @update:rubriek="(newRubriek) => updateCard({...card, rubriek: newRubriek})"
                @update:imgSrc="(newImgSrc) => updateCard({...card, imgSrc: newImgSrc})"
            />
          </div>
          <img :src="voegToeKnop" class="rubriek-create-button" @click="addCard"/>
        </div>

        <div class="receiver-container">
          <div class="receiver-card-container">
            <rubriek-receiver
                v-for="receiver in rubrieken"
                @click="() => handleClick(receiver)"
                :rubriek = "receiver.rubriek"
                :key="receiver.id"
            />
          </div>

          <img :src="voegToeKnop" class="receiver-create-button" @click="addReceiver"/>
        </div>
      </div>
      <!--Een dialog voor wanneer er een receiver moet worden toegevoegd-->
      <div v-if="showReceiverDialog" class="dialog-overlay">
        <div class="dialog">
          <h3>Enter Receiver Name</h3>
          <input v-model="newReceiverName" placeholder="Receiver Name" />
          <button @click="saveReceiver">Save</button>
          <button @click="cancelReceiver">Cancel</button>
        </div>
      </div>
    </docent-game-creation-layout>
  </AppLayout>

</template>


<style scoped>
.container {
}

div {
  border-style: dashed;
  border-color: black;
}
.nextButton{
  position: absolute;
  right: 10px;
  top: 50%;
}

.rubriek-card-container{
  display: grid;
  grid-template-columns: repeat(5, 1fr);
}
.receiver-card-container{
  display: grid;
  grid-template-columns: repeat(5, 1fr);
}

.rubriek-container {
  display: flex;
}

.receiver-container{
  display: flex;
}

.trash-bin {
  position: absolute;
  left: 10px;
  top: 10px;
}

.dialog-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.dialog {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.dialog h3 {
  margin-top: 0;
}

.dialog input {
  display: block;
  width: 100%;
  margin: 10px 0;
}

.dialog button {
  margin-right: 10px;
}
</style>
