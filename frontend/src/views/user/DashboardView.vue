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
]

const token = DataManager.token.get();
let role = JwtUtils.getRole(token);

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
  justify-content: start;
  align-items: center;
  height: 60%;
  flex-direction: column;
  gap: 30px;
}

.logo {
  height: 50px;
  object-fit: contain;
}

.icons {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}
</style>
