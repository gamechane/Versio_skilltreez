<template>
    <div class="link-content">
        <div class="balk-container">
            <div class="bovenste-balk">
                <img :src="faviconUrl" alt="Favicon">
                <p>{{ bovensteLinkNaam }}</p>
            </div>
            <div class="onderste-balk">
                <div class="kopieer-logo" @click="copyLink">
                    <img src="../../assets/copy.png" alt="Copy Icon" />
                </div>
                <a :href="ondersteLinkUrl" id="linkje">{{ ondersteLinkUrl }}</a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'LinkComponent',
    props: {
        bovensteLinkNaam: String,
        ondersteLinkUrl: String
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
        },
        copyLink() {
            var linkUrl = this.ondersteLinkUrl;
            var input = document.createElement('input');
            input.setAttribute('value', linkUrl);
            document.body.appendChild(input);
            input.select();
            input.setSelectionRange(0, 99999);
            document.execCommand('copy');
            document.body.removeChild(input);
            alert("Link gekopieerd naar klembord!");
        }
    }
};
</script>

<style scoped>
img{
    margin: 0px 1em 0px 0px;
}
.bovenste-balk img{
    height: 7vh;

}

.bovenste-balk p {
    font-size: 1.5em;
}

.onderste-balk a{
    font-size: 1.2em;
}

.onderste-balk img{
    height: 3vh;
    
}

#linkje{
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


</style>