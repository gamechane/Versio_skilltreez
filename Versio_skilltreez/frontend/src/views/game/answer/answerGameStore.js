import { reactive } from 'vue'

export const answerGameStore = reactive({
    questions: [],
    getQuestions: function() {
        return Object.values(this.questions);
    },
})