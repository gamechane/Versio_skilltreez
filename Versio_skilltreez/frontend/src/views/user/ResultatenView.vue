<template>
  <app-layout>
    <div class="container">
      <main class="main">
        <div class="actions">
          <h1>Resultaten</h1>
          <input type="text" v-model="searchQuery" placeholder="Zoeken..." />
        </div>

        <table class="result-table">
          <thead>
          <tr>
            <th><input type="checkbox" @change="toggleAll" /></th>
            <th @click="sort('name')">Naam <span>&#9660;</span></th>
            <th @click="sort('points')">Punten <span>&#9660;</span></th>
            <th @click="sort('group')">Groep <span>&#9660;</span></th>
            <th>···</th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="(result, index) in filteredResults"
              :key="index"
              :class="{ 'highlighted': selectedResults.includes(result) }"
          >
            <td><input type="checkbox" v-model="selectedResults" :value="result" /></td>
            <td>{{ result.name }}</td>
            <td>{{ result.points }}</td>
            <td>{{ result.group }}</td>
            <td>
              <div class="action-icons">
                <img class="binImg" alt="Verwijderen" :src="binImg" @click="confirmDelete(result)" />
              </div>
            </td>
          </tr>
          </tbody>
        </table>
        <div v-if="showDeleteConfirm" class="delete-confirm">
          <p>Weet je zeker dat je het resultaat wil verwijderen?</p>
          <button @click="cancelDelete">ANNULEER</button>
          <button @click="deleteResult">JA</button>
        </div>
      </main>
    </div>
  </app-layout>
</template>

<script setup>
import { ref, computed } from 'vue';
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import binImg from "@/assets/bin.png";

const results = ref([
  { name: "Khalid", points: 65, group: "A5F" },
  { name: "Jop", points: 60, group: "H5E" },
  { name: "Eray", points: 55, group: "A6A" },
  { name: "Martijn", points: 40, group: "H4D" },
]); // TODO: Fill from backend

const searchQuery = ref("");
const selectedResults = ref([]);
const showDeleteConfirm = ref(false);
const resultToDelete = ref(null);

const filteredResults = computed(() => {
  const query = searchQuery.value.toLowerCase();
  return results.value.filter(result =>
      result.name.toLowerCase().includes(query) ||
      result.group.toLowerCase().includes(query)
  );
});

const sort = (key) => {
  results.value.sort((a, b) => (a[key] > b[key] ? 1 : -1));
};

const toggleAll = (event) => {
  if (event.target.checked) {
    selectedResults.value = [...results.value];
  } else {
    selectedResults.value = [];
  }
};

const confirmDelete = (result) => {
  resultToDelete.value = result;
  showDeleteConfirm.value = true;
};

const deleteResult = () => {
  const index = results.value.indexOf(resultToDelete.value);
  if (index !== -1) {
    results.value.splice(index, 1);
  }
  showDeleteConfirm.value = false;
};

const cancelDelete = () => {
  showDeleteConfirm.value = false;
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

.result-table {
  width: 100%;
  border-collapse: collapse;
}

.result-table th, .result-table td {
  border: none;
  padding: 8px;
  text-align: left;
}

.result-table th {
  cursor: pointer;
  background-color: var(--primary-dark);
}

.result-table tbody tr:nth-child(even) {
  background-color: var(--secondary-light);
}

.result-table tbody tr:nth-child(odd) {
  background-color: var(--secondary);
}

.result-table tbody tr.highlighted {
  background-color: var(--primary);
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
</style>
