<template>
    <top-nav />
    <GameLayoutComponent2 id="game-layout" title="Gamedidactiek.nl">
        <div id="informatie-kaart-content">
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

import TopNav from "@/components/shared/AppLayout/TopNav.vue";
import GameLayoutComponent2 from "@/components/games/GameLayoutComponent2.vue";
import VerderButton from "@/components/informatiekaart/VerderButton.vue";

import TextContent from "@/components/informatiekaart/TextContent.vue";
import LinkComponent from "@/components/informatiekaart/LinkComponent.vue";
import VideoContent from "@/components/informatiekaart/VideoContent.vue";
import ImageContent from "@/components/informatiekaart/ImageContent.vue";
export default {
    name: "InformatieKaartView",
    components: { TopNav, GameLayoutComponent2, VerderButton },
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
            switch (type) {
                case "text":
                    return {
                        content: "<p><strong>Kopje 1</strong></p><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod sit amet justo id vestibulum. Nulla auctor sit amet ante eget molestie. Mauris ultricies tellus ac nulla tempor, nec rutrum nisi ultricies. Pellentesque nec velit id ante scelerisque tempor. Nam in odio in ligula sollicitudin tincidunt. Vestibulum auctor quam sit amet tortor vestibulum, sed malesuada ex volutpat. Fusce eget lectus vitae est sagittis sollicitudin. Nulla facilisi. Proin euismod, metus id gravida dapibus, mi magna dapibus tellus, nec convallis purus est in nulla. Nulla vitae sapien non sapien finibus rhoncus. Phasellus eget leo tristique, blandit eros non, venenatis leo.</p>"
                            + "<p><strong>Kopje 2</strong></p> <p>Integer auctor nunc non turpis efficitur, sed fermentum felis commodo. Nam a leo id neque facilisis facilisis vel vel urna. Ut ultricies, risus vel euismod lobortis, nunc libero vehicula lectus, ac dignissim neque velit ac magna. Suspendisse potenti. Sed vulputate feugiat ex, nec vestibulum quam suscipit nec. Nullam consectetur lorem sed urna vehicula, eget bibendum elit cursus. Vestibulum sodales consequat nunc id lacinia. Nulla eget magna ut nunc gravida dapibus.</p>"




                    };
                case "link":
                    return {
                        bovensteLinkNaam: 'gamedidactiek.nl',
                        ondersteLinkUrl: 'https://gamedidactiek.nl/page/7905/zelf-aan-de-slag.html'
                    };
                case "video":
                    return {
                        videoUrl: 'https://www.youtube.com/watch?v=K__5jSnoIh0',
                        videoTitle: 'Example Video'
                    };
                case "image":
                    return {
                        imageUrl: 'https://gamedidactiek.nl/dynamic/media/128/images/huisstijl/gamedidactiek_logo.png',
                        imageAlt: 'Example Image'
                    };
                default:
                    return {};
            }
        }
    }
};
</script>

<style scoped>
#game-layout {
    margin-top: 3%;
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
</style>
