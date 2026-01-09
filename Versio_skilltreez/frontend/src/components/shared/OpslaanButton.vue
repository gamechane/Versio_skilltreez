<template>
    <button @click="handleOpslaan" class="button">
        Opslaan
    </button>
</template>

<script>
export default {
    name: "OpslaanButton",
    methods: {
        handleOpslaan() {
            this.$emit('opslaan');
        }
    }
}
</script>

<style scoped>
.button {
    width: 210px;
    height: 64px;
    margin: 0 1em;
    background: #24A824;
    border: none;
    cursor: pointer;
    font-size: 24px;
    color: white;
    font-weight: bold;

}

.button:hover {
    background-color: lightblue;
}
</style>