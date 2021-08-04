<template>
  <div>
    <layoutHeaderUser />
    <layoutBannerUser v-if="$route.name === 'index'" />
    <layoutBannerUser2 v-else />
    <section class="section">
      <div class="container">
        <Nuxt />
      </div>
    </section>
    <layoutFooterUser />
  </div>
</template>
<script>
export default {
  middleware: 'isLogin',
}
</script>
