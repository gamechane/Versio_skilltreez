<script setup>
defineProps({
  title: {
    type: String,
    required: true,
  },
  icon: {
    type: String,
    required: true,
  },
});
</script>

<template>
  <li class="linkContainer">
    <button class="link">
      <img :src="icon" :alt="`${title}-icon`" class="icon" />
      <p class="label text-base">{{ title }}</p>
    </button>
  </li>
</template>

<style scoped>
.linkContainer {
  list-style: none;
  width: 100%;
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
  padding: 0.5rem 1rem;
  gap: 1rem;
  width: 100%;
  border: none;
  cursor: pointer;
}

.link:hover {
  filter: brightness(0.9);
  transition: filter 0.5s;
}

.label {
  font-weight: bold;
  color: var(--foreground);
}
</style>
