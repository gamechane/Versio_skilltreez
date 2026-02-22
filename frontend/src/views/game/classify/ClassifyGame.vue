<script setup>
import RubriekCard from "@/components/rubriekSpel/rubriekCard.vue";
import gameLayoutComponent2 from "@/components/games/GameLayoutComponent2.vue";
import {ref} from "vue";


// dit is de lijst met rubrieken die gevuld moet worden vanuit de back end
const rubrieken = ref([
  {name:"Vogels"},{name: 'Vissen'},{name: 'voertuigen'}
]);

// dit is de lijst met speek kaarten die gevuld moet worden vanuit de backend
const cards = ref([
      { id: 1, title: 'Snoek', rubriek: 'Vissen', selectedRubriek: null },
      { id: 2, title: 'Meeuw', rubriek: 'Vogels', selectedRubriek: null },
      { id: 3, title: 'auto', rubriek: 'Voertuigen', selectedRubriek: null}
    ]
)

const spelWin = () =>{
  alert("Yippie!")
  //hier zou je dan de win opslaan in de data base met levens en tijd dat over is als dit geinplementeerd is
}

const updateCard = (rubriekCard) => {
  const card = cards.value.find(card => card.id === rubriekcard.id)
  if (card){
    card.selectedRubriek = rubriekCard.selectedRubriek;
  }
}
const checkAnswers = () =>{
  for (const card in cards){
    if (!card.selectedRubriek){
      alert("niet alle kaarten hebben een rubriek gekozen")
      return
    }
    if (!card.selectedRubriek !== card.rubriek){
      alert("niet alle antwoorden kloppen")
      return;
    }
    else spelWin()
  }
}
</script>

<template>
  <gameLayoutComponent2 title="RubriekSpelDev" description="Development preview">
    <div class="games-container">
      <div class="grid-container">
        <RubriekCard
            v-for="card in cards"
            :key = "card.id"
            :title="card.title"
            :rubriek="card.rubriek"
            :rubrieken = "rubrieken"
            :selectedRubriek=null
            @update:rubriek="(newRubriek) => updateCard(...{card, selectedRubriek: newRubriek})"
        ></RubriekCard>
      </div>
      <button class="button" @click="checkAnswers">submit</button>
    </div>
  </gameLayoutComponent2>
</template>

<style scoped>
.grid-container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  width: 80%;
  grid-gap: 20px;
  margin-bottom: 50px;
}
.button{
  background-color: var(--primary-light);
  border: none;
  color: black;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 2px;
  padding: 10px 24px;
  font-weight: bold;
}
.games-container{
  display: flex;
  flex-direction: column;
  align-items: center;
}

</style>
