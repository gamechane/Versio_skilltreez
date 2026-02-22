<template>
    <div class="dashboardCard" @click="navigate()">
        <img :src="iconSrc" alt="Icon" class="icon" />
        <span class="text">{{ buttonText }}</span>
    </div>
</template>

<script setup>
import router from "@/router/index.js";
import {DataManager} from "@/services/store.js";
import {JwtUtils} from "@/utils/jwt-utils.js";

const props = defineProps({
  iconSrc: {
    type: String,
    required: true
  },
  buttonText: {
    type: String,
    required: true
  },
  roleLinks: {
    type: Object,
    required: true,
    adminLink: {
      type: String,
    },
    gameMasterLink: {
      type: String,
    },
    studentLink: {
      type: String,
    },
    default(rawProps) {
      return {
        adminLink: "/",
        gameMasterLink: "/",
        studentLink: "/",
      }
    },
  },
});

const token = DataManager.token.get();
let role = JwtUtils.getRole(token);

if (JwtUtils.isGameMaster(token)) {
  role = "GAME_MASTER";
}

function navigate() {
  let link;

  switch (role) {
    case "ADMIN":
      link = props.roleLinks.adminLink;
      break;
    case "GAME_MASTER":
      link = props.roleLinks.gameMasterLink;
      break;
    case "STUDENT":
      link = props.roleLinks.studentLink;
      break;
    default: link = "/";
  }

  router.push(link);
}
</script>

<style scoped>
.dashboardCard {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100px;
  height: 150px;
  border-radius: 10%;
  background-color: var(--primary-light);
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.dashboardCard:hover {
  background-color: lightblue;
}

.icon {
  width: 40px;
  height: 40px;
  margin-bottom: 10px;
}

.text {
  font-size: 14px;
  font-weight: bold;
}
</style>
