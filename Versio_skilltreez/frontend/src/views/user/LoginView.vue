<script setup>
import InputField from "@/components/shared/InputField.vue";
import SubmitButton from "@/components/shared/SubmitButton.vue";
import DropdownMenu from "@/components/shared/DropdownMenu.vue";
import TopNav from "@/components/shared/AppLayout/TopNav.vue";
import TextLink from "@/components/shared/TextLink.vue";
import { ref } from "vue";
import skilltreezlogoImg from "@/assets/skilltreez_logo_dark.svg";
import {AuthenticationService} from "@/services/authentication.js";

const accountOptions = ["Student", "Docent"];

const usernameEmailAddressInput = ref();
const passwordInput = ref();
const accountTypeInput = ref();

function submitLogin() {
  const loginResult = AuthenticationService.login(accountTypeInput.value, usernameEmailAddressInput.value, passwordInput.value);

  if (loginResult === false) {
    const usernameEmailAddressInputField = document.querySelector("#username-email-address-input-field");
    const passwordInputField = document.querySelector("#password-input-field");
    usernameEmailAddressInputField.classList.add("invalid");
    passwordInputField.classList.add("invalid")
  }
}
</script>

<template>
  <div class="login-view">
    <TopNav />
    <div class="login-content">
      <img class="logo" alt="Skilltreez logo" :src="skilltreezlogoImg" />
      <form class="login-form" @submit.prevent="submitLogin()">
        <InputField id="username-email-address-input-field" v-model="usernameEmailAddressInput" inputType="text" placeholder="Gebruikersnaam/Emailadres" width="300px" required="required" min-length="1" max-length="30"/>
        <InputField id="password-input-field" v-model="passwordInput" inputType="password" placeholder="Wachtwoord" width="300px" required="required" min-length="1" max-length="30"/>
        <DropdownMenu v-model:selectedOption="accountTypeInput"  :options="accountOptions" width="200px" />
        <SubmitButton class="login-button" inner="Login"/>
      </form>
      <div class="links">
        <TextLink inner="Speel zonder account" linkTo="/login/game-pin"/>
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
