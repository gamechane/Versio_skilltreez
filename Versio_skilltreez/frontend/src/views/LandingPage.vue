<template>
    <div class="landingPage">
        <div class="navTopBar">
            <img :src="logoImg" alt="logo">
            <div class="buttons">
                <div class="button loginButton"><RouterLink to="/login">Login</RouterLink></div>
                <div class="button registerButton"><RouterLink to="/registration">Register</RouterLink></div>
            </div>
        </div>
        <div class="mainBody">
            <div class="mainInfo">
                <div class="content">
                    <h1>Gamificatie van het onderwijs</h1>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    <RouterLink to="/login" class="loginButton">Login</RouterLink>
                </div>
                <HeaderImage></HeaderImage>
            </div>
            <div class="extraInfo">
                <div class="content">
                    <h2>Wat is Skilltreez</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod  tempor incididunt ut labore et dolore magna aliqua. Tellus orci ac  auctor augue mauris augue. Purus gravida quis blandit turpis cursus.  Ultricies leo integer malesuada nunc vel risus commodo viverra maecenas.</p>
                    <ul>
                        <li>Rubiceerspel</li>
                        <li>Combinatiespel</li>
                        <li>En nog 4 meer!</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import HeaderImage from "@/components/images/HeaderImage.vue";
import logoImg from "@/assets/skilltreez_logo_dark.svg";
</script>

<style scoped>
    .extraInfo > .content {
        margin-top: 120px;
    }

    .extraInfo > .content > p {
        margin-left: 16px;
    }

    .extraInfo > .content > h2 {
        display: flex;
        flex-direction: column;
    }

    .extraInfo > .content > h2::after {
        background-color: var(--primary-light);
        width: 100px;
        border-radius: 5px;
        height: 2px;
        margin-top: 2px;
        content: "";
    }

    .extraInfo > .content > ul {
        display: flex;
        flex-direction: row;
        list-style: none;
        margin: 0;
        padding: 0;
    }

    .extraInfo > .content > ul > li {
        justify-content: center;
        align-items: center;
        padding: 22px 32px;
        background-color: var(--primary-light);
        margin-right: 75px;
        border-radius: 5px;
        color: var(--dark);
        font-size: 15px;
    }


    .landingPage {
        width: 1300px;
        margin: 0 auto;
        padding: 30px;
    }

    .navTopBar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 50px;
    }

    .navTopBar > img {
        height: 23px;
    }

    .navTopBar > .buttons {
        display: flex;
        flex-direction: row;
        align-items: center;
    }

    .navTopBar > .buttons > .button {
        cursor: pointer;
        padding: 16px 24px;
        border-radius: 5px;

        margin-left: 8px;
    }

    .navTopBar > .buttons > .button > a {
        text-decoration: none;
    }

    .navTopBar > .buttons > .button.loginButton > a {
        color: var(--dark);
    }

    .navTopBar > .buttons > .button.registerButton {
        background-color: var(--primary);
    }

    .navTopBar > .buttons > .button.registerButton > a {
        color: #ffffff;
    }

    .mainBody > .mainInfo {
        margin-top: 100px;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }

    .mainBody > .mainInfo > .content > p {
        border-left: 2px solid var(--primary-light);
        margin-left: 8px;
        padding: 8px;
        margin-bottom: 32px;
    }

    .mainBody > .mainInfo > .content > .loginButton {
        text-decoration: none;
        background-color: var(--primary);
        color: #ffffff;
        padding: 12px 32px;
        border-radius: 25px;
    }

    .mainBody > .mainInfo > img {
        width: 445px;
    }

    h1 {
        font-family: var(--header-font-family);
        font-size: 52px;
        margin: 10px 0;
        font-weight: 500;
    }

    p {
        width: 610px;
        color: var(--dark);
        /* font s */
    }
</style>