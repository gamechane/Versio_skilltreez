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

if (role.startsWith("ROLE_")) {
  role = role.substring(5);
}

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
  width: 200px;
  height: 200px;
  border-radius: 20px;
  background-color: var(--primary-light, #A2E3A1);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05); /* Soft, premium shadow */
  cursor: pointer;
  transition: all 0.3s ease;
  padding: 20px;
  box-sizing: border-box;
}

.dashboardCard:hover {
  background-color: var(--secondary, #96e996);
  transform: translateY(-4px); /* Slight lift on interaction */
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1); /* Deeper shadow on hover */
}

.icon {
  width: 60px;
  height: 60px;
  margin-bottom: 20px;
  object-fit: contain;
}

.text {
  font-size: 18px;
  font-weight: 700;
  color: var(--foreground, #000227);
  text-transform: uppercase;
  letter-spacing: 0.5px;
  text-align: center;
}
</style>
