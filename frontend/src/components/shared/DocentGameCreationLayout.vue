
<template>

    <div class="container">
        <div class="back-button-wrapper">
            <BackButton />
        </div>
        <slot></slot>


    </div>

</template>

<style scoped>
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: var(--secondary-light);
    width: 98%;
    height: 98%;
    position: relative;
    padding: 10px;
}
.back-button-wrapper {
    position: absolute;
    left: 10px;
    top: 50%;
    transform: translateY(-50%);
}
</style>

<script setup>
import BackButton from "@/components/shared/BackButton.vue";
</script>