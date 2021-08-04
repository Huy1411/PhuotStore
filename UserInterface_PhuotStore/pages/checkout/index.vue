<template>
  <a-spin :spinning="loading">
    <form method="post">
      <div class="row">
        <div class="col-xl-7">
          <h4>Billing Details</h4>
          <div class="row">
            <div class="form-group col-xl-6">
              <label>Name <span class="text-danger">*</span></label>
              <input
                v-model="billingDetails.firstName"
                type="text"
                placeholder="Name"
                name="fname"
                class="form-control"
                required=""
              />
            </div>
            <!--            <div class="form-group col-xl-6">-->
            <!--              <label>Last Name <span class="text-danger">*</span></label>-->
            <!--              <input-->
            <!--                v-model="billingDetails.lastName"-->
            <!--                type="text"-->
            <!--                placeholder="Last Name"-->
            <!--                name="lname"-->
            <!--                class="form-control"-->
            <!--                required=""-->
            <!--              />-->
            <!--            </div>-->
            <div class="form-group col-xl-6">
              <label>Phone Number <span class="text-danger">*</span></label>
              <input
                v-model="billingDetails.phone"
                type="text"
                placeholder="Phone Number"
                name="phone"
                class="form-control"
                required=""
              />
            </div>
            <div class="form-group col-xl-6">
              <label>Email Address <span class="text-danger">*</span></label>
              <input
                v-model="billingDetails.email"
                type="email"
                placeholder="Email Address"
                name="email"
                class="form-control"
                required=""
              />
            </div>
            <div class="form-group col-xl-6">
              <label>Shipping Address <span class="text-danger">*</span></label>
              <input
                v-model="billingDetails.shippingAddress"
                type="text"
                placeholder="Shipping Address"
                name="shippingAddress"
                class="form-control"
                required=""
              />
            </div>
            <div class="form-group col-xl-12 mb-0">
              <label>Order Notes</label>
              <textarea
                v-model="billingDetails.note"
                name="name"
                rows="5"
                class="form-control"
                placeholder="Order Notes (Optional)"
              ></textarea>
            </div>
          </div>
          <!-- Buyer Info End -->
        </div>
        <div class="col-xl-5 checkout-billing">
          <!-- Order Details Start -->
          <table class="sigma_responsive-table">
            <thead>
              <tr>
                <th>Product</th>
                <th>Quantity</th>
                <th>Total</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in cart" :key="index">
                <td data-title="Product">
                  <div class="sigma_cart-product-wrapper">
                    <div class="sigma_cart-product-body">
                      <h6>
                        <a href="#">{{ item.product.productName }}</a>
                      </h6>
                    </div>
                  </div>
                </td>
                <td data-title="Quantity">{{ item.quantity }}</td>
                <td data-title="Total">
                  <strong>{{
                    formatPrice(item.product.price * item.quantity)
                  }}</strong>
                </td>
              </tr>
            </tbody>
            <tr class="total">
              <td>
                <h6 class="mb-0">Grand Total</h6>
              </td>
              <td>{{ billingDetails.totalQuantity }}</td>
              <td>
                <strong>{{ formatPrice(billingDetails.totalPrice) }}</strong>
              </td>
            </tr>
          </table>
          <div class="pay_ment" style="display: flex">
            <input
              v-model="billingDetails.paymentType"
              type="radio"
              value="PAYNOW"
              placeholder="Shipping Address"
              name="shippingAddress"
              class="form-control"
              required=""
              style="margin-top: 5px"
            />
            <label style="margin-left: 10px">PAYNOW</label>
            <input
              v-model="billingDetails.paymentType"
              type="radio"
              value="ONLINE"
              placeholder="Shipping Address"
              name="shippingAddress"
              class="form-control"
              required=""
              style="margin-top: 5px; margin-left: 100px"
            />
            <label style="margin-left: 10px">ONLINE</label>
          </div>
          <div v-if="billingDetails.paymentType !== 'PAYNOW'">
            <div class="form-group">
              <label>Card Number</label>
              <input
                type="text"
                class="form-control"
                name="master-number"
                placeholder="Card Number"
                value=""
              />
            </div>
            <div class="form-group">
              <label>Full Name</label>
              <input
                type="text"
                class="form-control"
                name="master-name"
                placeholder="Full Name"
                value=""
              />
            </div>
            <div class="row">
              <div class="col-xl-6 form-group">
                <label>Expiry Date</label>
                <input
                  type="text"
                  class="form-control"
                  name="master-expiry"
                  placeholder="Expiry Date (MM/YY)"
                  value=""
                />
              </div>
              <div class="col-xl-6 form-group">
                <label>CVV*</label>
                <input
                  type="number"
                  class="form-control"
                  name="master-cvv"
                  placeholder="CVV"
                  value=""
                />
              </div>
            </div>

            <p class="small">
              Your personal data will be used to process your order, support
              your experience throughout this website, and for other purposes
              described in our <a class="btn-link" href="#">privacy policy.</a>
            </p>
          </div>
          <button
            type="button"
            class="sigma_btn-custom primary btn-block"
            @click="payment()"
          >
            Place Order
          </button>
          <!-- Order Details End -->
        </div>
      </div>
    </form>
  </a-spin>
</template>

<script>
import { generateHash } from 'random-hash'
export default {
  name: 'index.vue',
  data() {
    return {
      loading: false,
      cart: [],
      grandTotal: 0,
      user: {},
      billingDetails: {
        orderName: '',
        note: '',
        status: 'WAITING',
        totalQuantity: 0,
        totalPrice: 0,
        firstName: '',
        lastName: '',
        email: '',
        shippingAddress: '',
        paymentType: 'PAYNOW',
        phone: '',
        userID: '',
        product: [],
        combo: [],
      },
    }
  },
  computed: {
    userID() {
      return this.$auth.$storage.getUniversal('token').userID
        ? this.$auth.$storage.getUniversal('token').userID
        : ''
    },
  },
  async created() {
    await this.getCart()
  },
  methods: {
    async getUserInfo() {
      this.loading = true
      try {
        const result = await this.$api.getUser(this.userID, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.user = result
        console.log(this.user)
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
      }
    },
    async getCart() {
      if (process.browser) {
        await this.getUserInfo()
        this.billingDetails.firstName = this.user.username
        this.billingDetails.shippingAddress = this.user.address
        this.billingDetails.phone = this.user.phone
        this.billingDetails.email = this.user.email
        const cart = localStorage.getItem('cart')
          ? JSON.parse(localStorage.getItem('cart'))
          : []
        const user = this.$auth.$storage.getUniversal('token')
        this.cart = [...cart]
        cart.forEach((item) => {
          const itemPer = Number(item.product.price * item.quantity)
          this.billingDetails.totalQuantity += Number(item.quantity)
          this.billingDetails.totalPrice += itemPer
          this.billingDetails.product.push(item.product.productID)
          this.billingDetails.userID = user.userID
          this.billingDetails.orderName = generateHash()
        })
      }
    },
    payment() {
      this.$confirm({
        title: 'Are you sure to order ? ',
        content: 'Please check the billing information before order ?',
        okText: 'Submit',
        cancelText: 'Cancel',
        onOk: this.checkout,
      })
    },
    async checkout() {
      try {
        this.loading = true
        await this.$api.orderPlace(this.billingDetails, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        localStorage.removeItem('cart')
        this.loading = false
        this.$message.success(`Payment Successfully!`)
        this.$router.push('/')
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
