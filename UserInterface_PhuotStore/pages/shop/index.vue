<template>
  <a-spin :spinning="loading">
    <div class="row">
      <shopContent :products="products" :total="total" />
      <shopSideBar :categories="categories" @getProduct="getProduct" />
    </div>
  </a-spin>
</template>

<script>
export default {
  name: 'index.vue',
  data() {
    return {
      categories: [],
      products: [],
      categoryID: '',
      loading: false,
      total: 0,
    }
  },
  async created() {
    await this.loadPage()
  },
  methods: {
    async loadPage() {
      try {
        this.loading = true
        const categories = await this.$api.categoryList({
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.categories = [...categories.content]
        if (this.categoryID === '') {
          const products = await this.$api.productList({
            headers: {
              Authorization: this.$auth.$storage.getUniversal('token').token,
            },
          })
          this.products = [...products.content]
          this.total = products.content.length
        }
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
      }
    },
    async getProduct(id) {
      this.categoryID = id
      try {
        this.loading = true

        if (id === 'ALL') {
          const products = await this.$api.productList({
            headers: {
              Authorization: this.$auth.$storage.getUniversal('token').token,
            },
          })
          this.products = [...products.content]
        } else {
          const products = await this.$api.getProductByCategory(id, {
            headers: {
              Authorization: this.$auth.$storage.getUniversal('token').token,
            },
          })
          this.products = [...products.content]
        }
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
      }
    },
  },
}
</script>
