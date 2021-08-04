<template>
  <a-spin :spinning="loading">
    <div class="section section-padding">
      <div class="container">
        <div class="row">
          <div class="col-lg-5 col-md-6">
            <div class="sigma_product-single-thumb mb-lg-30">
              <div class="slider">
                <img src="assets/img/products/new/0.jpg" alt="product" />
                <img src="assets/img/products/new/1.jpg" alt="product" />
                <img src="assets/img/products/new/2.jpg" alt="product" />
                <img src="assets/img/products/new/3.jpg" alt="product" />
              </div>
              <div class="slider-nav">
                <img src="assets/img/products/0.jpg" alt="product" />
                <img src="assets/img/products/1.jpg" alt="product" />
                <img src="assets/img/products/2.jpg" alt="product" />
                <img src="assets/img/products/3.jpg" alt="product" />
              </div>
            </div>
          </div>
          <div class="col-lg-7 col-md-6">
            <div class="sigma_product-single-content">
              <div class="sigma_product-price">
                <span>352$</span>
                <span>245$</span>
              </div>
              <div class="sigma_rating-wrapper">
                <div class="sigma_rating">
                  <i class="fas fa-star active"></i>
                  <i class="fas fa-star active"></i>
                  <i class="fas fa-star active"></i>
                  <i class="fas fa-star active"></i>
                  <i class="fas fa-star"></i>
                </div>
              </div>

              <hr />

              <p class="sigma_product-excerpt">
                {{ product ? product.productDesc : '' }}
              </p>

              <p class="fs-14">
                <strong class="mr-2"
                  >Product Name:
                  <span>{{ product ? product.productName : '' }}</span></strong
                >
              </p>
              <p class="fs-14">
                <strong class="mr-2"
                  >Quantity :
                  <span>{{ product ? product.qty : '' }}</span></strong
                >
              </p>
              <p class="fs-14">
                <strong class="mr-2">Category: </strong>
                <span class="custom-body">{{
                  product.category ? product.category.categoryName : ''
                }}</span>
              </p>
              <p class="fs-14">
                <strong class="mr-2">Brand: </strong>
                <span class="custom-body">{{
                  product.brand ? product.brand.brandName : ''
                }}</span>
              </p>
              <hr />

              <form class="sigma_product-atc-form">
                <div class="sigma_product-variation-wrapper">
                  <div class="qty-outter">
                    <div class="qty-inner">
                      <label>Qty</label>
                      <div class="qty">
                        <span class="qty-subtract"
                          ><i
                            class="fa fa-minus"
                            @click="
                              quantity === 1 ? (quantity = 1) : (quantity -= 1)
                            "
                          ></i
                        ></span>
                        <input v-model="quantity" type="text" name="qty" />
                        <span class="qty-add" @click="quantity += 1"
                          ><i class="fa fa-plus"></i
                        ></span>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="sigma_product-buttons">
                  <a class="sigma_btn-custom" @click="addToCard(product)"
                    >Add To Cart<i class="far fa-shopping-basket"></i>
                  </a>
                </div>
              </form>

              <!-- Post Meta Start -->
              <div class="sigma_post-single-meta">
                <div class="sigma_post-single-meta-item sigma_post-share">
                  <h6>Share</h6>
                  <ul class="sigma_sm">
                    <li>
                      <a href="#">
                        <i class="fab fa-facebook-f"></i>
                      </a>
                    </li>
                    <li>
                      <a href="#">
                        <i class="fab fa-linkedin-in"></i>
                      </a>
                    </li>
                    <li>
                      <a href="#">
                        <i class="fab fa-twitter"></i>
                      </a>
                    </li>
                    <li>
                      <a href="#">
                        <i class="fab fa-youtube"></i>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
              <!-- Post Meta End -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </a-spin>
</template>
<script>
export default {
  data() {
    return {
      loading: false,
      product: {},
      quantity: 1,
    }
  },
  async created() {
    await this.loadPage()
  },
  methods: {
    async loadPage() {
      this.loading = true
      try {
        const result = await this.$api.getProduct(this.$route.params.id, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.product = { ...result }
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
      }
    },
    addToCard(product) {
      if (process.browser) {
        let cart = localStorage.getItem('cart')
        if (cart === null) cart = []
        else cart = JSON.parse(cart)
        let count = 0
        for (let i = 0; i < cart.length; i++) {
          if (cart[i].product.productID === product.productID) {
            if (cart[i].quantity < product.qty) {
              cart[i].quantity += this.quantity
            }
            count++
          }
        }
        if (count === 0) {
          cart.push({ product, quantity: this.quantity })
        }
        this.$message.success(`Add To Cart Successfully!`)
        localStorage.setItem('cart', JSON.stringify(cart))
      }
    },
  },
}
</script>
