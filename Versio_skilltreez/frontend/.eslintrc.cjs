/* eslint-env node */
module.exports = {
  root: true,
  plugins: [
    "@stylistic/js"
  ],
  "extends": [
    "plugin:vue/vue3-essential",
    "eslint:recommended"
  ],
  parserOptions: {
    ecmaVersion: "latest"
  },
  rules: {
    camelcase: "error",
    "no-var": "error",
    "@stylistic/js/semi": "error",
    "@stylistic/js/quotes": ["error", "double"],
  },
  env: {
    "browser": true
  }
};
