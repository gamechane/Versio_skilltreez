<template>
      <div class="top-bar">
          <h1 class="title">{{ title }}</h1>
          <p class="description">{{ description }}</p>
      </div>
</template>

<script>
export default {
    props: {
        title: {
          type: String,
          required: true
        },
        description: {
          type: String,
          required: true
        }
    }
}
</script>

<style scoped>

.top-bar {
  position: absolute;
  height: 12vh;
  width: 1610px;
  background: var(--primary);
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}



h1 {
  font-size: 25px;
  margin-bottom: 5px;
}

p {
  margin-top: 5px;
}

</style>