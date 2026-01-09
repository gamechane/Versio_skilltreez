<script setup>
import { useRouter } from 'vue-router';

import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";

import ImageIcon from "@/assets/ImageIcon.svg";
import CopyIcon from "@/assets/copy.png";
import filmIcon from "@/assets/film.svg";
import TextIcon from "@/assets/TextIcon.svg";
import InformatieKaartButton from "@/components/informatiekaart/InformatieKaartButton.vue";
import DocentGameCreationLayout from "@/components/shared/DocentGameCreationLayout.vue";

const router = useRouter();

const buttons = [
    { iconSrc: TextIcon, path: "/games/create/information-card/text" },
    { iconSrc: ImageIcon, path: "/games/create/information-card/image" },
    { iconSrc: filmIcon, path: "/games/create/information-card/video" },
    { iconSrc: CopyIcon, path: "/games/create/information-card/link" },
];

const handleButtonClick = (path) => {
    if (path) {
        router.push(path);
    }
};
</script>

<template>
    <AppLayout>
        <DocentGameCreationLayout>
            <div class="container">
                <div class="icons">
                    <informatie-kaart-button
                        v-for="(button, index) in buttons"
                        :key="index"
                        :icon-src="button.iconSrc"
                        :path="button.path"
                        @click="handleButtonClick(button.path)"
                    />
                </div>
            </div>
        </DocentGameCreationLayout>
    </AppLayout>
</template>

<style scoped>
.container {
    display: flex;
    align-items: center;
    flex-direction: column;
    gap: 30px;
}

.icons {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 20px;
}
</style>
