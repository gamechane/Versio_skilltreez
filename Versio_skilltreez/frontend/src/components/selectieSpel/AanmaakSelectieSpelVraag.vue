<template>
  <InputField class="input" type="text" placeholder="Voer uw vraag in:" :value="modelValue" @input="$emit('update:modelValue', $event.target.value)"></InputField>
</template>
<style scoped>

.input /deep/ ::placeholder {
  font-weight: bold;
  color: #000227;
}

.input /deep/ input{
  transform: scale(1.4);
}

</style>
<script setup>
import InputField from "@/components/games/InputField.vue";

defineProps({
  modelValue: String
});
</script>

