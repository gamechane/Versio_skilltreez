<script setup>
import skilltreezLogoImg from "@/assets/skilltreez_logo_dark.svg";
import InputField from "@/components/shared/InputField.vue";
import DropdownMenu from "@/components/shared/DropdownMenu.vue";
import SubmitButton from "@/components/shared/SubmitButton.vue";
import TextLink from "@/components/shared/TextLink.vue";
import TopNav from "@/components/shared/AppLayout/TopNav.vue";
import {ref} from "vue";
import {PasswordUtils} from "@/utils/password-utils.js";
import {EmailAddressUtils} from "@/utils/email-address-utils.js";
import {UsernameUtils} from "@/utils/username-utils.js";
import {UserService} from "@/services/user.js";

const accountOptions = ["Student", "Docent"];

const usernameInput = ref();
const emailAddressInput = ref();
const passwordInput = ref();
const repeatPasswordInput = ref();
const accountTypeInput = ref();

function checkUsernameValidity() {
  const usernameInputField = document.querySelector("#username-input-field")

  UsernameUtils.showUsernameValidity(usernameInputField);
}

function checkEmailAdressValidity() {
  const emailAddressInputField = document.querySelector("#email-address-input-field");

  EmailAddressUtils.showEmailAddressValidity(emailAddressInputField);
}

function checkPasswordValidity() {
  const passwordInputField = document.querySelector('#password-input-field');

  PasswordUtils.showPasswordValidity(passwordInputField);
}

function checkPasswordMatch() {
  const passwordInputField = document.querySelector("#password-input-field");
  const repeatPasswordInputField = document.querySelector("#repeat-password-input-field");

  PasswordUtils.showRepeatPasswordMatch(passwordInputField, repeatPasswordInputField);
}

function register() {
  UserService.register(accountTypeInput.value, usernameInput.value, emailAddressInput.value, passwordInput.value);
}
</script>

<template>
  <div class="registration-view">
    <TopNav />
    <div class="registration-content">
      <img class="logo" alt="Skilltreez logo" :src=skilltreezLogoImg>
      <form class="registration-form" @submit.prevent="register()">
        <InputField id="username-input-field" v-model="usernameInput" @input="checkUsernameValidity()" type="text" placeholder="Gebruikersnaam" width="300px" required="required" min-length="6" max-length="30"></InputField>
        <InputField id="email-address-input-field" v-model="emailAddressInput" @input="checkEmailAdressValidity()" type="email" placeholder="E-mailadres" width="300px" required="required" min-length="6" max-length="30"></InputField>
        <InputField id="password-input-field" v-model="passwordInput" @input="checkPasswordValidity(); checkPasswordMatch()" type="password" placeholder="Wachtwoord" width="300px" required="required" min-length="6" max-length="30"></InputField>
        <InputField id="repeat-password-input-field" v-model="repeatPasswordInput" @input="checkPasswordMatch()" type="password" placeholder="Herhaal wachtwoord" width="300px" required="required" min-length="6" max-length="30"></InputField>
        <DropdownMenu v-model:selectedOption="accountTypeInput" width="200px" :options=accountOptions></DropdownMenu>
        <SubmitButton inner="Registreren" class="register-button"></SubmitButton>
      </form>
      <div class="links">
        <TextLink link-to="/login/game-pin" inner="Speel zonder account"></TextLink>
        <TextLink link-to="/login" inner="Ik heb al een account"></TextLink>
      </div>
    </div>
  </div>
</template>

<style scoped>
.registration-content {
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

.registration-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.register-button {
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
