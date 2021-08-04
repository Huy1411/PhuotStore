<template>
  <header class="sigma_header header-1 header-absolute header-light">
    <div class="sigma_header-top">
      <div class="container">
        <div class="sigma_header-top-inner">
          <ul class="sigma_header-top-links">
            <li class="menu-item">
              <a href="tel:+123456789">
                <i class="fal fa-phone"></i> (+123) 123 4567 890</a
              >
            </li>
            <li class="menu-item">
              <a href="mailto:info@example.com">
                <i class="fal fa-envelope"></i> info@example.com</a
              >
            </li>
          </ul>
          <ul class="sigma_sm">
            <li v-if="userName !== ''">
              <i
                style="color: #d48459; cursor: pointer"
                class="far fa-user"
                @click="goProfile"
              ></i>
              <span style="color: #d48459; cursor: pointer">{{
                userName
              }}</span>
              <i
                style="color: #d48459; cursor: pointer"
                class="fas fa-sign-out-alt"
                @click="logout"
              ></i>
            </li>
            <li v-else>
              <nuxt-link to="/login"
                ><i class="far fa-sign-in-alt"></i> Login
              </nuxt-link>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <!-- Middle Header Start -->
    <div class="sigma_header-middle">
      <div class="container">
        <nav class="navbar">
          <div class="sigma_logo-wrapper">
            <a class="navbar-brand" href="index.html">
              <img src="assets/img/logo-2.png" alt="logo" />
            </a>
          </div>
          <ul class="navbar-nav">
            <li
              v-for="(item, index) in menu"
              :key="index"
              class="menu-item menu-item-has-children"
            >
              <nuxt-link :to="item.path">{{ item.name }}</nuxt-link>
            </li>
          </ul>

          <div class="sigma_header-controls style-2">
            <ul class="sigma_header-controls-inner">
              <li>
                <a
                  href="contact-us.html"
                  class="d-none d-lg-block sigma_btn-custom"
                  >Book Today!</a
                >
              </li>
              <li>
                <nuxt-link to="/cart" class="d-none d-lg-block sigma_btn-custom"
                  >Cart

                  <i class="far fa-shopping-basket" style="font-size: 24px"></i>
                </nuxt-link>
              </li>

              <li class="aside-toggler aside-trigger-left">
                <span></span>
                <span></span>
                <span></span>
              </li>
            </ul>
          </div>
        </nav>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: 'HeaderUser.vue',
  data() {
    return {
      menu: [
        {
          name: 'Home',
          path: '/',
        },
        {
          name: 'About',
          path: '/about',
        },
        {
          name: 'Shop',
          path: '/shop',
        },
        {
          name: 'Contact',
          path: '/contact',
        },
      ],
    }
  },
  computed: {
    userName() {
      return this.$auth.$storage.getUniversal('token').username
        ? this.$auth.$storage.getUniversal('token').username
        : ''
    },
    userID() {
      return this.$auth.$storage.getUniversal('token').userID
        ? this.$auth.$storage.getUniversal('token').userID
        : ''
    },
  },
  methods: {
    async logout() {
      this.$auth.$storage.removeUniversal('token')
      this.$message.success(`Successfully Logout !`)
      await this.$router.push('/login')
    },
    goProfile() {
      this.$router.push({ name: 'profile', params: { id: this.userID } })
    },
  },
}
</script>
