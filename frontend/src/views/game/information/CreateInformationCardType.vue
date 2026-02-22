<template>
    <AppLayout>
        <DocentGameCreationLayout>
            <div id="informatie-kaart-content">
                <component :is="currentComponent" v-bind="currentComponentProps" ref="currentComponentRef"/>
                <div class="button-container">
                    <OpslaanButton @opslaan="handleOpslaan"/>
                    <PreviewButton @click="handlePreview"/>
                </div>
            </div>
        </DocentGameCreationLayout>
    </AppLayout>
</template>

<script>
import GameLayoutComponent2 from "@/components/games/GameLayoutComponent2.vue";
import TopNav from "@/components/shared/AppLayout/TopNav.vue";
import LinkComponent from "@/components/informatiekaart/LinkComponent.vue";
import TextEditor from "@/components/informatiekaart/TextEditor.vue";
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import OpslaanButton from "@/components/shared/OpslaanButton.vue";
import ImageEditor from "@/components/informatiekaart/ImageEditor.vue";
import PreviewButton from "@/components/shared/PreviewButton.vue";
import VideoEditor from "@/components/informatiekaart/VideoEditor.vue";
import LinkEditor from "@/components/informatiekaart/LinkEditor.vue";
import DocentGameCreationLayout from "@/components/shared/DocentGameCreationLayout.vue";

export default {
    name: "InformatieKaartCreatieView",
    components: {
        DocentGameCreationLayout,
        PreviewButton,
        OpslaanButton,
        VideoEditor,
        AppLayout,
        LinkComponent,
        TextEditor,
        LinkEditor,
        TopNav,
        ImageEditor,
        GameLayoutComponent2
    },
    computed: {
        currentComponent() {
            const type = this.$route.params.type;
            switch (type) {
                case "text":
                    return TextEditor;
                case "link":
                    return LinkEditor;
                case "video":
                    return VideoEditor;
                case "image":
                    return ImageEditor;
                default:
                    return null;
            }
        },
        currentComponentProps() {
            const type = this.$route.params.type;
            const savedData = localStorage.getItem(`informatiekaart-${type}`);
            if (savedData) {
                switch (type) {
                    case "text":
                        return {content: savedData};
                    case "link":
                        const linkData = JSON.parse(savedData);
                        return {
                            bovensteLinkNaam: linkData.bovensteLinkNaam,
                            ondersteLinkUrl: linkData.ondersteLinkUrl
                        };
                    case "video":
                        return {videoUrl: savedData};
                    case "image":
                        return {imageUrl: savedData};
                    default:
                        return {};
                }
            } else {
                switch (type) {
                    case "text":
                        return {content: ""};
                    case "link":
                        return {bovensteLinkNaam: "", ondersteLinkUrl: ""};
                    case "video":
                        return {videoUrl: ""};
                    case "image":
                        return {imageUrl: ""};
                    default:
                        return {};
                }
            }
        }
    },
    methods: {
        handleOpslaan() {
            const currentComponentInstance = this.$refs.currentComponentRef;

            if (currentComponentInstance) {
                switch (this.$route.params.type) {
                    case "text":
                        this.saveTextEditorData(currentComponentInstance);
                        break;
                    case "link":
                        this.saveLinkEditorData(currentComponentInstance);
                        break;
                    case "video":
                        this.saveVideoEditorData(currentComponentInstance);
                        break;
                    case "image":
                        this.saveImageEditorData(currentComponentInstance);
                        break;
                    default:
                        console.warn("Unexpected component type");
                        break;
                }
                alert('Gegevens zijn opgeslagen');
            }
        },
        saveTextEditorData(componentInstance) {
            const content = componentInstance.getContent();
            localStorage.setItem('informatiekaart-text', content);
            console.log("Text Editor Content:", content);
        },
        saveLinkEditorData(componentInstance) {
            const data = {
                bovensteLinkNaam: componentInstance.bovensteLinkNaam,
                ondersteLinkUrl: componentInstance.ondersteLinkUrl
            };
            localStorage.setItem('informatiekaart-link', JSON.stringify(data));
            console.log("Link Editor Data:", data);
        },
        saveVideoEditorData(componentInstance) {
            const videoUrl = componentInstance.videoUrl;
            localStorage.setItem('informatiekaart-video', videoUrl);
            console.log("Video Editor Data:", videoUrl);
        },
        saveImageEditorData(componentInstance) {
            const imageUrl = componentInstance.imageUrl;
            localStorage.setItem('informatiekaart-image', imageUrl);
            console.log("Image Editor Data:", imageUrl);
        },
        getData() {
            const currentComponentInstance = this.$refs.currentComponentRef;

            if (currentComponentInstance) {
                let content = '';
                switch (this.$route.params.type) {
                    case "text":
                        content = encodeURIComponent(currentComponentInstance.getContent());
                        break;
                    case "link":
                        content = encodeURIComponent(JSON.stringify({
                            bovensteLinkNaam: currentComponentInstance.bovensteLinkNaam,
                            ondersteLinkUrl: currentComponentInstance.ondersteLinkUrl
                        }));
                        break;
                    case "video":
                        content = encodeURIComponent(JSON.stringify({videoUrl: currentComponentInstance.videoUrl}));
                        break;
                    case "image":
                        content = encodeURIComponent(JSON.stringify({imageUrl: currentComponentInstance.imageUrl}));
                        break;
                    default:
                        console.warn("Unexpected component type");
                        break;
                }
                this.$router.push(`/games/information-card/preview/${this.$route.params.type}/${content}`);
            }
        },
        handlePreview() {
            this.handleOpslaan();
            this.getData();
        }
    }
};
</script>

<style scoped>
#informatie-kaart-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 0 20% 1% 20%;
    padding: 20px 20px 10px 20px;
}

.button-container {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-top: 20px;
    padding: 10px;
}
</style>
