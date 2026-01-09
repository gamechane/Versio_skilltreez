<template>
  <button class="green-button">
    <img :src="iconSrc" alt="Button Icon" class="button-icon" />
  </button>
</template>

<script>
export default {
  name: 'InformatieKaartButton',
  props: {
    iconSrc: {
      type: String,
      required: true,
    },
  },
};
</script>

<style scoped>
.green-button {
  background-color: var(--primary-light);
  width: 150px; /* Adjust size as needed */
  height: 150px; /* Adjust size as needed */
  border: none;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  padding: 0;

}

.button-icon {
  width: 50%; /* Adjust size as needed */
  height: 50%; /* Adjust size as needed */
}
</style>