<template>
    <div class="link-content">
        <div class="balk-container">
            <div class="bovenste-balk">
                <img :src="faviconUrl" alt="Favicon">
                <input type="text" v-model="bovensteLinkNaam" placeholder="Geef naam" />
            </div>
            <div class="onderste-balk">
                <input type="text" v-model="ondersteLinkUrl" placeholder="Plak hier de URL" />

            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'LinkEditor',
    data() {
        return {
            bovensteLinkNaam: '',
            ondersteLinkUrl: ''
        };
    },
    computed: {
        faviconUrl() {
            return `https://www.google.com/s2/favicons?domain=${this.extractDomain(this.ondersteLinkUrl)}`;
        }
    },
    methods: {
        extractDomain(url) {
            const link = document.createElement('a');
            link.href = url;
            return link.hostname;
        }
    }
};
</script>

<style scoped>
img {
    margin: 0px 1em 0px 0px;
}
.bovenste-balk img {
    height: 7vh;
}

.bovenste-balk input {
    font-size: 1.5em;
    border: none;
    background: none;
    outline: none;
    width: 100%;
}

.onderste-balk a {
    font-size: 1.2em;
    margin-left: 10px;
}

.onderste-balk img {
    height: 3vh;
}

#linkje {
    text-decoration: none;
    color: black;
}
.balk-container {
    width: 897px;
    height: 140px;
    display: flex;
    flex-direction: column;
}

.bovenste-balk {
    height: 87px;
    background-color: var(--primary-light);
    display: flex;
    align-items: center;
    padding-left: 10px;
}

.onderste-balk {
    display: flex;
    align-items: center;
    padding-left: 10px;
    height: 53px;
    background-color: white;
}

.onderste-balk input {
    font-size: 1.2em;
    border: none;
    background: none;
    outline: none;
    width: 100%;
}
</style>
