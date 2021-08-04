<template>
  <a-spin :spinning="loading">
    <a-tabs default-active-key="user" tab-position="left" @change="tabChange">
      <a-tab-pane key="user" tab="User Info">
        <a-form-model
          :model="user"
          :label-col="labelCol"
          :wrapper-col="wrapperCol"
        >
          <a-form-model-item label="User Name">
            <a-input v-model="user.username" />
          </a-form-model-item>
          <a-form-model-item label="Password">
            <a-input v-model="user.password" type="password" />
          </a-form-model-item>
          <a-form-model-item label="Address">
            <a-input v-model="user.address" />
          </a-form-model-item>
          <a-form-model-item label="Phone">
            <a-input v-model="user.phone" />
          </a-form-model-item>
          <a-form-model-item label="Email">
            <a-input v-model="user.email" />
          </a-form-model-item>
          <a-form-model-item :wrapper-col="{ span: 3, offset: 21 }">
            <a-button type="primary" @click="updateUser">
              Update User
            </a-button>
          </a-form-model-item>
        </a-form-model>
      </a-tab-pane>
      <a-tab-pane key="order" tab="Order Info" force-render>
        <h6 v-if="orderID !== ''" style="color: #d48459">
          ORDER CODE : PS - {{ order.orderName }}
        </h6>
        <a-tabs
          v-if="orderID !== ''"
          default-active-key="WAITING"
          @change="changeTab"
        >
          <a-tab-pane key="WAITING" tab="WAITING">
            <a-list
              v-if="order.status === 'WAITING'"
              item-layout="horizontal"
              :data-source="data"
            >
              <a-list-item slot="renderItem" slot-scope="item">
                <a-list-item-meta :description="item.productDesc">
                  <a slot="title">{{ item.productName }}</a>
                  <a-avatar slot="avatar" src="assets/img/products/0.jpg" />
                </a-list-item-meta>
                <div style="margin-right: 20px; color: #d48459">
                  Brand : {{ item.brand }}
                </div>
                <div style="margin-right: 20px; color: #d48459">
                  Category : {{ item.category }}
                </div>
                <div style="color: #d48459">{{ formatPrice(item.price) }}</div>
              </a-list-item>
              <div style="display: flex; justify-content: space-between">
                <p style="color: #d48459">{{ data.length }} product</p>
                <p style="color: #d48459">
                  Total Price : {{ formatPrice(order.totalPrice) }}
                </p>
              </div>
              <div style="display: flex; justify-content: space-between">
                <p style="color: #d48459">
                  Have you want change the billing infomation please click Edit,
                  or click the cancel to cancel the order
                </p>
                <a-button type="primary" @click="visible = true">
                  Edit Order</a-button
                >
                <a-button type="danger" @click="updateOrder('CANCEL')">
                  CANCEL ORDER</a-button
                >
              </div>
            </a-list>
            <p v-else style="color: #d48459; text-align: center">
              This Status Have No Order
            </p>
          </a-tab-pane>
          <a-tab-pane key="CONFIRM" tab="CONFIRM">
            <a-list
              v-if="order.status === 'CONFIRM'"
              item-layout="horizontal"
              :data-source="data"
            >
              <a-list-item slot="renderItem" slot-scope="item">
                <a-list-item-meta :description="item.productDesc">
                  <a slot="title">{{ item.productName }}</a>
                  <a-avatar slot="avatar" src="assets/img/products/0.jpg" />
                </a-list-item-meta>
                <div style="margin-right: 20px; color: #d48459">
                  Brand : {{ item.brand }}
                </div>
                <div style="margin-right: 20px; color: #d48459">
                  Category : {{ item.category }}
                </div>
                <div style="color: #d48459">{{ formatPrice(item.price) }}</div>
              </a-list-item>
              <div style="display: flex; justify-content: space-between">
                <p style="color: #d48459">{{ data.length }} product</p>
                <p style="color: #d48459">
                  Total Price : {{ formatPrice(order.totalPrice) }}
                </p>
              </div>
              <div style="display: flex; justify-content: space-between">
                <p style="color: #d48459">
                  Your order is being prepared. Please check your shipping
                  address before we ship it. Orders during delivery cannot be
                  edited or canceled
                </p>
                <a-button type="primary" @click="visible = true">
                  Edit Order</a-button
                >
              </div>
            </a-list>
            <p v-else style="color: #d48459; text-align: center">
              This Status Have No Order
            </p>
          </a-tab-pane>
          <a-tab-pane key="SHIPPING" tab="SHIPPING">
            <a-list
              v-if="order.status === 'SHIPPING'"
              item-layout="horizontal"
              :data-source="data"
            >
              <a-list-item slot="renderItem" slot-scope="item">
                <a-list-item-meta :description="item.productDesc">
                  <a slot="title">{{ item.productName }}</a>
                  <a-avatar slot="avatar" src="assets/img/products/0.jpg" />
                </a-list-item-meta>
                <div style="margin-right: 20px; color: #d48459">
                  Brand : {{ item.brand }}
                </div>
                <div style="margin-right: 20px; color: #d48459">
                  Category : {{ item.category }}
                </div>
                <div style="color: #d48459">{{ formatPrice(item.price) }}</div>
              </a-list-item>
              <div style="display: flex; justify-content: space-between">
                <p style="color: #d48459">{{ data.length }} product</p>
                <p style="color: #d48459">
                  Total Price : {{ formatPrice(order.totalPrice) }}
                </p>
              </div>
              <div style="display: flex; justify-content: space-between">
                <p style="color: #d48459">
                  Have you received your order, Click the order received order
                  button to confirm the order
                </p>
                <a-button type="primary" @click="updateOrder('DONE')">
                  Order received
                </a-button>
              </div>
            </a-list>
            <p v-else style="color: #d48459; text-align: center">
              This Status Have No Order
            </p>
          </a-tab-pane>
          <a-tab-pane key="CANCEL" tab="CANCEL">
            <a-list
              v-if="order.status === 'CANCEL'"
              item-layout="horizontal"
              :data-source="data"
            >
              <a-list-item slot="renderItem" slot-scope="item">
                <a-list-item-meta :description="item.productDesc">
                  <a slot="title">{{ item.productName }}</a>
                  <a-avatar slot="avatar" src="assets/img/products/0.jpg" />
                </a-list-item-meta>
                <div style="margin-right: 20px; color: #d48459">
                  Brand : {{ item.brand }}
                </div>
                <div style="margin-right: 20px; color: #d48459">
                  Category : {{ item.category }}
                </div>
                <div style="color: #d48459">{{ formatPrice(item.price) }}</div>
              </a-list-item>
              <div style="display: flex; justify-content: space-between">
                <p style="color: #d48459">{{ data.length }} product</p>
                <p style="color: #d48459">
                  Total Price : {{ formatPrice(order.totalPrice) }}
                </p>
              </div>
              <div style="display: flex; justify-content: space-between">
                <p style="color: #d48459">
                  Have you received your order, if you have received it, please
                  click the confirm button
                </p>
              </div>
            </a-list>
            <p v-else style="color: #d48459; text-align: center">
              This Status Have No Order
            </p>
          </a-tab-pane>
          <a-tab-pane key="DONE" tab="DONE">
            <a-list
              v-if="order.status === 'DONE'"
              item-layout="horizontal"
              :data-source="data"
            >
              <a-list-item slot="renderItem" slot-scope="item">
                <a-list-item-meta :description="item.productDesc">
                  <a slot="title">{{ item.productName }}</a>
                  <a-avatar slot="avatar" src="assets/img/products/0.jpg" />
                </a-list-item-meta>
                <div style="margin-right: 20px; color: #d48459">
                  Brand : {{ item.brand }}
                </div>
                <div style="margin-right: 20px; color: #d48459">
                  Category : {{ item.category }}
                </div>
                <div style="color: #d48459">{{ formatPrice(item.price) }}</div>
              </a-list-item>
              <div style="display: flex; justify-content: space-between">
                <p style="color: #d48459">{{ data.length }} product</p>
                <p style="color: #d48459">
                  Total Price : {{ formatPrice(order.totalPrice) }}
                </p>
              </div>
              <div style="display: flex; justify-content: space-between">
                <p style="color: #d48459">
                  Order has been delivered successfully
                </p>
              </div>
            </a-list>
            <p v-else style="color: #d48459; text-align: center">
              This Status Have No Order
            </p>
          </a-tab-pane>
          <a-button slot="tabBarExtraContent" @click="backToList">
            <a-icon type="arrow-left" /> Back To List
          </a-button>
        </a-tabs>
        <div v-else>
          <h6 style="color: #d48459; text-align: center">ORDER LIST</h6>
          <a-table :columns="columns" :data-source="orderList">
            <span slot="action" slot-scope="record">
              <a-button type="primary" @click="editOrder(record.orderID)"
                ><a-icon type="edit"
              /></a-button>
            </span>
          </a-table>
        </div>
      </a-tab-pane>
    </a-tabs>
    <a-modal v-model="visible" title="Edit Shipping Address" @ok="handleOk">
      <a-form-model
        :model="orderUpdate"
        :label-col="{ span: 7 }"
        :wrapper-col="{ span: 17 }"
      >
        <a-form-model-item label="Shipping Address">
          <a-input
            v-model="orderUpdate.shippingAddress"
            placeholder="Enter Shipping Address"
          />
        </a-form-model-item>
        <a-form-model-item label="Phone Number">
          <a-input
            v-model="orderUpdate.phone"
            placeholder="Enter Phone Number"
          />
        </a-form-model-item>
        <a-form-model-item label="Email Address">
          <a-input
            v-model="orderUpdate.email"
            placeholder="Enter Email Address"
          />
        </a-form-model-item>
      </a-form-model>
    </a-modal>
  </a-spin>
