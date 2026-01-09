<template>
  <app-layout>
    <div class="container">
      <main class="main">
        <div class="profile-header">
          <img class="profile-picture" alt="Profile" :src="profileImg" />
          <div class="profile-info">
            <h2>Gebruikersnaam</h2>
            <a href="/profile/edit-password">Wachtwoord wijzigen</a>
          </div>
        </div>

        <div class="tables-container">
          <div class="table-wrapper">
            <h2 class="table-title">Mijn skilltrees</h2>
            <table class="profile-table">
              <thead>
              <tr>
                <th @click="sort('skilltrees', 'name')">Naam</th>
                <th @click="sort('skilltrees', 'played')">Gespeeld</th>
                <th @click="sort('skilltrees', 'result')">Resultaat</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item, index) in skilltrees" :key="index">
                <td>{{ item.name }}</td>
                <td>{{ item.played }}</td>
                <td>{{ item.result }}</td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="table-wrapper">
            <h2 class="table-title">Mijn spellen</h2>
            <table class="profile-table">
              <thead>
              <tr>
                <th @click="sort('games', 'name')">Naam</th>
                <th @click="sort('games', 'played')">Gespeeld</th>
                <th @click="sort('games', 'result')">Resultaat</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item, index) in games" :key="index">
                <td>{{ item.name }}</td>
                <td>{{ item.played }}</td>
                <td>{{ item.result }}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </main>
    </div>
  </app-layout>
</template>

<script setup>
import { ref } from 'vue';
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import profileImg from "@/assets/profile.png";

const skilltrees = ref([
  { name: "Dier en voedsel, HAVO 5", played: 19, result: "95%" },
  { name: "Tijdperken, HAVO 5", played: 5, result: "80%" },
  { name: "Dier en voedsel, HAVO 5", played: 6, result: "76%" },
  { name: "Literatuurlijsten, HAVO 5", played: 13, result: "88%" },
]); //todo haal uit backend

const games = ref([
  { name: "Zoogdieren herkennen", played: 36, result: "95%" },
  { name: "Zoogdieren herkennen", played: 14, result: "80%" },
  { name: "Zoogdieren herkennen", played: 13, result: "76%" },
  { name: "Zoogdieren herkennen", played: 12, result: "88%" },
]);//todo haal uit backend

const currentSortKey = ref('');
const currentSortOrder = ref('asc');

const sort = (listName, key) => {
  let list;
  if (listName === 'skilltrees') {
    list = skilltrees;
  } else if (listName === 'games') {
    list = games;
  } else {
    return;
  }

  if (currentSortKey.value === key) {
    currentSortOrder.value = currentSortOrder.value === 'asc' ? 'desc' : 'asc';
  } else {
    currentSortOrder.value = 'asc';
  }
  currentSortKey.value = key;

  list.value.sort((a, b) => {
    if (a[key] < b[key]) return currentSortOrder.value === 'asc' ? -1 : 1;
    if (a[key] > b[key]) return currentSortOrder.value === 'asc' ? 1 : -1;
    return 0;
  });
};

const changePassword = () => {
  alert("Wachtwoord wijzigen is currently not implemented.");
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
  padding: 20px;
  overflow-y: auto;
}

.profile-header {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
}

.profile-picture {
  width: 100px;
  height: 100px;
  border-radius: 50%;
}

.profile-info {
  margin-left: 20px;
  text-align: center;
}

.profile-info h2 {
  margin: 0;
}

.profile-info a {
  color: blue;
  text-decoration: underline;
  cursor: pointer;
}

.tables-container {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.table-wrapper {
  flex: 1;
  margin: 0 10px;
}

.table-title {
  background-color: #24A824;
  color: black;
  text-align: center;
  padding: 10px;
  margin-bottom: 0;
}

.profile-table {
  width: 100%;
  border-collapse: collapse;
}

.profile-table th, .profile-table td {
  padding: 8px;
  text-align: left;
}

.profile-table th {
  cursor: pointer;
  background-color: var(--primary);
  color: black;
}

.profile-table tbody tr:nth-child(even) {
  background-color: var(--secondary-light);
}

.profile-table tbody tr:nth-child(odd) {
  background-color: var(--secondary);
}
</style>
