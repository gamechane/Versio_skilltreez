<template>
    <game-layout-component2 title="RubriekSpelDev" description="Development preview">
        <div class="games-container">
            <div class="grid-container">
                <rubriek-game-card set="voertuigen" title="auto"></rubriek-game-card>
                <rubriek-game-card set="vogels" title="meeuw"></rubriek-game-card>
                <rubriek-game-card set="vissen" title="karper"></rubriek-game-card>
                <rubriek-game-card set="voetuigen" title="fiets"></rubriek-game-card>
                <rubriek-game-card set="vogels" title="kraai"></rubriek-game-card>
                <rubriek-game-card set="vissen" title="snoek"></rubriek-game-card>
            <div class="receiver-container">
                <RubriekReceiver rubriek="voetuigen"></RubriekReceiver>
                <RubriekReceiver rubriek="vogels"></RubriekReceiver>
                <RubriekReceiver rubriek="vissen"></RubriekReceiver>
            </div>
        </div>


    </game-layout-component2>
</template>

<script>
import GameLayoutComponent2 from "@/components/games/GameLayoutComponent2.vue";
import rubriekCard from "@/components/rubriekSpel/rubriekCard.vue";
import RubriekReceiver from "@/components/rubriekSpel/rubriekReceiver.vue";

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