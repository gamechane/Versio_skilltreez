<template>
    <div class="image-content">
        <img :src="imageUrl" :alt="imageAlt" />
    </div>
</template>

<script>
export default {
    name: 'ImageContent',
    props: {
        imageUrl: String,
        imageAlt: String
    }
};
</script>

<style scoped>
.image-content {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 45vh;
}
img {
    max-width: 100%;
    max-height: 100%;
    object-fit: contain;
}
</style>