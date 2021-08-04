<template>
  <div
    class="sigma_subheader dark-overlay dark-overlay-2"
    style="background-image: url(assets/img/subheader.jpg)"
  >
    <div class="container">
      <div class="sigma_subheader-inner">
        <div class="sigma_subheader-text">
          <h1>{{ $route.name.toUpperCase() }}</h1>
        </div>
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <nuxt-link to="/">Home</nuxt-link>
          </li>
          <nuxt-link
            :to="$route.path"
            class="breadcrumb-item active"
            aria-current="page"
            >{{ $route.name.toUpperCase() }}</nuxt-link
          >
        </ol>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BannerUser2.vue',
}
</script>
