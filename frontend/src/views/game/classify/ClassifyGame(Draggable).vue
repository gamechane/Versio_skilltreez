<!--Het was de wens van de opdracht gever om het rubriek spel te spelen door middel van een draggable element wij geprobeert dit te realiseren door middel van
een vue draggable elment en hier door middel van een html dragable element. Dit is ons niet gelukt maar we wouden deze code wel achter laten voor volgende teams
om te kijken hoe onze poging eruit zag en te zien waar we wellicht mis zijn gegaan.
Onze main probleem met de vue draggable is dat je on eindig in de zelfde container kon blijven slepen ongeacht of deze wel klopte-->
<template>
  <game-layout-component2 title="RubriekSpelDev" description="Development preview">
    <div class="games-container">
      <div class="grid-container">

        <template #item="{element, index}">
          <div class="list-group-item list-green">{{}}{{}}</div>
        </template>
        <section class="draggable-elements">
          <rubriek-game-card draggable="true" set="1" title="test" id="test1"></rubriek-game-card>
          <rubriek-game-card draggable="true" set="2" title="test2"></rubriek-game-card>
          <rubriek-game-card draggable="true" set="1" title="test3"></rubriek-game-card>
          <rubriek-game-card draggable="true" set="2" title="test4"></rubriek-game-card>
          <rubriek-game-card draggable="true" set="3" title="test5"></rubriek-game-card>
          <rubriek-game-card draggable="true" set="3" title="test6"></rubriek-game-card>
          <rubriek-game-card draggable="true" set="3" title="test7"></rubriek-game-card>
        </section>

        <section class="droppable-elements">
          <div class="droppable" data-draggable-id="test1"><span><rubriek-game-card  set="1" title="test1"></rubriek-game-card></span></div>
          <div class="droppable" data-draggable-id="test2"><span><rubriek-game-card  set="2" title="test2"></rubriek-game-card></span></div>
          <div class="droppable" data-draggable-id="test3"><span><rubriek-game-card  set="3" title="test3"></rubriek-game-card></span></div>


        </section>



      </div>

      <RubriekReceiver rubriek="vissen"></RubriekReceiver>
    </div>


  </game-layout-component2>
</template>

<script>
import GameLayoutComponent2 from "@/components/games/GameLayoutComponent2.vue";
import rubriekCard from "@/components/rubriekSpel/rubriekCard.vue";
import RubriekReceiver from "@/components/rubriekSpel/rubriekReceiver.vue";

export default {
  components: {rubriekCard, RubriekReceiver, GameLayoutComponent2},
}

const draggableElements = document.querySelectorAll(".draggable");
const droppableElements = document.querySelectorAll(".draggable");

draggableElements.forEach(elem => {
  elem.addEventListener("dragstart",dragStart);
  elem.addEventListener("drag",drag);
  elem.addEventListener("dragend",dragEnd);
});

droppableElements.forEach(elem  => {
  elem.addEventListener("dragenter",dragEnter);
  elem.addEventListener("dragover",dragOver);
  elem.addEventListener("dragleave",dragLeave);
  elem.addEventListener("drop",drop);
});

function dragEnter(event){
  event.target.classList.add("droppable-hover")
}

function dragLeave(event){
  event.target.classList.remove("droppable-hover")

}

function dragStart(event){
  event.dataTransfer.setData("text",event.target.id);
}

function dragOver(event){
  event.preventDefault();
}

function drop(event){
  event.preventDefault();
  event.target.classList.remove("droppable-hover");
  const draggableElementData = event.dataTransfer.getData("text");
  const droppableElementData = event.target.getAttribute("data-draggable-id");
  event.target.style.backgroundColor = draggableElementData;
  if(draggableElementData === droppableElementData){
    event.target.classList.add("dropped");
    const draggableElement = document.getElementById(draggableElementData);
    event.target.style.backgroundColor = window.getComputedStyle(draggableElement).color;
    draggableElement.classList.add("dragged");
    draggableElement.setAttribute("draggable","false");
    event.target.insertAdjacentHTML("afterbegin",`<rubriek-game-card draggable="true" set="1" title="test"></rubriek-game-card>`)
  }
}

</script>

<style scoped>
.grid-container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  width: 80%;
  grid-gap: 20px;
  margin-bottom: 50px;
}
.receiver-container{
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  grid-gap: 20px;
  margin-bottom: 50px;
}
.games-container{
  display: flex;
  flex-direction: column;
  align-items: center;
}

</style>
