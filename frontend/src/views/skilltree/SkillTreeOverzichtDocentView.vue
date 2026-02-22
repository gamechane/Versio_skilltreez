<template>
  <app-layout>
    <div class="container">
      <main class="main">
        <div class="header">
          <h1>Mijn skilltrees</h1>
          <div class="search-inputwrapper">
            <input class="search-input" type="text" v-model="searchQuery" placeholder="Zoeken..." />
          </div>
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
            <th>···</th>
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
            <td>
              <div class="action-icons">
                <img class="binImg" alt="Kopieëren" :src="copyImg" @click="copySkilltree(skilltree)" />
                <img class="binImg" alt="Verwijderen" :src="binImg" @click="confirmDelete(skilltree)" />
              </div>
            </td>
          </tr>
          <tr class="newSkilltree" @click="addSkilltree">
            <td><span class="plus-sign">&#43</span></td>
            <td>Nieuwe skilltree</td>
            <td colspan="5"></td>
          </tr>
          </tbody>
        </table>
        <div v-if="showDeleteConfirm" class="delete-confirm">
          <p>Weet u zeker dat u de skilltree wilt verwijderen?</p>
          <button @click="cancelDelete">ANNULEER</button>
          <button @click="deleteSkilltree">JA</button>
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

const skilltrees = ref([
  { name: "Topografie, HAVO 5", description: "Steden van Europa", points: "10 / 30", created: "29-01-2023", updated: "30-01-2023" },
  { name: "Tijdperken, HAVO 5", description: "Geschiedenis", points: "10 / 30", created: "29-01-2023", updated: "30-01-2023" },
  { name: "Dier en voedsel, HAVO 5", description: "Snelheid van dieren en wat ze eten", points: "10 / 30", created: "29-01-2023", updated: "30-01-2023" },
  { name: "Literatuurlijsten, HAVO 5", description: "Toets over de gelezen boeken", points: "10 / 30", created: "29-01-2023", updated: "30-01-2023" },
]); //todo vullen uit backend

const searchQuery = ref("");
const selectedSkilltrees = ref([]);
const showDeleteConfirm = ref(false);
const skilltreeToDelete = ref(null);

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

const confirmDelete = (skilltree) => {
  skilltreeToDelete.value = skilltree;
  showDeleteConfirm.value = true;
};

const deleteSkilltree = () => {
  const index = skilltrees.value.indexOf(skilltreeToDelete.value);
  if (index !== -1) {
    skilltrees.value.splice(index, 1);
  }
  showDeleteConfirm.value = false;
};

const cancelDelete = () => {
  showDeleteConfirm.value = false;
};

const copySkilltree = (skilltree) =>{
  const details = `
    Name: ${skilltree.name}
    Description: ${skilltree.description}
    Points: ${skilltree.points}
    Created: ${skilltree.created}
    Updated: ${skilltree.updated}
  `;
  alert(`Skilltree details:\n${details}`);
}

const addSkilltree = () => {
  router.push({ name: 'Create skill tree' });
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
.search-inputwrapper{
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

.newSkilltree {
  cursor: pointer;
  font-weight: bold;
}

.plus-sign {
  font-size: 24px;
  font-weight: bold;
  color: black;
}
</style>
