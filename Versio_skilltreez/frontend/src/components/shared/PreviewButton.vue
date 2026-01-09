<template>
    <button class="button">
        Preview
    </button>
</template>

<script>
export default {
    name: "PreviewButton"
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