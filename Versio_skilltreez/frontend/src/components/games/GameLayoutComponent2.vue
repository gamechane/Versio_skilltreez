<template>
    <div>
        <div class="content">
            <div class="top-bar">
                <h1 class="title">{{ title }}</h1>
                <p class="description">{{ description }}</p>
            </div>
            <div id="game-content">
                <slot ></slot>
            </div>

        </div>
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
    height: 15vh;
    width: 90vw;
    background: var(--primary);

}
#game-content{
    display: flex;
    justify-content: center;
    margin-top: 150px;
}


.title {
    margin-top: 13px;
    display: flex;
    justify-content: center;

}

.description {
  margin-bottom:5px;
  justify-content: center;
  display: flex;
}

/* Vlak eronder */
.content {
    position: absolute;
    width: 90vw;
    height: 88vh;
    background: var(--secondary-light);
    bottom: 0;
    left: 50%;
    transform: translateX(-50%);
    overflow-y: auto;
    overflow-x: hidden;

}

</style>
