<script>
  import { store } from "../../store.js";

  export default {
  props: {
  buttonText: {
  type: String,
  required: true,
},
},
  setup() {
  const handleClick = () => {
  const selectedCard = store.selectedCard;
  console.log(`Geselecteerde kaart: ${selectedCard}`);
  // Voer hier de logica uit om de geselecteerde kaart te verwerken
};

  return {
  handleClick,
};
},
};
</script>

<template>
  <button @click="handleClick">{{ buttonText }}</button>
</template>

<style scoped>
button {
  background-color: var(--primary-light);
  border: none;
  color: black;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 2px;
  padding: 10px 24px;
  font-weight: bold;
}

button:hover {
  background-color: var(--primary-dark); /* Verander de achtergrondkleur wanneer de muis over de knop beweegt */
}
</style>