<script setup>
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import gameDidactiek2Img from "@/assets/game_didactiek_2.svg";
</script>

<template>
  <app-layout>
    <div class="container">
      <main class="main">
        <h1>Over Skilltreez</h1>
        <div class="content-container">
          <p>
            Wil je meer leren over het toepassen van skill-trees en het leren gebruiken van de software aan de hand van de
            taxonomie van Bloom? In het boek Gamedidactiek 2 legt Martijn Koops uit hoe bij een specifiek leerdoel een skill-tree
            kan worden gebouwd.<br>
            <br>
            In Gamedidactiek2 legt Martijn, aan de hand van de herziene taxonomie van Bloom, uit hoe bij een specifiek leerdoel
            een skill-tree kan worden gebouwd. Deze vormt de ruggengraat van een arrangement van eenvoudig zelf te maken
            minigames om leerlingen formatieve feedback te geven bij een specifiek leerdoel.Hij vertelt hoe je bekende en minder
            bekende, online game editors zoals socrative, learningapps, decktoys, en vele andere, op een onderbouwde manier
            kunt inzetten in je eigen onderwijs.<br>
            <br>
            Leerlingen hebben behoefte aan feedback. Ze willen weten of ze goed bezig zijn en waar de kennis en of vaardigheden
            tekort schieten. In een klas is meestal maar één docent. Dat maakt het geven van persoonlijke feedback lastig.
            Computerspelletjes kunnen hier een uitkomst bieden. Leerlingen vinden spelletjes vaak leuk en uitdagend.
            Als we nu slimme spelletjes ontwerpen, kunnen we die gebruiken om de leerling van feedback te voorzien, en tegelijkertijd te motiveren.
            Dit boek biedt eerst een structuur om lesinhoud op te delen in onderdelen. Vervolgens analyseren we de onderdelen
            en bekijken wat voor spellen daar het beste bij passen. Steeds maken we gebruik van eenvoudige computerspellen,
            die we als docent daadwerkelijk zelf kunnen realiseren, zonder dat we hoeven te programmeren.
            Zo bouwen we, stap voor stap, een compleet arrangement van formatieve toetsspellen bij een specifieke les of cursus.
          </p>
          <img :src="gameDidactiek2Img" alt="Game didactiek 2 book">
        </div>

      </main>
    </div>
  </app-layout>
</template>

<style scoped>
.container {
  display: flex;
  width: 100%;
  height: calc(100vh - 40px);
  overflow: hidden;
}

.main {
  display: flex;
  flex-direction: column;
  flex: 1;
  background: var(--secondary-background);
  margin: 15px 35px 0 35px;
  align-items: center;
}

.content-container {
  display: flex;
  justify-content: space-between;
  padding: 0 50px 0 50px;
}

img {
  float: right;
  margin: 0 50px 0 50px;
}
</style>
