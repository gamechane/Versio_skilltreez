<script setup>
import InputField from "@/components/shared/InputField.vue";
import SubmitButton from "@/components/shared/SubmitButton.vue";
import TopNav from "@/components/shared/AppLayout/TopNav.vue";
import TextLink from "@/components/shared/TextLink.vue";
import { ref } from "vue";
import skilltreezLogoImg from "@/assets/skilltreez_logo_dark.svg";
import {AuthenticationService} from "@/services/authentication.js";

const gamePinInput = ref();

function submitGamePin() {
  const loginResult = AuthenticationService.loginGamePin(gamePinInput.value);

  loginResult.then(result => {
    if (!result) {
      const gamePinInputField = document.querySelector("#game-pin-input-field");
      gamePinInputField.classList.add("invalid");
    }
  });
}
</script>

<template>
  <div class="login-game-pin-view">
    <TopNav />
    <div class="login-content">
      <img class="logo" alt="Skilltreez logo" :src="skilltreezLogoImg" />
      <form class="login-form" @submit.prevent="submitGamePin()">
        <InputField id="game-pin-input-field" v-model="gamePinInput" inputType="text" placeholder="Game Pin" width="300px" required="required" min-length="1" max-length="30"/>
        <SubmitButton inner="Volgende" class="login-button"/>
      </form>
      <div class="links">
        <TextLink inner="Inloggen" linkTo="/login"/>
        <TextLink inner="Account aanmaken" linkTo="/registration"/>
      </div>
    </div>
  </div>
</template>

<style scoped>
  .login-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 50px;
  }

  .logo {
    height: 50px;
    object-fit: contain;
    margin-bottom: 30px;
  }

  .login-form {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
  }

  .login-button {
    margin-top: 50px;
  }

  .links {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 30px;
    gap: 0.3rem;
  }
</style>
