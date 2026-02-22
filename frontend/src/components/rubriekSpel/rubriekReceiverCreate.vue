<!--Dit is het component wat moet dienen als het plusje dat een nieuwe rubriek kaart aanmaakt in het rubriekspel aanmaak scherm-->
<template>
  <div><img src="@/assets/plus-icon.png" alt="plus icon for "></div>
</template>

<script>
export default {
  name: "RubriekCreateCardButton",
  methods: {
    handleClick() {

      createCard();
    },
    createCard() {
      //cards.push(RubriekCreateCard card)
      //tell the view component to make a new card?
    }
  }
}

</script>

<style scoped>

</style>
