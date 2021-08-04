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
          :model="category"
          :label-col="labelCol"
          :wrapper-col="wrapperCol"
        >
          <a-form-model-item label="Category Name">
            <a-input
              v-model="category.categoryName"
              placeholder="Enter Category Name"
            />
          </a-form-model-item>
          <a-form-model-item label="Category Code">
            <a-input
              v-model="category.categoryCode"
              placeholder="Enter Category Code"
            />
          </a-form-model-item>
          <a-form-model-item label="Category Desc">
            <a-input
              v-model="category.categoryDesc"
              placeholder="Enter Category Desc"
            />
          </a-form-model-item>
          <a-form-model-item v-if="action === 'edit'" label="Status">
            <a-select v-model="category.status" placeholder="Select Status">
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
      categoryID: '',
      title: '',
      action: '',
      category: {
        categoryName: '',
        categoryCode: '',
        categoryDesc: '',
        status: 'SHOW',
      },
    }
  },
  methods: {
    show() {
      this.resetData()
      this.categoryID = ''
      this.action = 'create'
      this.title = 'Create Category'
      this.visible = true
    },
    async showEdit(id) {
      try {
        this.loading = true
        this.action = 'edit'
        this.title = 'Edit Category'
        this.visible = true
        const result = await this.$api.getCategory(id, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.categoryID = result.categoryID
        this.category = {
          categoryName: result.categoryName,
          categoryCode: result.categoryCode,
          categoryDesc: result.categoryDesc,
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
        if (this.categoryID !== '') {
          await this.$api.updateCategory(this.categoryID, this.category, {
            headers: {
              Authorization: this.$auth.$storage.getUniversal('token').token,
            },
          })
          this.$message.success(`Update Category Successfully!`)
        } else {
          await this.$api.addCategory(this.category, {
            headers: {
              Authorization: this.$auth.$storage.getUniversal('token').token,
            },
          })
          this.$message.success(`Add Category Successfully!`)
        }
      } catch (e) {
        if (e.response.data) {
          this.$message.warning(e.response.data.details)
        }
      } finally {
        this.confirmLoading = false
        this.visible = false
        this.resetData()
        this.$emit('refreshCategory')
      }
    },
    close(e) {
      this.visible = false
      this.resetData()
    },
    resetData() {
      this.category = {
        categoryName: '',
        categoryCode: '',
        categoryDesc: '',
        status: 'SHOW',
      }
    },
  },
}
</script>
