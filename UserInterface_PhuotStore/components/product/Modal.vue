<template>
  <div>
    <a-modal
      :title="title"
      :visible="visible"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
      @cancel="close"
    >
      <a-spin :spinning="loading">
        <a-form-model
          :model="product"
          :label-col="labelCol"
          :wrapper-col="wrapperCol"
        >
          <a-form-model-item label="Product Name">
            <a-input
              v-model="product.productName"
              placeholder="Enter Product Name"
            />
          </a-form-model-item>
          <a-form-model-item label="Product Code">
            <a-input
              v-model="product.productCode"
              placeholder="Enter Product Code"
            />
          </a-form-model-item>
          <a-form-model-item label="Product Desc">
            <a-input
              v-model="product.productDesc"
              placeholder="Enter Product Desc"
            />
          </a-form-model-item>
          <a-form-model-item label="Product Discount">
            <a-input v-model="product.discount" placeholder="Enter Discount" />
          </a-form-model-item>
          <a-form-model-item label="Product QTY">
            <a-input v-model="product.qty" placeholder="Enter QTY" />
          </a-form-model-item>
          <a-form-model-item label="Product Price">
            <a-input v-model="product.price" placeholder="Enter Price" />
          </a-form-model-item>
          <!--          <a-form-model-item label="Product Image">-->
          <!--            <a-upload-->
          <!--              name="file"-->
          <!--              accept="image/*"-->
          <!--              :multiple="true"-->
          <!--              @change="handleChange"-->
          <!--            >-->
          <!--              <a-button> <a-icon type="upload" /> Select Images </a-button>-->
          <!--            </a-upload>-->
          <!--          </a-form-model-item>-->
          <a-form-model-item label="Category">
            <a-select
              v-model="product.categoryID"
              placeholder="Select Category"
            >
              <a-select-option
                v-for="category in categories"
                :key="category.categoryID"
                :value="category.categoryID"
              >
                {{ category.categoryName }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
          <a-form-model-item label="Brand">
            <a-select v-model="product.brandID" placeholder="Select Brand">
              <a-select-option
                v-for="brand in brands"
                :key="brand.brandID"
                :value="brand.brandID"
              >
                {{ brand.brandName }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
          <a-form-model-item v-if="action === 'edit'" label="Status">
            <a-select v-model="product.status" placeholder="Select Status">
              <a-select-option value="SHOW"> SHOW </a-select-option>
              <a-select-option value="HIDDEN"> HIDDEN </a-select-option>
            </a-select>
          </a-form-model-item>
          <a-form-model-item v-if="action === 'edit'" label="Status">
            <a-select v-model="product.status" placeholder="Select Status">
              <a-select-option value="SHOW"> SHOW </a-select-option>
              <a-select-option value="HIDDEN"> HIDDEN </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-form-model>
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
      productID: '',
      title: '',
      action: '',
      categories: [],
      brands: [],
      fileList: [],
      fileURL: [],
      product: {
        productName: '',
        // images: [],
        productCode: '',
        productDesc: '',
        discount: '',
        qty: '',
        price: '',
        createAt: new Date(),
        updateAt: new Date(),
        brandID: '',
        categoryID: '',
        status: 'SHOW',
      },
    }
  },
  // watch: {
  //   fileList(newValue) {
  //     console.log(newValue)
  //   },
  // },
  async created() {
    await this.getData()
  },
  methods: {
    async getData() {
      try {
        this.loading = true
        const categories = await this.$api.categoryList({
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        const brands = await this.$api.brandList({
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.categories = [...categories.content]
        this.brands = [...brands.content]
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
      }
    },
    show() {
      this.resetData()
      this.productID = ''
      this.action = 'create'
      this.title = 'Create Product'
      this.visible = true
    },
    async showEdit(id) {
      try {
        this.loading = true
        this.action = 'edit'
        this.title = 'Edit Product'
        this.visible = true
        const result = await this.$api.getProduct(id, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.productID = result.productID
        this.product = {
          productName: result.productName,
          productCode: result.productCode,
          productDesc: result.productDesc,
          discount: result.discount,
          qty: result.qty,
          price: result.price,
          createAt: result.createAt,
          updateAt: result.updateAt,
          brandID: result.brand.brandID,
          categoryID: result.category.categoryID,
          status: result.status,
        }
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.loading = false
      }
    },
    async handleOk(e) {
      try {
        this.confirmLoading = true
        if (this.productID !== '') {
          await this.$api.updateProduct(this.productID, this.product, {
            headers: {
              Authorization: this.$auth.$storage.getUniversal('token').token,
            },
          })
          this.$message.success(`Update Product Successfully!`)
        } else {
          // this.uploadImage()
          // this.product.images = [...this.fileURL]
          await this.$api.addProduct(this.product, {
            headers: {
              Authorization: this.$auth.$storage.getUniversal('token').token,
            },
          })
          this.$message.success(`Add Product Successfully!`)
        }
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.confirmLoading = false
        this.visible = false
        this.resetData()
        // this.fileURL = []
        this.$emit('refreshProduct')
      }
    },
    close(e) {
      this.visible = false
      this.resetData()
      // this.fileURL = []
      // this.fileList = []
      // localStorage.removeItem('images')
    },
    resetData() {
      this.product = {
        productName: '',
        productCode: '',
        productDesc: '',
        discount: '',
        // images: [],
        qty: '',
        price: '',
        createAt: new Date(),
        updateAt: new Date(),
        brandID: '',
        categoryID: '',
        status: 'SHOW',
      }
    },
    // handleChange(info) {
    //   if (info.file.status !== 'uploading') {
    //     this.fileList = [...info.fileList]
    //   }
    //   if (info.file.status === 'done') {
    //     this.$message.success(`Add Image Successfully!`)
    //   }
    // },
    // uploadImage() {
    //   this.fileList.forEach((item) => {
    //     const fileRef = this.$fire
    //       .storage()
    //       .ref()
    //       .child(`images/${item.originFileObj.name}`)
    //     fileRef.put(item.originFileObj)
    //     fileRef.getDownloadURL().then((url) => {
    //       this.fileURL.push(url)
    //       console.log(this.fileURL)
    //       if (process.browser) {
    //         localStorage.setItem('images', JSON.stringify(this.fileURL))
    //         this.product.images = localStorage.getItem('images')
    //           ? localStorage.getItem('images')
    //           : []
    //       }
    //     })
    //   })
    // },
  },
}
</script>
