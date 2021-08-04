<template>
  <div>
    <a-modal
      :width="900"
      :title="title"
      :visible="visible"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
      @cancel="close"
    >
      <a-spin :spinning="loading">
        <a-descriptions title="Change Status" bordered>
          <a-descriptions-item label="Choose Status" :span="3">
            <a-radio-group
              v-model="status"
              name="radioGroup"
              @change="onChange"
            >
              <a-radio value="WAITING" :disabled="true" style="color: #aaaba6">
                WAITING
              </a-radio>
              <a-radio
                :disabled="
                  order &&
                  (order.status === 'DONE' || order.status === 'CANCEL')
                "
                value="SHIPPING"
                style="color: purple"
              >
                SHIPPING
              </a-radio>
              <a-radio
                :disabled="
                  order &&
                  (order.status === 'SHIPPING' ||
                    order.status === 'CANCEL' ||
                    order.status === 'DONE')
                "
                value="CONFIRM"
                style="color: #5d91ff"
              >
                CONFIRM
              </a-radio>
              <a-radio
                :disabled="
                  order &&
                  (order.status === 'SHIPPING' ||
                    order.status === 'CONFIRM' ||
                    order.status === 'DONE')
                "
                value="CANCEL"
                style="color: red"
              >
                CANCEL
              </a-radio>
            </a-radio-group>
          </a-descriptions-item>
        </a-descriptions>
        <a-descriptions title="Order Info" bordered>
          <a-descriptions-item label="Order Code" :span="3">
            PS - {{ order ? order.orderName : '' }}
          </a-descriptions-item>
          <a-descriptions-item label="Customer Name" :span="3">
            {{ order ? order.user.username : '' }}
          </a-descriptions-item>
          <a-descriptions-item label="Email Address" :span="2">
            {{ order ? order.email : '' }}
          </a-descriptions-item>
          <a-descriptions-item label="Phone Number">
            {{ order ? order.phone : '' }}
          </a-descriptions-item>
          <a-descriptions-item label="Shipping Address" :span="2">
            {{ order ? order.shippingAddress : '' }}
          </a-descriptions-item>
          <a-descriptions-item label="Status">
            <a-badge
              :status="setStatus(order ? order.status : '')"
              :text="order ? order.status : ''"
            />
          </a-descriptions-item>
          <a-descriptions-item label="Payment Type" :span="2">
            {{ order ? order.paymentType : '' }}
          </a-descriptions-item>
          <a-descriptions-item label="Grand Total">
            {{ formatPrice(order ? order.totalPrice : 0) }}
          </a-descriptions-item>
          <a-descriptions-item label="Product Info">
            <a-tag
              v-for="(item, index) in order ? order.products : []"
              :key="index"
              color="#D48459"
            >
              {{ item.productName }}
            </a-tag>
          </a-descriptions-item>
        </a-descriptions>
      </a-spin>
    </a-modal>
  </div>
</template>
<script>
export default {
  data() {
    return {
      labelCol: { span: 7 },
      wrapperCol: { span: 17 },
      visible: false,
      confirmLoading: false,
      loading: false,
      orderID: '',
      title: '',
      action: '',
      order: null,
      orderUpdate: null,
      status: '',
      isCanChange: false,
    }
  },
  computed: {},
  methods: {
    async showEdit(id) {
      try {
        this.loading = true
        this.action = 'edit'
        this.title = 'Order Details'
        this.orderID = id
        this.visible = true
        const result = await this.$api.getOrder(id, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
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
        this.order = {
          orderName: result.orderName,
          phone: result.phone,
          shippingAddress: result.shippingAddress,
          email: result.email,
          note: result.note,
          paymentType: result.paymentType,
          totalPrice: result.totalPrice,
          status: result.status,
          user: result.user,
          products: result.products,
          combos: result.combos,
          createdAt: result.createdAt,
          updateAt: result.updateAt,
        }
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
        this.status = result.status
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
      }
    },
    handleOk(e) {
      this.$confirm({
        title: 'Update Order ? ',
        content: 'Do you sure want to update order ? ',
        onOk: () => this.updateOrder(),
      })
    },
    async updateOrder() {
      try {
        this.confirmLoading = true
        await this.$api.updateOrder(this.orderID, this.orderUpdate, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.$message.success(`Update Order Successfully!`)
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.confirmLoading = false
        this.visible = false
        this.$emit('refreshOrder')
      }
    },
    close(e) {
      this.visible = false
      this.isCanChange = false
    },
    formatPrice(money) {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(money)
    },
    setStatus(text) {
      let status = ''
      if (text === 'WAITING') {
        status = 'default'
      } else if (text === 'CONFIRM') {
        status = 'processing'
      } else if (text === 'SHIPPING') {
        status = 'warning'
      } else if (text === 'DONE') {
        status = 'success'
      } else {
        status = 'error'
      }
      return status
    },
    onChange(e) {
      this.status = e.target.value
      this.orderUpdate.status = e.target.value
    },
  },
}
</script>
