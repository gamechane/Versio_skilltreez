<template>
    <div class="rich-text-editor-container">
        <div id="toolbar">
            <button class="ql-bold"></button>
            <button class="ql-italic"></button>
            <button class="ql-underline"></button>
            <button class="ql-align" value=""></button>
            <button class="ql-align" value="center"></button>
            <button class="ql-align" value="right"></button>
            <button class="ql-list" value="bullet"></button>
        </div>
        <div ref="editor" class="editor"></div>
    </div>
</template>

<script>
import Quill from 'quill';
import 'quill/dist/quill.snow.css';

export default {
    props: ['content'],
    data() {
        return {
            editor: null,
        };
    },
    mounted() {
        this.editor = new Quill(this.$refs.editor, {
            theme: 'snow',
            modules: {
                toolbar: '#toolbar'
            }
        });

        if (this.content) {
            this.editor.root.innerHTML = this.content;
        }
    },
    methods: {
        getContent() {
            if (this.editor) {
                return this.editor.root.innerHTML;
            }
            return '';
        }
    }
};
</script>

<style scoped>
.rich-text-editor-container {
    width: 70em;
    max-width: 800px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

#toolbar {
    background-color: #96E996;
    border-bottom: 1px solid #ccc;
    padding: 8px;
}

.editor {
    background-color: #FFFFFF;
    height: 45em;
    overflow-y: auto;
    padding: 8px;
}
</style>
