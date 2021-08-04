<template>
  <a-form-model
    layout="inline"
    :model="formFilter"
    @submit="handleSubmit"
    @submit.native.prevent
  >
    <a-form-model-item>
      <a-input v-model="formFilter.search" placeholder="Search">
        <a-icon slot="prefix" type="user" style="color: rgba(0, 0, 0, 0.25)" />
      </a-input>
    </a-form-model-item>
    <a-form-model-item>
      <a-select
        default-value=""
        style="width: 200px"
        @change="handleChangeStatus"
      >
        <a-select-option value="">Filter by Status</a-select-option>
        <a-select-option value="waiting"> Waiting </a-select-option>
        <a-select-option value="shipping"> Shipping </a-select-option>
        <a-select-option value="confirm"> Confirm </a-select-option>
        <a-select-option value="done"> Done </a-select-option>
        <a-select-option value="cancel"> Cancel </a-select-option>
      </a-select>
    </a-form-model-item>
    <a-form-model-item>
      <a-select
        default-value=""
        style="width: 200px"
        @change="handleChangePayment"
      >
        <a-select-option value="">Filter by payment type</a-select-option>
        <a-select-option value="paynow"> PAYNOW </a-select-option>
        <a-select-option value="online"> ONLINE </a-select-option>
      </a-select>
    </a-form-model-item>
    <a-form-model-item>
      <a-button type="primary" html-type="submit"> Search </a-button>
    </a-form-model-item>
  </a-form-model>
</template>

<script>
export default {
  data() {
    return {
      formFilter: {
        search: '',
        status: '',
        paymentType: '',
        typeSearch: 'search',
      },
    }
  },
  watch: {
    'formFilter.search'(newValue) {
      this.formFilter.typeSearch = 'search'
    },
  },
  methods: {
    handleSubmit(e) {
      this.$emit('search', this.formFilter)
    },
    handleChangeStatus(value) {
      this.formFilter.status = value
      this.formFilter.typeSearch = 'filter'
    },
    handleChangePayment(value) {
      this.formFilter.paymentType = value
      this.formFilter.typeSearch = 'filter'
    },
  },
}
</script>
