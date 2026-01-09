<script setup>
  const props = defineProps({
    inputType: String,
    required: {
      type: Boolean,
      default: false,
    },
    placeholder: String,
    width: String,
    minLength: String,
    maxLength: String,
  });

  const value = defineModel({ type: String });
</script>

<template>
  <input class="inputField" :type="inputType" :placeholder="placeholder" :style="{ width: width }" v-model="value" :required="required" :minlength="minLength" :maxlength="maxLength">
</template>

<style scoped>
.inputField {
  text-align: center;
  padding: 1.1rem 1.8rem;
  font: var(--font-family);
  border-radius: 2px;
  border: 1px solid var(--light-gray);
}

.invalid {
  border-color: red;
}

.invalid:focus {
  outline-color: red;
}

.valid {
  border-color: var(--primary);
}

.valid:focus {
  outline-color: var(--primary);
}
</style>
