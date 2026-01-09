<template>
  <div class="card">
    <div  class="card-header"> {{title}}</div>
  </div>
</template>

<script>
export default {
  props:{
    title:{
      type: String
    },
    imgSrc:{
      type: String
    },
    set:{
      type: String,
      required: true
    }
  },
  methods: {
    //sleep code van sly.
  }
}
</script>


<style scoped>
.card{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 5px;
  width: 150px;
  height: 200px;
  cursor: pointer;
  background-color: var(--primary-light);
}


</style>
