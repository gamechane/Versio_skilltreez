<template>
    <button class="button">
        Verder
    </button>
</template>

<script>
export default {
    name: "VerderButton"
}
</script>

<style scoped>
.button {
    width: 210px;
    height: 64px;

    background: var(--primary-light);
    border: none;
    cursor: pointer;
    font-size: 24px;
    color: black;
    font-weight: bold;

}

.button:hover {
    background-color: lightblue;
}
</style>