<template>
    <div class="text-content" v-html="content"></div>
</template>

<script>
export default {
    name: "TextContent",
    props: {
        content: {
            type: String,
            required: true
        }
    }
};
</script>

<style scoped>
.text-content {
    overflow-y: auto;
    max-height: calc(88vh - 12vh - 200px);
    font-size: 1.2em;
}
</style>