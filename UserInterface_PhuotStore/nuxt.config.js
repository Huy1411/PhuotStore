export default {
  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'Phượt Store',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' },
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      {
        rel: 'stylesheet',
        href: `https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css`,
        type: 'text/css',
      },
      {
        rel: 'stylesheet',
        href: 'https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i',
      },
    ],
    scripts: [
      {
        src: 'assets/js/plugins/jquery-3.5.1.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/popper.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/bootstrap.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/imagesloaded.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/jquery.magnific-popup.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/jquery.countdown.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/jquery.waypoints.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/jquery.counterup.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/jquery.zoom.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/jquery.inview.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/jquery.event.move.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/wow.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/isotope.pkgd.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/slick.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/plugins/ion.rangeSlider.min.js',
        type: 'text/javascript',
      },
      {
        src: 'assets/js/main.js',
        type: 'text/javascript',
      },
    ],
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
    'ant-design-vue/dist/antd.css',
    '@/assets/css/main.scss',
    '@/assets/user/css/plugins/bootstrap.min.css',
    '@/assets/user/css/plugins/animate.min.css',
    '@/assets/user/css/plugins/magnific-popup.css',
    '@/assets/user/css/plugins/slick.css',
    '@/assets/user/css/plugins/slick-theme.css',
    '@/assets/user/css/plugins/ion.rangeSlider.min.css',
    '@/assets/user/fonts/flaticon/flaticon.css',
    '@/assets/user/css/plugins/font-awesome.min.css',
    '@/assets/user/css/style.css',
    '@/assets/user/css/responsive.css',
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: ['@/plugins/antd-ui', '~/plugins/api', '~/plugins/firebase'],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    // https://go.nuxtjs.dev/eslint
    '@nuxtjs/eslint-module',
    // https://go.nuxtjs.dev/stylelint
    '@nuxtjs/stylelint-module',
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/axios
    '@nuxtjs/axios',
    // https://go.nuxtjs.dev/pwa
    '@nuxtjs/pwa',
    // https://go.nuxtjs.dev/content
    '@nuxt/content',
    '@nuxtjs/auth-next',
  ],
  subdomains: ['admin'],
  env: {
    baseUrl: 'https://phuotstoreapiv1.herokuapp.com',
    ROOT_DOMAIN: 'user',
    API_KEY: 'AIzaSyCgvK5-zVhm5aBnrVClBaAk9o7RaiDdiAI',
    AUTH_DOMAIN: 'phuotstore.firebaseapp.com',
    PROJECT_ID: 'phuotstore',
    STORAGE_BUCKET: 'phuotstore.appspot.com',
    MESSAGING_SENDER_ID: '917875276169',
    APP_ID: '1:917875276169:web:64c4ae86df3e86fee8c958',
    MESUREMENT_ID: 'G-39K8N6F8TG',
  },
  // Axios module configuration: https://go.nuxtjs.dev/config-axios
  axios: {},

  // PWA module configuration: https://go.nuxtjs.dev/pwa
  pwa: {
    manifest: {
      lang: 'en',
    },
  },

  // Content module configuration: https://go.nuxtjs.dev/config-content
  content: {},

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
    loaders: {
      less: {
        lessOptions: {
          javascriptEnabled: true,
          math: 'always',
        },
      },
    },
  },
  loading: {
    color: '#D08E6C',
    height: '5px',
  },
}
