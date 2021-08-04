<template>
  <a-table :columns="columns" :data-source="products">
    <a-tag
      slot="status"
      slot-scope="text"
      :color="text === 'SHOW' ? 'green' : 'red'"
    >
      {{ text === 'SHOW' ? 'SHOW' : 'HIDDEN' }}</a-tag
    >
    <span slot="action" slot-scope="record">
      <a-button type="primary" @click="editProduct(record.id)"
        ><a-icon type="edit"
      /></a-button>
      <a-button type="danger" @click="deleteProduct(record.id)"
        ><a-icon type="delete"
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
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: 'Description',
    dataIndex: 'desc',
    key: 'desc',
  },
  {
    title: 'Discount',
    dataIndex: 'discount',
    key: 'discount',
  },
  {
    title: 'Quantity',
    dataIndex: 'qty',
    key: 'qty',
  },
  {
    title: 'Price',
    dataIndex: 'price',
    key: 'price',
  },
  {
    title: 'Category',
    dataIndex: 'categoryName',
    key: 'categoryName',
  },
  {
    title: 'Brand',
    dataIndex: 'brandName',
    key: 'brandName',
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
    products: {
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
    editProduct(id) {
      this.$emit('editProduct', id)
    },
    deleteProduct(id) {
      this.$emit('deleteProduct', id)
    },
  },
}
</script>
