<template>
  <div class="sigma_banner banner-3">
    <!-- Custom Arrows Start -->
    <div class="sigma_arrows sigma_arrows-transparent">
      <div class="sigma_round-arrow prev-arrow">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
          x="0px"
          y="0px"
          width="125px"
          height="125px"
          viewBox="0 0 197 197"
          enable-background="new 0 0 197 197"
          xml:space="preserve"
        >
          <circle
            class="sigma_round-arrow-stroke"
            stroke-linecap="round"
            cx="98.5"
            cy="98.6"
            r="97.5"
          ></circle>
          <circle
            class="sigma_round-arrow-circle"
            stroke-linecap="round"
            cx="98.5"
            cy="98.6"
            r="97.5"
          ></circle>
        </svg>
        <i class="far fa-arrow-left slick-arrow slider-prev"></i>
      </div>
      <div class="sigma_round-arrow next-arrow">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
          x="0px"
          y="0px"
          width="125px"
          height="125px"
          viewBox="0 0 197 197"
          enable-background="new 0 0 197 197"
          xml:space="preserve"
        >
          <circle
            class="sigma_round-arrow-stroke"
            stroke-linecap="round"
            cx="98.5"
            cy="98.6"
            r="97.5"
          ></circle>
          <circle
            class="sigma_round-arrow-circle"
            stroke-linecap="round"
            cx="98.5"
            cy="98.6"
            r="97.5"
          ></circle>
        </svg>
        <i class="far fa-arrow-right slick-arrow slider-next"></i>
      </div>
    </div>
    <!-- Custom Arrows End -->

    <div class="sigma_banner-slider">
      <!-- Banner Item Start -->
      <div
        class="
          light-bg
          sigma_banner-slider-inner
          bg-cover bg-center bg-norepeat
          dark-overlay
        "
        style="background-image: url('assets/img/banner/3.jpg')"
      >
        <div class="sigma_banner-text">
          <div class="container">
            <div class="row align-items-center">
              <div class="col-lg-6">
                <h4 class="text-white">Campa The Best Camping Co.</h4>
                <h1 class="title text-white">The Best Camping Ever</h1>
                <p>
                  Camping is an outdoor activity involving overnight stays away
                  from home with or without a shelter, such as a tent or a
                  recreational vehicle.
                </p>
                <div class="section-button d-flex align-items-center">
                  <a href="about-us.html" class="sigma_btn-custom"
                    >About Company
                  </a>
                  <a
                    href="contact-us.html"
                    class="ml-3 sigma_btn-custom secondary"
                    >Get a Quote
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BannerUser.vue',
}
</script>
