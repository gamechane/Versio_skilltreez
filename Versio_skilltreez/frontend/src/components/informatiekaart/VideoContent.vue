<template>
    <div class="video-content">
        <iframe width="560" height="315" :src="embedUrl" frameborder="0" allowfullscreen></iframe>
    </div>
</template>

<script>
export default {
    name: "VideoContent",
    props: {
        videoUrl: {
            type: String,
            required: true
        }
    },
    computed: {
        embedUrl() {

            const videoId = this.videoUrl.split('v=')[1];
            return `https://www.youtube.com/embed/${videoId}`;
        }
    }
};
</script>

<style scoped>
.video-content {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 45vh;
}
</style>