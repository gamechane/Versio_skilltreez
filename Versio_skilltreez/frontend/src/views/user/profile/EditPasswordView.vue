<script setup>
import skilltreezLogoImg from "@/assets/skilltreez_logo_dark.svg";
import InputField from "@/components/shared/InputField.vue";
import SubmitButton from "@/components/shared/SubmitButton.vue";
import TopNav from "@/components/shared/AppLayout/TopNav.vue";
import {ref} from "vue";
import BackButton from "@/components/shared/BackButton.vue";
import {PasswordUtils} from "@/utils/password-utils.js";
import {UserService} from "@/services/user.js";
import router from "@/router/index.js";

const currentPasswordInput = ref();
const newPasswordInput = ref();
const repeatNewPasswordInput = ref();

function edit() {
  const editPasswordResult = UserService.editPassword(currentPasswordInput.value, newPasswordInput.value);

  if (editPasswordResult) {
    router.go(-1);
  } else {
    alert("Current password is not valid!");
  }
}

function checkPasswordValidity() {
  const currentPasswordInputField = document.querySelector("#current-password-input-field");

  PasswordUtils.showPasswordValidity(currentPasswordInputField);
}

function checkDifferentPassword() {
  const currentPasswordInputField = document.querySelector("#current-password-input-field");
  const newPasswordInputField = document.querySelector("#new-password-input-field");

  PasswordUtils.showDifferentPassword(currentPasswordInputField, newPasswordInputField);
}

function checkPasswordMatch() {
  const newPasswordInputField = document.querySelector("#new-password-input-field");
  const repeatNewPasswordInputField = document.querySelector("#repeat-new-password-input-field");

  PasswordUtils.showRepeatPasswordMatch(newPasswordInputField, repeatNewPasswordInputField);
}

</script>

<template>
  <div class="edit-password-view">
    <TopNav />
    <BackButton />
    <div class="edit-password-content">
      <img class="logo" alt="Skilltreez logo" :src=skilltreezLogoImg>
      <h1 class="text-lg">Wachtwoord wijzigen</h1>
      <form class="edit-password-form" @submit.prevent="edit()">
        <InputField id="current-password-input-field" v-model="currentPasswordInput" @input="checkPasswordValidity(); checkDifferentPassword()" type="password" placeholder="Huidig wachtwoord" width="300px" required="required" min-length="6" max-length="30"></InputField>
        <InputField id="new-password-input-field" v-model="newPasswordInput" @input="checkPasswordMatch(); checkDifferentPassword()" type="password" placeholder="Nieuw wachtwoord" width="300px" required="required" min-length="6" max-length="30"></InputField>
        <InputField id="repeat-new-password-input-field" v-model="repeatNewPasswordInput" @input="checkPasswordMatch()" type="password" placeholder="Herhaal nieuw wachtwoord" width="300px" required="required" min-length="6" max-length="30"></InputField>
        <SubmitButton inner="Wijzigen" class="edit-button"></SubmitButton>
      </form>
    </div>
  </div>

</template>

<style scoped>
.edit-password-content {
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

h1 {
  margin-bottom: 20px;
}

.edit-password-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.edit-button {
  margin-top: 50px;
}
</style>