</template>

<script>
const columns = [
  {
    title: 'ID',
    dataIndex: 'orderID',
    key: 'orderID',
  },
  {
    title: 'Order Code',
    dataIndex: 'orderName',
    key: 'orderName',
  },
  {
    title: 'Grand Total',
    dataIndex: 'totalPrice',
    key: 'totalPrice',
  },
  {
    title: 'Total Quantity',
    dataIndex: 'totalQuantity',
    key: 'totalQuantity',
  },
  {
    title: 'Pay Date',
    dataIndex: 'createAt',
    key: 'createAt',
    scopedSlots: { customRender: 'payDate' },
  },
  {
    title: 'Status',
    dataIndex: 'status',
    key: 'status',
  },
  {
    title: 'Order Note',
    dataIndex: 'note',
    key: 'note',
  },
  {
    title: 'Action',
    key: 'action',
    scopedSlots: { customRender: 'action' },
  },
]
export default {
  name: 'index.vue',
  data() {
    return {
      data: [],
      columns,
      labelCol: { span: 3 },
      wrapperCol: { span: 21 },
      user: {},
      loading: false,
      orderID: '',
      order: {},
      orderList: [],
      products: [],
      orderUpdate: {},
      visible: false,
    }
  },
  computed: {
    userID() {
      return this.$route.params.id ? this.$route.params.id : ''
    },
  },
  async created() {
    await this.loadPage()
  },
  methods: {
    async loadPage() {
      this.loading = true
      try {
        const result = await this.$api.getUser(this.userID, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.user = result
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
      }
    },
    async updateUser() {
      try {
        this.loading = true
        delete this.user.roles
        await this.$api.updateUser(this.userID, this.user, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        await this.loadPage()
      }
    },
    tabChange(key) {
      if (key === 'order' && this.userID !== '') {
        this.getOrderList()
      } else {
        this.loadPage()
      }
    },
    async backToList() {
      await this.getOrderList()
      this.orderID = ''
      this.products = []
    },
    formatPrice(money) {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(money)
    },
    async getOrderList() {
      try {
        this.loading = true
        const result = await this.$api.getOrderByUser(this.userID, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.orderList = [...result.content]
        console.log(result.content)
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
      }
    },
    async editOrder(id) {
      this.orderID = id
      try {
        this.loading = true
        const result = await this.$api.getOrder(id, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.order = { ...result }
        this.data = [...result.products].reduce((acc, item) => {
          const product = {
            productDesc: item.productDesc,
            productName: item.productName,
            price: item.price,
            brand: item.brand.brandName,
            category: item.category.categoryName,
          }
          acc.push(product)
          return acc
        }, [])
        const productEdit = []
        // eslint-disable-next-line no-unused-expressions
        result.products.length
          ? result.products.forEach((item) => {
              productEdit.push(item.productID)
            })
          : []
        const comboEdit = []
        // eslint-disable-next-line no-unused-expressions
        result.combos.length
          ? result.combos.forEach((item) => {
              comboEdit.push(item.comboID)
            })
          : []
        this.orderUpdate = {
          orderID: result.orderID,
          orderName: result.orderName,
          phone: result.phone,
          shippingAddress: result.shippingAddress,
          lastName: result.lastName,
          firstName: result.firstName,
          totalQuantity: result.totalQuantity,
          combo: comboEdit,
          product: productEdit,
          userID: result.user.userID,
          email: result.email,
          note: result.note,
          paymentType: result.paymentType,
          totalPrice: result.totalPrice,
          status: result.status,
          createAt: result.createAt,
          updateAt: new Date(),
        }
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
      }
    },
    changeTab(key) {
      console.log(key)
    },
    showModal() {
      this.visible = true
    },
    async handleOk(e) {
      try {
        this.loading = true
        await this.$api.updateOrder(this.orderID, this.orderUpdate, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
        await this.editOrder(this.orderID)
        this.visible = false
      }
    },
    async updateOrder(status) {
      try {
        this.orderUpdate.status = status
        this.loading = true
        await this.$api.updateOrder(this.orderID, this.orderUpdate, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
        await this.editOrder(this.orderID)
      }
    },
  },
}
</script>
