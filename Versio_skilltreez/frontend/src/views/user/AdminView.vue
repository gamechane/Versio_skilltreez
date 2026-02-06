<script setup>
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import InputField from "@/components/shared/InputField.vue";
import DropdownMenu from "@/components/shared/DropdownMenu.vue";
import SubmitButton from "@/components/shared/SubmitButton.vue";
import { ref, onMounted } from "vue";
import { ApiService } from "@/services/api.js";
import { DataManager } from "@/services/store.js";

const roleOptions = ["Student", "Docent", "Admin"];

// Form state
const showForm = ref(false);
const usernameInput = ref("");
const emailInput = ref("");
const passwordInput = ref("");
const repeatPasswordInput = ref("");
const roleInput = ref("Student");

// Users list
const users = ref([]);
const loading = ref(true);
const error = ref(null);

// Load users on mount
onMounted(async () => {
  await loadUsers();
});

async function loadUsers() {
  loading.value = true;
  error.value = null;
  try {
    const token = DataManager.token.get();
    const result = await ApiService.makeApiCall("user/", "GET", token);
    users.value = result || [];
  } catch (e) {
    error.value = "Kon gebruikers niet laden";
    console.error(e);
  }
  loading.value = false;
}

async function createUser() {
  if (passwordInput.value !== repeatPasswordInput.value) {
    alert("Wachtwoorden komen niet overeen");
    return;
  }

  try {
    const token = DataManager.token.get();
    const createUserBody = {
      username: usernameInput.value.toLowerCase(),
      emailAddress: emailInput.value.toLowerCase(),
      password: passwordInput.value,
      roles: [mapRoleToBackend(roleInput.value)],
    };

    await ApiService.makeApiCall("user/", "POST", token, createUserBody);
    
    // Reset form and reload users
    resetForm();
    await loadUsers();
  } catch (e) {
    alert("Kon gebruiker niet aanmaken: " + e.message);
    console.error(e);
  }
}

async function deleteUser(user) {
  if (!confirm(`Weet je zeker dat je ${user.username} wilt verwijderen?`)) {
    return;
  }

  try {
    const token = DataManager.token.get();
    await ApiService.makeApiCall("user/", "DELETE", token, { id: user.id });
    await loadUsers();
  } catch (e) {
    alert("Kon gebruiker niet verwijderen: " + e.message);
    console.error(e);
  }
}

function mapRoleToBackend(role) {
  const roleMap = {
    "Student": "STUDENT",
    "Docent": "GAME_MASTER_FREE",
    "Admin": "ADMIN"
  };
  return roleMap[role] || "STUDENT";
}

function mapRoleToDisplay(roles) {
  if (!roles || roles.length === 0) return "Onbekend";
  
  // Get the first role (or most relevant one)
  const role = Array.isArray(roles) ? roles[0] : roles;
  
  if (role.includes("ADMIN")) return "Admin";
  if (role.includes("GAME_MASTER_PREMIUM")) return "Docent (Premium)";
  if (role.includes("GAME_MASTER_FREE") || role.includes("GAME_MASTER")) return "Docent";
  if (role.includes("STUDENT")) return "Student";
  return role;
}

function resetForm() {
  showForm.value = false;
  usernameInput.value = "";
  emailInput.value = "";
  passwordInput.value = "";
  repeatPasswordInput.value = "";
  roleInput.value = "Student";
}
</script>

<template>
  <AppLayout :showBackButton="true">
    <div class="admin-container">
      <div class="admin-header">
        <h1>Gebruikersbeheer</h1>
        <button class="add-button" @click="showForm = !showForm">
          {{ showForm ? 'Annuleren' : '+ Nieuwe gebruiker' }}
        </button>
      </div>

      <!-- User Creation Form -->
      <div v-if="showForm" class="user-form">
        <h2>Nieuwe gebruiker aanmaken</h2>
        <form @submit.prevent="createUser()">
          <InputField
            id="admin-username"
            v-model="usernameInput"
            inputType="text"
            placeholder="Gebruikersnaam"
            width="300px"
            :required="true"
          />
          <InputField
            id="admin-email"
            v-model="emailInput"
            inputType="email"
            placeholder="E-mailadres"
            width="300px"
            :required="true"
          />
          <InputField
            id="admin-password"
            v-model="passwordInput"
            inputType="password"
            placeholder="Wachtwoord"
            width="300px"
            :required="true"
          />
          <InputField
            id="admin-repeat-password"
            v-model="repeatPasswordInput"
            inputType="password"
            placeholder="Herhaal wachtwoord"
            width="300px"
            :required="true"
          />
          <DropdownMenu
            v-model:selectedOption="roleInput"
            width="200px"
            :options="roleOptions"
          />
          <SubmitButton inner="Opslaan" type="submit" />
        </form>
      </div>

      <!-- Users Table -->
      <div class="users-table-container">
        <div v-if="loading" class="loading">Laden...</div>
        <div v-else-if="error" class="error">{{ error }}</div>
        <table v-else class="users-table">
          <thead>
            <tr>
              <th>Gebruikersnaam</th>
              <th>E-mailadres</th>
              <th>Rol</th>
              <th>Acties</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users" :key="user.id" class="user-row">
              <td>{{ user.username }}</td>
              <td>{{ user.emailAddress }}</td>
              <td>{{ mapRoleToDisplay(user.roles) }}</td>
              <td class="actions">
                <button class="delete-button" @click="deleteUser(user)" title="Verwijderen">
                  🗑️
                </button>
              </td>
            </tr>
            <tr v-if="users.length === 0">
              <td colspan="4" class="no-users">Geen gebruikers gevonden</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </AppLayout>
</template>

<style scoped>
.admin-container {
  padding: 20px 40px;
  max-width: 1000px;
}

.admin-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.admin-header h1 {
  color: var(--text-color);
  font-size: 1.8rem;
}

.add-button {
  background-color: var(--primary);
  color: white;
  border: none;
  padding: 12px 24px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease;
}

.add-button:hover {
  background-color: darkgreen;
}

.user-form {
  background-color: var(--background);
  border: 1px solid var(--light-gray);
  border-radius: 8px;
  padding: 30px;
  margin-bottom: 30px;
}

.user-form h2 {
  margin-bottom: 20px;
  color: var(--text-color);
}

.user-form form {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 15px;
}

.users-table-container {
  background-color: var(--background);
  border-radius: 8px;
  overflow: hidden;
  border: 1px solid var(--light-gray);
}

.users-table {
  width: 100%;
  border-collapse: collapse;
}

.users-table th {
  background-color: var(--primary);
  color: white;
  padding: 15px;
  text-align: left;
  font-weight: 600;
}

.users-table td {
  padding: 15px;
  border-bottom: 1px solid var(--light-gray);
}

.user-row:nth-child(even) {
  background-color: rgba(76, 175, 80, 0.05);
}

.user-row:hover {
  background-color: rgba(76, 175, 80, 0.1);
}

.actions {
  display: flex;
  gap: 10px;
}

.delete-button {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 1.2rem;
  padding: 5px;
  transition: transform 0.2s ease;
}

.delete-button:hover {
  transform: scale(1.2);
}

.loading,
.error,
.no-users {
  text-align: center;
  padding: 40px;
  color: var(--text-color);
}

.error {
  color: red;
}
</style>
