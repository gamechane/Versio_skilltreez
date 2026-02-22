<!--Dit is een component die de kaarten ontvangt wanneer ze gespleept worden tijden het spel procces -->
<template>
  <div class="receiver-container">
    <div class="receiver"></div>
    <div class="title">{{rubriek}} </div>
  </div>
</template>

<script>

export default {
    props:{
        rubriek:{
            type: String,
            required: true,
        }
    }
}
</script>

<style scoped>
.receiver{
  border-width: 1px;
  border-color: black;
  border-style: solid;
  border-radius: 5px;
  width: 150px;
  height: 200px;
  background-color: white;
}
.receiver-container{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.title{
  font-size: var(--font-size);
  font-family: var(--font-family);
}

</style>
