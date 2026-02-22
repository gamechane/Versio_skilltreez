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
        <div class="content">
          <div class="table-wrapper">
            <h2 class="table-title1">Mijn Skiltrees</h2>
            <table class="profile-table">
              <tbody>
              <tr v-for="(item, index) in skilltrees" :key="index">
                <td class="checkbox-column"><input type="checkbox" /></td>
                <td class="name-column">{{ item.name }}</td>
                <td class="progress-column">{{ item.progress }}</td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="groups-wrapper">
            <h2 class="groups-title">Mijn groepen</h2>
            <div class="group-item" v-for="(group, index) in groups" :key="index">
              <div class="group-name">{{ group.name }}</div>
              <div class="group-progress">{{ group.progress }}</div>
            </div>
          </div>
          <div class="rewards-wrapper">
            <img class="rewards-icon" alt="Rewards" :src="rewardsImg" />
            <h2 class="rewards-title">Mijn beloningen</h2>
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
import rewardsImg from "@/assets/rewards.png"; // Add your rewards icon image path here

const skilltrees = ref([
  { name: "Topografie, HAVO 5", progress: "20 / 50" },
  { name: "Tijdperken, HAVO 5", progress: "25 / 60" },
  { name: "Dier en voedsel, HAVO 5", progress: "18 / 40" },
  { name: "Literatuurlijsten, HAVO 5", progress: "15 / 15" },
]);

const groups = ref([
  { name: "H5E", progress: "20 / 50" },
]);

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

.content {
  display: flex;
  justify-content: space-around;
  align-items: flex-start;
}

.table-wrapper, .groups-wrapper, .rewards-wrapper {
  flex: 1;
  margin: 0 10px;
  text-align: center;
}

.groups-title, .rewards-title {
  background-color: white;
  color: black;
  padding: 10px;
  margin-bottom: 0;
}
.table-title1{
  background-color: #24A824;
  color: black;
  padding: 10px;
  margin-bottom: 0;
}

.groups-wrapper, .rewards-wrapper {
  background-color: white;
  padding: 10px;
  border-radius: 5px;
}

.profile-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 0px;
}

.profile-table th, .profile-table td {
  padding: 8px;
  text-align: left;
}

.profile-table th {
  background-color: var(--primary);
  color: black;
}

.profile-table th {
  background-color: var(--primary);
  color: black;
}

.profile-table tbody .checkbox-column, .profile-table tbody .name-column {
  background-color: var(--secondary);
}

.profile-table tbody .progress-column {
  background-color: #24A824;
}

.groups-wrapper .group-item {
  display: flex;
  justify-content: space-between;
  background-color: var(--secondary-light);
  padding: 10px;
  margin-top: 10px;
  border-radius: 5px;
}

.rewards-icon {
  width: 100px;
  height: 100px;
  margin-top: 20px;
}
</style>
