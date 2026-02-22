<script setup>
import NavLink from "./NavLink.vue";
import NavButton from "./NavButton.vue";

import dashboardIcon from "@/assets/dashboard.png";
import gamesIcon from "@/assets/games.png";
import skilltreesIcon from "@/assets/skilltrees.png";
import rankingsIcon from "@/assets/rankings.png";
import profileIcon from "@/assets/profile.png";

import moreInfoIcon from "@/assets/moreinfo.png";
import logoutIcon from "@/assets/logout.png";
import {AuthenticationService} from "@/services/authentication.js";
import {JwtUtils} from "@/utils/jwt-utils.js";
import {DataManager} from "@/services/store.js";

const routes = [
  {
    title: "Dashboard",
    icon: dashboardIcon,
    adminLink: "/dashboard",
    gameMasterLink: "/dashboard",
    studentLink: "/dashboard",
    allowedRoles: ["ADMIN", "GAME_MASTER", "STUDENT"],
  },
  {
    title: "Spellen",
    icon: gamesIcon,
    adminLink: "/games",
    gameMasterLink: "/games",
    allowedRoles: ["ADMIN", "GAME_MASTER"],
  },
  {
    title: "Skill trees",
    icon: skilltreesIcon,
    adminLink: "/skill-tree-overview-teacher",
    gameMasterLink: "/skill-tree-overview-teacher",
    studentLink: "/skill-tree-overview-student",
    allowedRoles: ["ADMIN", "GAME_MASTER", "STUDENT"],
  },
  {
    title: "Resultaten",
    icon: rankingsIcon,
    adminLink: "/results",
    gameMasterLink: "/results",
    allowedRoles: ["ADMIN", "GAME_MASTER",],
  },
  {
    title: "Profiel",
    icon: profileIcon,
    adminLink: "/profile-teacher",
    gameMasterLink: "/profile-teacher",
    studentLink: "/profile-student",
    allowedRoles: ["ADMIN", "GAME_MASTER", "STUDENT"],
  },
  {
    to: "/more-information",
    title: "Meer info",
    icon: moreInfoIcon,
  },
];

const buttons = [
  {
    onClick: AuthenticationService.logout,
    title: "Uitloggen",
    icon: logoutIcon,
  },
];

const token = DataManager.token.get();
let role = JwtUtils.getRole(token);

if (JwtUtils.isGameMaster(token)) {
  role = "GAME_MASTER";
}

function finalLink(route) {
  let link;

  switch(role) {
    case "ADMIN":
      if (route.allowedRoles.includes(role)) {
        link = route.adminLink;
      }
      break;
    case "GAME_MASTER":
      if (route.allowedRoles.includes(role)) {
        link = route.adminLink;
      }
      break;
    case "STUDENT":
      if (route.allowedRoles.includes(role)) {
        link = route.studentLink;
      }
      break;
    default: link = "/";
  }

  return link;
}
</script>

<template>
  <div class="container">
    <aside class="sidebar">
      <div class="link-lists-container">
        <ul class="nav-items top-links" >
          <template v-for="route in routes.slice(0, -1)">
            <NavLink  v-if="route.allowedRoles.includes(role)" :key="route.id" :to="finalLink(route)" :icon="route.icon" :title="route.title"/>
          </template>
        </ul>
        <ul class="nav-items bottom-links">
          <NavLink v-for="route in routes.slice(-1)" :key="route.id" v-bind="route" :to="route.to"/>
        </ul>
      </div>
      <ul class="nav-items">
        <NavButton v-for="button in buttons" :key="button.id" v-bind="button" />
      </ul>
    </aside>
    <main class="main">
      <slot></slot>
    </main>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  height: calc(100vh - 40px);
  position: fixed;
  top: 40px;
  left: 0;
}

.main {
  flex: 1;
  width: calc(100vw - 180px);
  height: calc(100vh - 40px);
  overflow-y: auto;
  position: relative;
  padding: 10px 20px;
}

.sidebar {
  background-color: var(--secondary);
  width: 150px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: calc(100vh - 40px);
}

.link-lists-container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}

.nav-items {
  padding: 0;
  margin: 0;
  list-style: none;
  display: flex;
  flex-direction: column;
}
</style>
