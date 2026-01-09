<template>
    <GameLayoutComponent2 id="game-layout" title="Gamedidactiek.nl">
        <div id="informatie-kaart-content">
            <button class="close-button" @click="handleClose">X</button>
            <component
                :is="currentComponent"
                v-bind="currentComponentProps"
            ></component>
            <div class="button-container">
                <verder-button id="button"></verder-button>
            </div>
        </div>
    </GameLayoutComponent2>
</template>

<script>
import GameLayoutComponent2 from "@/components/games/GameLayoutComponent2.vue";
import VerderButton from "@/components/informatiekaart/VerderButton.vue";
import TextContent from "@/components/informatiekaart/TextContent.vue";
import LinkComponent from "@/components/informatiekaart/LinkComponent.vue";
import VideoContent from "@/components/informatiekaart/VideoContent.vue";
import ImageContent from "@/components/informatiekaart/ImageContent.vue";

export default {
    name: "PreviewInformatieKaartView",
    components: { GameLayoutComponent2, VerderButton },
    computed: {
        currentComponent() {
            const type = this.$route.params.type;
            switch (type) {
                case "text":
                    return TextContent;
                case "link":
                    return LinkComponent;
                case "video":
                    return VideoContent;
                case "image":
                    return ImageContent;
                default:
                    return null;
            }
        },
        currentComponentProps() {
            const type = this.$route.params.type;
            const content = decodeURIComponent(this.$route.params.content);

            switch (type) {
                case "text":
                    console.log({content});
                    return { content };
                case "link":
                    return JSON.parse(content);
                case "video":
                    return JSON.parse(content);
                case "image":
                    return JSON.parse(content);
                default:
                    return {};
            }
        }
    },
    methods: {
        handleClose() {
            this.$router.go(-1); // Navigate back to the previous page
        }
    }
};
</script>

<style scoped>
#game-layout {
    margin-top: 1%;
    display: flex;
    justify-content: center;

}

#informatie-kaart-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 0 20% 1% 20%;
    padding: 20px 20px 10px 20px;
}

.button-container {
    margin-top: 40px;
}

.close-button {
    position: absolute;
    top: 10px;
    right: 10px;
    background: none;
    border: none;
    font-size: 20px;
    cursor: pointer;
}
</style>
