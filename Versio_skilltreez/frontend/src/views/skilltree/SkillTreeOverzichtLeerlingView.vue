<template>
  <app-layout>
    <div class="container">
      <main class="main">
        <div class="actions">
          <form @submit.prevent="addViaGamepin">
            <div class="input-wrapper">
              <input type="text" class="gamepin-input" placeholder="Gamepin" />
              <button type="submit">+</button>
            </div>
          </form>
          <h1>Mijn skilltrees</h1>
          <input type="text" v-model="searchQuery" placeholder="Zoeken..." />
        </div>

        <table class="skilltree-table">
          <thead>
          <tr>
            <th><input type="checkbox" @change="toggleAll" /></th>
            <th @click="sort('name')">Naam</th>
            <th @click="sort('description')">Omschrijving</th>
            <th @click="sort('points')">Punten</th>
            <th @click="sort('created')">Gemaakt op</th>
            <th @click="sort('updated')">Gewijzigd op</th>
            <th><img class="binImg" alt="Verwijderen" :src="binImg" />️</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(skilltree, index) in filteredSkilltrees" :key="index">
            <td><input type="checkbox" v-model="selectedSkilltrees" :value="skilltree" /></td>
            <td>{{ skilltree.name }}</td>
            <td>{{ skilltree.description }}</td>
            <td>{{ skilltree.points }}</td>
            <td>{{ skilltree.created }}</td>
            <td>{{ skilltree.updated }}</td>
            <td></td>
          </tr>
          </tbody>
        </table>
      </main>
    </div>
  </app-layout>
</template>

<script setup>
import { ref, computed } from 'vue';
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import binImg from "@/assets/bin.png";

const skilltrees = ref([
  { name: "Topografie, HAVO 5", description: "Steden van Europa", points: "10 / 30", created: "29-01-2023", updated: "30-01-2023" },
  { name: "Tijdperken, HAVO 5", description: "Geschiedenis", points: "10 / 30", created: "29-01-2023", updated: "30-01-2023" },
  { name: "Dier en voedsel, HAVO 5", description: "Snelheid van dieren en wat ze eten", points: "10 / 30", created: "29-01-2023", updated: "30-01-2023" },
  { name: "Literatuurlijsten, HAVO 5", description: "Toets over de gelezen boeken", points: "10 / 30", created: "29-01-2023", updated: "30-01-2023" },
]); //todo vullen uit backend

const searchQuery = ref("");
const selectedSkilltrees = ref([]);

const filteredSkilltrees = computed(() => {
  return skilltrees.value.filter(skilltree =>
      skilltree.name.toLowerCase().includes(searchQuery.value.toLowerCase())
  );
});

const sort = (key) => {
  skilltrees.value.sort((a, b) => (a[key] > b[key] ? 1 : -1));
};

const toggleAll = (event) => {
  if (event.target.checked) {
    selectedSkilltrees.value = [...skilltrees.value];
  } else {
    selectedSkilltrees.value = [];
  }
};
const addViaGamepin = () => {
  const inputElement = document.querySelector('.gamepin-input');
  const gamepinValue = inputElement.value;
  alert(`Gamepin value entered: ${gamepinValue}`); // todo add the skilltree that matches with gamepin from backend and add to the list
  inputElement.value = '';
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

.actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.actions input {
  width: 200px;
  padding: 10px;
  font-size: 16px;
  background-color: var(--primary-light);
  border: none;
}

.actions button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
}
.input-wrapper {
  position: relative;
  display: inline-block;
}

.input-wrapper .gamepin-input {
  width: calc(100% - 40px);
  padding: 10px;
  font-size: 16px;
  background-color: white;
  border: 1px solid #ccc;
}

.input-wrapper button[type="submit"] {
  position: absolute;
  top: 0;
  right: 0;
  padding: 10px 20px;
  background-color: white;
  color: black;
  cursor: pointer;
  border: 1px solid #ccc;
  border-left: none;
  font-size: 16px;
  font-weight: bold;
  }

.skilltree-table {
  width: 100%;
  border-collapse: collapse;
}

.skilltree-table th, .skilltree-table td {
  border: none;
  padding: 8px;
  text-align: left;
}

.skilltree-table th {
  cursor: pointer;
  background-color: var(--primary-dark); /*todo --primary for selected */
}
.skilltree-table tbody tr:nth-child(even) {
  background-color: var(--secondary-light);
}

.skilltree-table tbody tr:nth-child(odd) {
  background-color: var(--secondary);
}

.binImg {
  width: 20px;
  height: auto;
  display: inline-block;
  vertical-align: middle;
}

</style>
