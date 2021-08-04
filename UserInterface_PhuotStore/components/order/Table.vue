<template>
  <a-table :columns="columns" :data-source="orders">
    <a-tag slot="status" slot-scope="text" :color="setStatusColor(text)">
      {{ text }}</a-tag
    >
    <span slot="code" slot-scope="text">PS - {{ text }}</span>
    <span slot="user" slot-scope="text" style="text-transform: capitalize">{{
      text.username
    }}</span>
    <span slot="price" slot-scope="text">{{ formatPrice(text) }}</span>
    <span slot="action" slot-scope="record">
      <a-button type="primary" @click="editOrder(record.id)"
        ><a-icon type="edit"
      /></a-button>
    </span>
  </a-table>
</template>

<script>
const columns = [
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'Order Code',
    dataIndex: 'orderName',
    key: 'orderName',
    scopedSlots: { customRender: 'code' },
  },
  {
    title: 'Customer Name',
    dataIndex: 'user',
    key: 'user',
    scopedSlots: { customRender: 'user' },
  },
  {
    title: 'Phone Number',
    dataIndex: 'phone',
    key: 'phone',
  },
  {
    title: 'Email Address',
    dataIndex: 'email',
    key: 'email',
  },
  {
    title: 'Shipping Address',
    dataIndex: 'address',
    key: 'address',
  },
  {
    title: 'Grand Total',
    dataIndex: 'totalPrice',
    key: 'totalPrice',
    scopedSlots: { customRender: 'price' },
  },
  {
    title: 'Payment Type',
    dataIndex: 'paymentType',
    key: 'paymentType',
  },
  {
    title: 'Status',
    dataIndex: 'status',
    key: 'status',
    scopedSlots: { customRender: 'status' },
  },
  {
    title: 'Action',
    key: 'action',
    scopedSlots: { customRender: 'action' },
  },
]

export default {
  name: 'Table',
  props: {
    orders: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      columns,
      loading: false,
    }
  },
  methods: {
    editOrder(id) {
      this.$emit('editOrder', id)
    },
    formatPrice(money) {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(money)
    },
    setStatusColor(text) {
      let color = ''
      if (text === 'WAITING') {
        color = '#ebc638'
      } else if (text === 'CONFIRM') {
        color = 'purple'
      } else if (text === 'SHIPPING') {
        color = 'blue'
      } else if (text === 'DONE') {
        color = 'green'
      } else {
        color = 'red'
      }
      return color
    },
  },
}
</script>
