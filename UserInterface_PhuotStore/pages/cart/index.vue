<template>
  <div>
    <table class="sigma_responsive-table">
      <thead>
        <tr>
          <th class="remove-item"></th>
          <th>Product</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Total</th>
        </tr>
      </thead>
      <tbody v-if="cart.length">
        <tr v-for="(item, index) in cart" :key="index">
          <td class="remove">
            <button
              type="button"
              class="close-btn close-danger remove-from-cart"
              @click="remove(item)"
            >
              <span></span>
              <span></span>
            </button>
          </td>
          <td data-title="Product">
            <div class="sigma_cart-product-wrapper">
              <img src="assets/img/products/1.jpg" alt="prod1" />
              <div class="sigma_cart-product-body">
                <h6>
                  <a href="#">{{ item.product.productName }}</a>
                </h6>
              </div>
            </div>
          </td>
          <td data-title="Price">
            <strong>{{ formatPrice(item.product.price) }}</strong>
          </td>
          <td class="quantity" data-title="Quantity">
            <input
              v-model="item.quantity"
              type="number"
              class="qty form-control"
              @change="onChange(item)"
            />
          </td>
          <td data-title="Total">
            <strong>{{
              formatPrice(item.product.price * item.quantity)
            }}</strong>
          </td>
        </tr>
      </tbody>
      <span
        v-else
        style="
          margin-top: 20px;
          width: 100%;
          display: flex;
          justify-content: center;
          align-items: center;
        "
      >
        Empty Cart Item
      </span>
    </table>
    <!--    <div class="row">-->
    <!--      <div class="col-lg-6">-->
    <!--        <a-range-picker @change="onChange" />-->
    <!--      </div>-->
    <!--    </div>-->
    <div class="row">
      <div class="col-lg-5">
        <div class="form-group mb-0">
          <div class="input-group mb-0">
            <input
              type="text"
              class="form-control"
              placeholder="Enter Coupon Code"
              aria-label="Coupon Code"
            />
            <div class="input-group-append">
              <button
                class="sigma_btn-custom shadow-none"
                type="button"
                @click="goToCheckOut()"
              >
                CheckOut
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      cart: [],
    }
  },
  async created() {
    await this.getCart()
  },
  methods: {
    getCart() {
      if (process.browser) {
        const cart = localStorage.getItem('cart')
          ? JSON.parse(localStorage.getItem('cart'))
          : []
        this.cart = [...cart]
      }
    },
    goToCheckOut() {
      this.$router.push('/checkout')
    },
    formatPrice(money) {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(money)
    },
    onChange(item) {
      ;[...this.cart].map((cart) => {
        return cart.product.productID === item.product.productID
      })
      if (process.browser) {
        localStorage.setItem('cart', JSON.stringify(this.cart))
      }
    },
    remove(item) {
      this.cart = [...this.cart].filter((cart) => {
        return cart.product.productID !== item.product.productID
      })
      if (process.browser) {
        localStorage.setItem('cart', JSON.stringify(this.cart))
      }
    },
  },
}
</script>
