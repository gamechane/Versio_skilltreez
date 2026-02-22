<template>
    <app-layout>
        <div class="container">
            <main class="main">
                <div class="header">
                    <h1>Mijn spellen</h1>
                    <div class="search-inputwrapper">
                        <input class="search-input" type="text" v-model="searchQuery" placeholder="Zoeken..." />
                    </div>
                </div>

                <table class="game-table">
                    <thead>
                    <tr>
                        <th><input type="checkbox" @change="toggleAll" /></th>
                        <th @click="sort('name')">Naam</th>
                        <th @click="sort('description')">Omschrijving</th>
                        <th @click="sort('genre')">Genre</th>
                        <th @click="sort('releaseDate')">Uitgebracht op</th>
                        <th @click="sort('lastPlayed')">Laatst gespeeld op</th>
                        <th>···</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="(game, index) in filteredGames" :key="index">
                        <td><input type="checkbox" v-model="selectedGames" :value="game" /></td>
                        <td>{{ game.name }}</td>
                        <td>{{ game.description }}</td>
                        <td>{{ game.genre }}</td>
                        <td>{{ game.releaseDate }}</td>
                        <td>{{ game.lastPlayed }}</td>
                        <td>
                            <div class="action-icons">
                                <img class="binImg" alt="Kopieëren" :src="copyImg" @click="copyGame(game)" />
                                <img class="binImg" alt="Verwijderen" :src="binImg" @click="confirmDelete(game)" />
                            </div>
                        </td>
                    </tr>
                    <tr class="newGame" @click="addGame">
                        <td><span class="plus-sign">&#43;</span></td>
                        <td>Nieuw spel</td>
                        <td colspan="5"></td>
                    </tr>
                    </tbody>
                </table>
                <div v-if="showDeleteConfirm" class="delete-confirm">
                    <p>Weet u zeker dat u het spel wilt verwijderen?</p>
                    <button @click="cancelDelete">ANNULEER</button>
                    <button @click="deleteGame">JA</button>
                </div>
            </main>
        </div>
    </app-layout>
</template>

<script setup>
import { ref, computed } from 'vue';
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import binImg from "@/assets/bin.png";
import copyImg from "@/assets/copy.png";
import router from "@/router/index.js";

const games = ref([
    { name: "De Avonturen van Aap", description: "Een spannend avontuurspel", genre: "Avontuur", releaseDate: "01-02-2022", lastPlayed: "15-03-2023" },
    { name: "Race Koning", description: "Snelste auto race spel", genre: "Racen", releaseDate: "12-05-2021", lastPlayed: "20-06-2023" },
    { name: "Puzzel Meester", description: "Uitdagende puzzels om op te lossen", genre: "Puzzel", releaseDate: "23-08-2020", lastPlayed: "10-01-2023" },
    { name: "Ruimte Verkenner", description: "Verken de verre ruimte", genre: "Sciencefiction", releaseDate: "15-11-2019", lastPlayed: "02-07-2023" },
]); //todo vullen uit backend

const searchQuery = ref("");
const selectedGames = ref([]);
const showDeleteConfirm = ref(false);
const gameToDelete = ref(null);

const filteredGames = computed(() => {
    return games.value.filter(game =>
        game.name.toLowerCase().includes(searchQuery.value.toLowerCase())
    );
});

const sort = (key) => {
    games.value.sort((a, b) => (a[key] > b[key] ? 1 : -1));
};

const toggleAll = (event) => {
    if (event.target.checked) {
        selectedGames.value = [...games.value];
    } else {
        selectedGames.value = [];
    }
};

const confirmDelete = (game) => {
    gameToDelete.value = game;
    showDeleteConfirm.value = true;
};

const deleteGame = () => {
    const index = games.value.indexOf(gameToDelete.value);
    if (index !== -1) {
        games.value.splice(index, 1);
    }
    showDeleteConfirm.value = false;
};
const cancelDelete = () => {
    showDeleteConfirm.value = false;
};
const copyGame = (game) => {
    const details = `
    Name: ${game.name}
    Description: ${game.description}
    Genre: ${game.genre}
    Release Date: ${game.releaseDate}
    Last Played: ${game.lastPlayed}
  `;
    alert(`Game details:\n${details}`);
}

const addGame = () => {
    router.push({ name: 'Create game' });
};

</script>

<style scoped>
.container {
    display: flex;
    width: 100%;
    height: calc(100vh - 40px);
    overflow: hidden;
}

.main {
    flex: 1;
    padding: 10px 20px;
    overflow-y: auto;
}

.header {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}

.header h1 {
    margin-left: auto;
}

.search-input {
    width: 200px;
    padding: 10px;
    font-size: 16px;
    background-color: var(--primary-light);
    border: none;
}
.search-inputwrapper {
    margin-left: auto;
}

.binImg {
    width: 20px;
    height: auto;
    display: inline-block;
    vertical-align: middle;
    cursor: pointer;
}

.action-icons {
    display: flex;
    gap: 5px;
    opacity: 50%;
}

.game-table {
    width: 100%;
    border-collapse: collapse;
}

.game-table th, .game-table td {
    border: none;
    padding: 8px;
    text-align: left;
}

.game-table th {
    cursor: pointer;
    background-color: var(--primary-dark); /*todo --primary for selected */
}

.game-table tbody tr:nth-child(even) {
    background-color: var(--secondary-light);
}

.game-table tbody tr:nth-child(odd) {
    background-color: var(--secondary);
}

.delete-confirm {
    position: fixed;
    top: 30%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: #fff;
    border: 1px solid black;
    padding: 20px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.delete-confirm p {
    margin-bottom: 20px;
}

.delete-confirm button {
    margin-right: 10px;
    left: 100%;
    font-size: 16px;
    cursor: pointer;
    border: none;
    border-radius: 5px;
}

.delete-confirm button {
    background-color: white;
    color: black;
}

.newGame {
    cursor: pointer;
    font-weight: bold;
}

.plus-sign {
    font-size: 24px;
    font-weight: bold;
    color: black;
}
</style>
