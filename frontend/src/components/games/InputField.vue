<script setup>
defineProps({
  placeholder: {
    type: String,
    required: false,
  },
  type: {
    type: String,
    required: false,
  },
  value: {
    type: String,
    required: true,
  },
  onChange: {
    type: Function,
    required: false,
  },
});
</script>

<template>
  <div class="inputWrapper">
    <input
      :placeholder="placeholder"
      :value="value"
      @input="(event) => onChange(event.target.value)"
      :type="type"
    />
  </div>
</template>

<style>
.inputWrapper > input {
  font-size: 16px;
  font-weight: bolder;
  text-align: center;
  padding: 8px 10px;
  width: max(40vw, 200px);
  border: 1px solid black;
  border-radius: 0;
}
</style>