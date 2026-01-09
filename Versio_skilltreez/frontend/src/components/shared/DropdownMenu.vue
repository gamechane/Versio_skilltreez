<template>
  <div class="dropdown" :style="{ width: width }">
    <div class="info option" tabindex="0">
      {{ selectedOption }}
      <SorteerPijl class="img" />
    </div>
    <div class="content">
      <div class="option" v-for="option in unselectedOptions" :key="option" @click="selectOption(option)" @keyup.enter="selectOption(option)" tabindex="0">
        {{ option }}
      </div>
    </div>
  </div>
</template>

<script setup>
  import SorteerPijl from "@/components/images/SorteerPijl.vue";
  import {computed, ref} from "vue";

  const props = defineProps({
    options: {
      type: Array,
      required: true
    },
    width: {
      type: String,
      required: true
    }
  });

  const selectedOption = defineModel('selectedOption',
      { default: "No items..." }
  );

  selectedOption.value = props.options[0];

  const unselectedOptions = computed(() => getUnselectedOptions());

  function getUnselectedOptions() {
    return props.options.filter(option => option !== selectedOption.value);
  }

  function selectOption(option) {
    selectedOption.value = option;
  }
</script>

<style scoped>
  .dropdown {
    position: relative;
    display: inline-block;

    background-color: var(--background);
    cursor: default;

    border: 1px solid var(--light-gray);
    border-radius: 2px;

    user-select: none;
  }

  .info {
    padding: 12px 16px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .img {
    width: 12px;
    transition: all .15s ease;
  }

  .content {
    display: none;
    position: absolute;
    flex-direction: column;
    width: inherit;
    z-index: 1;
    border-radius: 2px;
    border: 1px solid var(--light-gray);
    border-top: 0;
    /* :D */
    transform: translateX(-1px);
  }

  .option {
    padding: 12px 16px;
    /* 32px is the padding */
    width: calc(100% - 32px);
  }

  .option:hover, .option:focus {
    background-color: var(--primary);
    cursor: pointer;
  }

  .dropdown:hover .content, .dropdown:focus-within .content {
    display: flex;
  }

  .dropdown:hover .img, .dropdown:focus-within .img {
    transform: rotate(-180deg);
  }
</style>
