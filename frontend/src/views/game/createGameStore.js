import { reactive } from 'vue'

export const createGameStore = reactive({
    selectedGame:"",
    title: "",
    description: "",
    settings: {},
})