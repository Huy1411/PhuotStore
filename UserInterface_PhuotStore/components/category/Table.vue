<template>
  <a-table :columns="columns" :data-source="categories">
    <a-tag
      slot="status"
      slot-scope="text"
      :color="text === 'SHOW' ? 'green' : 'red'"
    >
      {{ text === 'SHOW' ? 'SHOW' : 'HIDDEN' }}</a-tag
    >
    <span slot="action" slot-scope="record">
      <a-button type="primary" @click="editCategory(record.id)"
        ><a-icon type="edit"
      /></a-button>
      <a-button type="danger" @click="deleteCategory(record.id)"
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
    categories: {
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
    editCategory(id) {
      this.$emit('editCategory', id)
    },
    deleteCategory(id) {
      this.$emit('deleteCategory', id)
    },
  },
}
</script>
