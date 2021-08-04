<template>
  <div class="col-lg-8">
    <!-- Product Count & Orderby Start -->
    <div class="sigma_shop-global">
      <p>
        Showing <b>{{ products.length }}</b> of <b>{{ total }}</b> products
      </p>
      <a href="#" class="custom-secondary fw-600"
        >Filter <i class="custom-primary far fa-filter ml-2"></i>
      </a>
    </div>
    <!-- Product Count & Orderby End -->

    <div class="row masonry">
      <div
        v-for="(product, index) in products"
        :key="index"
        class="col-md-4 col-sm-6 masonry-item"
      >
        <div class="sigma_product">
          <div class="sigma_product-thumb">
            <nuxt-link :to="{ path: `/shop/${product.productID}` }"
              ><img src="assets/img/products/0.jpg" alt="product"
            /></nuxt-link>
            <div class="sigma_product-controls">
              <a href="#" data-toggle="tooltip" title="Wishlist">
                <i class="far fa-heart"></i>
              </a>
              <a
                data-toggle="tooltip"
                title="Add To Cart"
                @click="addToCard(product)"
              >
                <i class="far fa-shopping-basket"></i>
              </a>
              <a href="#" data-toggle="tooltip" title="Quick View">
                <i
                  data-toggle="modal"
                  data-target="#quickViewModal"
                  class="far fa-eye"
                ></i>
              </a>
            </div>
          </div>
          <div class="sigma_product-body">
            <h5 class="sigma_product-title">
              <nuxt-link :to="{ path: `/shop/${product.productID}` }">{{
                product.productName
              }}</nuxt-link>
            </h5>
            <div class="sigma_product-price">
              <span>{{ formatPrice(product.price) }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <ul class="pagination">
      <li class="page-item"><a class="page-link" href="#">1</a></li>
      <li class="page-item active">
        <a class="page-link" href="#"
          >2 <span class="sr-only">(current)</span></a
        >
      </li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Content.vue',
  props: {
    products: {
      type: Array,
      default: () => [],
    },
    total: {
      type: Number,
      default: () => 0,
    },
  },
  data() {
    return {
      currentCart: [],
    }
  },
  methods: {
    addToCard(product) {
      if (process.browser) {
        let cart = localStorage.getItem('cart')
        if (cart === null) cart = []
        else cart = JSON.parse(cart)
        let count = 0
        for (let i = 0; i < cart.length; i++) {
          if (cart[i].product.productID === product.productID) {
            if (cart[i].quantity < product.qty) {
              cart[i].quantity++
            }
            count++
          }
        }
        if (count === 0) {
          cart.push({ product, quantity: 1 })
        }
        this.$message.success(`Add To Cart Successfully!`)
        localStorage.setItem('cart', JSON.stringify(cart))
        this.$store.dispatch('setCount', cart.length)
      }
    },
    formatPrice(money) {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(money)
    },
  },
}
</script>
