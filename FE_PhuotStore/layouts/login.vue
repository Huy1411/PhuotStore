<template>
  <Nuxt />
</template>

<script>
export default {
  name: 'login.vue',
  middleware: 'isLogin',
}
</script>
