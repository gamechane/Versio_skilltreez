<script setup>
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";

import logoImg from "@/assets/skilltreez_logo_dark.svg";

import gamesIcon from "@/assets/games.png";
import skilltreesIcon from "@/assets/skilltrees.png";
import rankingsIcon from "@/assets/rankings.png";
import profileIcon from "@/assets/profile.png";
import DashboardCard from "@/components/dashboard/dashboardCard.vue";
import {JwtUtils} from "@/utils/jwt-utils.js";
import {DataManager} from "@/services/store.js";

const dashboardCards = [
  {
    icon: gamesIcon,
    buttonText: "Spellen",
    roleLinks: {
      adminLink: "/games",
      gameMasterLink: "/games",
    },
    allowedRoles: ["ADMIN", "GAME_MASTER"],
  },
  {
    icon: skilltreesIcon,
    buttonText: "Skilltrees",
    roleLinks: {
      adminLink: "skill-tree-overview-teacher",
      gameMasterLink: "/skill-tree-overview-teacher",
      studentLink: "/skill-tree-overview-student",
    },
    allowedRoles: ["ADMIN", "GAME_MASTER", "STUDENT"],
  },
  {
    icon: rankingsIcon,
    buttonText: "Resultaten",
    roleLinks: {
      adminLink: "/results",
      gameMasterLink: "/results",
    },
    allowedRoles: ["ADMIN", "GAME_MASTER"],
  },
  {
    icon: profileIcon,
    buttonText: "Profiel",
    roleLinks: {
      adminLink: "/profile-teacher",
      gameMasterLink: "/profile-teacher",
      studentLink: "/profile-student",
    },
    allowedRoles: ["ADMIN", "GAME_MASTER", "STUDENT"],
  },
  {
    icon: profileIcon,
    buttonText: "Admin",
    roleLinks: {
      adminLink: "/admin",
    },
    allowedRoles: ["ADMIN"],
  },
]

const token = DataManager.token.get();
let role = JwtUtils.getRole(token);

if (role.startsWith("ROLE_")) {
  role = role.substring(5);
}

if (JwtUtils.isGameMaster(token)) {
  role = "GAME_MASTER";
}

</script>

<template>
  <AppLayout>
    <div class="container">
      <img alt="Skilltreez logo" class="logo" :src="logoImg" />
      <div class="icons">
        <template v-for="dashboardCard in dashboardCards">
          <dashboardCard v-if="dashboardCard.allowedRoles.includes(role)" :key="dashboardCard.id" :icon-src="dashboardCard.icon" :button-text="dashboardCard.buttonText" :roleLinks="dashboardCard.roleLinks"></dashboardCard>
        </template>

      </div>
    </div>
  </AppLayout>
</template>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 100px);
  padding: 40px 20px;
  flex-direction: column;
  gap: 40px;
  box-sizing: border-box;
}

.logo {
  height: 80px;
  object-fit: contain;
  margin-bottom: 10px;
}

.icons {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 40px;
}

@media (max-width: 600px) {
  .icons {
    grid-template-columns: 1fr;
    gap: 20px;
  }
}
</style>
