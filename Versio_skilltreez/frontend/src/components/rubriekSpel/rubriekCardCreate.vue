<!--Dit component wordt gebruikt op het rubriek spel aanmaak scherm (/documentatie//wireframes/schermen/1.2.1.1).
Dit component is het zelfde als een spel kaart maar heeft 2 input velden een een optie voor het toevoegen van een image.
Het input veld voor text moet verdwijnen wanneer de gebruiker er voor kiest om een image teo te voegen. (sanne heeft image teoveog systeem kijk samen naar dit)
Verder is er nog een drop down voor de rubriek deze wordt gevuld bij het aanmaken van een kaart in het view component
-->
<script>
import InputField from "@/components/shared/InputField.vue";
import {ref, watch} from "vue";


export default {
    components: {InputField},
    props:{
        title:{
            type: String,
            required: true,
        },
        imgSrc: {
            type: String,
            default: null,
        },
        rubriek:{
            type: String,
            default: "",
            required: true,
        },
        rubrieken:{
            type: Array
        },
    },
    setup(props, {emit}){
        //We maken gebruik van f-model local variables,
        //deze refrence we naar de properties zodat de properties overeen komen met de inputs.
        const localTitle = ref(props.title);
        const localRubriek = ref(props.rubriek);
        const localImgSrc = ref(props.imgSrc);

        const addImage = (e) =>{
            e.stopPropagation()
            //Implement img selection set local localImgSrc
            console.log("Implement img selector")
            localImgSrc.value = "4168"
            console.log(localImgSrc)
        }

        //Watcher om de updates te emiten
        watch(localTitle, (newTitle) =>{
            emit('update:title', newTitle);
        });

        watch(localRubriek, (newRubriek) => {
            emit('update:rubriek', newRubriek);
        })

        watch(localImgSrc, (newImgSrc) => {
            emit('update:imgSrc', newImgSrc);
        })

        return{
            localRubriek,
            localImgSrc,
            localTitle,
            addImage,
        }
    },
}
</script>


<template>
    <div class="card">
        <img v-if="!imgSrc" :src="imgSelector" class="import-img" @click="addImage">
        <img v-else class="card-img" :src="imgSrc">
        <input-field v-if="!imgSrc" class="title-input" v-model="localTitle"></input-field>
        <select v-model="localRubriek" class="rubriek-input">
            <option
                v-for= "rubriek in rubrieken"
                :key="rubriek.id"
                :value="rubriek.rubriek"
            >
                {{rubriek.rubriek}}
            </option>
        </select>
    </div>
</template>

<style scoped>
.card {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
    border-radius: 5px;
    width: 150px;
    height: 200px;
    cursor: pointer;
    background-color: #b0e6a5;
    padding: 10px;
    box-sizing: border-box;
}

.card-img {
    width: 50px;
    height: 50px;
    object-fit: cover;
}
/*.import-img {*/
/*    border: 2px dashed #000;*/
/*    padding: 10px;*/
/*}*/

.title-input {
    width: 100px;
    height: 30px;
    text-align: center;
    font-size: 14px;
    margin-top: 10px;
    background-color: white;
    border: 1px solid #ccc;
    border-radius: 3px;
    box-sizing: border-box;
}

.rubriek-input {
    width: 100px;
    margin-top: 10px;
    text-align: center;
    font-size: 14px;
    background-color: white;
    border: 1px solid #ccc;
    border-radius: 3px;
    box-sizing: border-box;
}
</style>
