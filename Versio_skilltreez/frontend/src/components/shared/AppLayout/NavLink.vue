<script setup>
import { RouterLink, useRoute } from "vue-router";
import { computed } from "vue";

const props = defineProps({
  to: {
    type: String,
    required: true,
  },
  title: {
    type: String,
    required: true,
  },
  icon: {
    type: String,
    required: true,
  },
});

const currentRoute = useRoute();
let isActive = computed(() => currentRoute.path === props.to);
</script>

<template>
  <li class="linkContainer">
    <RouterLink class="link" :to="to" :class="isActive ? 'active' : ''">
      <img :src="icon" :alt="`${title}-icon`" class="icon" />
      <p class="label text-base">{{ title }}</p>
    </RouterLink>
  </li>
</template>

<style scoped>
.linkContainer {
  list-style: none;
}

.icon {
  width: 1.3rem;
  height: 1.3rem;
}

.link {
  background-color: var(--secondary);
  text-decoration: none;
  list-style: none;
  display: flex;
  align-items: center;
  padding: 1rem 1rem;
  gap: 1rem;
}

.link:hover:not(.active) {
  filter: brightness(0.9);
  transition: filter 0.5s;
}

.label {
  font-weight: bold;
  color: var(--foreground);
}

.active {
  background-color: var(--primary);
  cursor: default;
}
</style>
