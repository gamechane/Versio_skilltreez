<template>
    <div class="image-uploader">
        <div v-if="imageData" class="image-preview">
            <img :src="imageData" alt="Preview" />
            <button class="remove-button" @click="removeImage">X</button>
        </div>
        <div v-else class="image-placeholder">
            <img src="../../assets/ImageIcon.svg" alt="Placeholder" class="placeholder-icon" />
        </div>
        <div class="file-input-wrapper">
            <input type="file" accept="image/*" @change="onFileChange" ref="fileInput" />
            <button class="file-input-button" @click="triggerFileInput">
                <img src="../../assets/imageSelectorIcon.svg" alt="Select Image" />
            </button>
        </div>
        <input type="text" v-model="imageUrl" @change="onUrlChange" placeholder="Of plak een afbeeldingslink hier" />
    </div>
</template>

<script>
export default {
    data() {
        return {
            imageData: null,
            imageUrl: ''
        };
    },
    methods: {
        onFileChange(e) {
            const file = e.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = (event) => {
                    this.imageData = event.target.result;
                };
                reader.readAsDataURL(file);
            }
        },
        onUrlChange() {
            this.imageData = this.imageUrl;
        },
        removeImage() {
            this.imageData = null;
            this.imageUrl = '';
        },
        triggerFileInput() {
            this.$refs.fileInput.click();
        }
    }
};
</script>

<style scoped>
.image-uploader {
    display: flex;
    flex-direction: column;
    align-items: center;
}
.image-preview {
    position: relative;
    margin-bottom: 10px;
}
.image-preview img {
    max-width: 100%;
    height: auto;
}
.remove-button {
    position: absolute;
    top: 10px;
    right: 10px;
    background-color: red;
    color: white;
    border: none;
    border-radius: 50%;
    width: 30px;
    height: 30px;
    cursor: pointer;
}
.image-placeholder {
    width: 100%;
    height: 200px;
    display: flex;
    justify-content: center;
    align-items: center;

    margin-bottom: 10px;
}
.placeholder-icon {
    width: 270px; /* Vergroot tot 3 keer */
    height: 270px;
    margin-bottom: 20px;
}
.file-input-wrapper {
    position: relative;
    margin: 10px;
}
input[type="file"] {
    display: none;
}
.file-input-button {
    background-color: white;
    border: 3px solid var(--secondary);
    border-radius: 50%;
    padding: 20px; /* Grotere padding */
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
}
.file-input-button img {
    width: 36px;
    height: 36px;
}
input[type="text"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}
</style>
