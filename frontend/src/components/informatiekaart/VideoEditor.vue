<template>
    <div class="video-editor">
        <div v-if="videoUrl" class="video-preview">
            <iframe width="800" height="450" :src="embedYoutubeUrl(videoUrl)" frameborder="0" allowfullscreen></iframe>
            <button class="remove-button" @click="removeVideo">X</button>
        </div>
        <div v-else class="video-placeholder">
            <img src="../../assets/film.svg" alt="Video Placeholder" class="placeholder-icon" />
        </div>
        <input type="text" v-model="videoUrl" placeholder="Plak hier de Youtube-video-URL" />
    </div>
</template>

<script>
export default {
    data() {
        return {
            videoUrl: ''
        };
    },
    watch: {
        videoUrl: 'onUrlChange'
    },
    methods: {
        onUrlChange(newVal) {
            if (this.isValidYoutubeUrl(newVal)) {
                this.videoUrl = newVal.trim(); // Remove any leading/trailing spaces
            } else {
                this.videoUrl = '';
            }
        },
        removeVideo() {
            this.videoUrl = '';
        },
        isValidYoutubeUrl(url) {
            return /^https?:\/\/(?:www\.)?(youtube\.com\/watch\?v=|youtu\.be\/)([a-zA-Z0-9_-]{11})/.test(url);
        },
        embedYoutubeUrl(url) {
            const videoId = this.getVideoIdFromUrl(url);
            return `https://www.youtube.com/embed/${videoId}`;
        },
        getVideoIdFromUrl(url) {
            const regex = /(?:[?&]v=|\/embed\/|\/)([a-zA-Z0-9_-]{11})/;
            const match = url.match(regex);
            return match && match[1];
        }
    }
};
</script>

<style scoped>
.video-editor {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 20px;
}
.video-preview {
    position: relative;
    margin-bottom: 10px;
}
.video-preview iframe {
    width: 800px;
    height: 450px;
}
.remove-button {
    position: absolute;
    top: 10px;
    right: 10px;
    background-color: red;
    color: white;
    border: none;
    border-radius: 50%;
    width: 30px;
    height: 30px;
    cursor: pointer;
}
.video-placeholder {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 10px;
}
.placeholder-icon {
    width: 350px;
    height: 350px;
    margin: 30% 0 20px 0;
}
input[type="text"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}
</style>