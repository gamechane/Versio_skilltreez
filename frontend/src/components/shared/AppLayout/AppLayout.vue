<script setup>
import TopNav from "./TopNav.vue";
import SideBar from "./SideBar.vue";
import BackButton from "@/components/shared/BackButton.vue";

const props = defineProps({
  showBackButton: {
    type: Boolean,
    default: true,
  }
})
</script>

<template>
  <TopNav />
  <SideBar>
     <BackButton v-if="props.showBackButton"/>
    <slot></slot>
  </SideBar>
</template>

<style scoped></style>
