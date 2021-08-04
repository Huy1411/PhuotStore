export default function ({ $axios, redirect }, inject) {
  const axios = $axios.create({
    headers: {
      common: {
        Accept: 'application/json',
      },
    },
  })
  axios.setBaseURL(process.env.baseUrl)

  axios.onError((error) => {
    const code = parseInt(error.response && error.response.status)
    if ([403, 500, 401].includes(code)) {
      redirect('/400')
    }
  })
  const api = {
    // Auth API
    login: (data) => axios.$post('/api/v1/auth/signin', data),
    register: (data) => axios.$post('/api/v1/auth/signup', data),
    getUser: (id, config) => axios.$get(`/api/v1/users/${id}`, config),
    updateUser: (id, data, config) =>
      axios.$put(`api/v1/users/update/${id}`, data, config),
    // Category API
    categoryList: (config) => axios.$get('api/v1/categories', config),
    addCategory: (data, config) =>
      axios.$post('api/v1/categories/add', data, config),
    getCategory: (id, config) => axios.$get(`api/v1/categories/${id}`, config),
    updateCategory: (id, data, config) =>
      axios.$put(`api/v1/categories/update/${id}`, data, config),
    deleteCategory: (id, config) =>
      axios.$delete(`api/v1/categories/delete/${id}`, config),
    // Brand API
    brandList: (config) => axios.$get('api/v1/brands', config),
    addBrand: (data, config) => axios.$post('api/v1/brands/add', data, config),
    getBrand: (id, config) => axios.$get(`api/v1/brands/${id}`, config),
    updateBrand: (id, data, config) =>
      axios.$put(`api/v1/brands/update/${id}`, data, config),
    deleteBrand: (id, config) =>
      axios.$delete(`api/v1/brands/delete/${id}`, config),
    // Product API
    productList: (config) => axios.$get('api/v1/products', config),
    addProduct: (data, config) =>
      axios.$post('api/v1/products/add', data, config),
    getProduct: (id, config) => axios.$get(`api/v1/products/${id}`, config),
    updateProduct: (id, data, config) =>
      axios.$put(`api/v1/products/update/${id}`, data, config),
    deleteProduct: (id, config) =>
      axios.$delete(`api/v1/products/delete/${id}`, config),
    getProductByCategory: (id, config) =>
      axios.$get(`api/v1/products/categories/${id}`, config),
    // Order API
    orderList: (config) => axios.$get('api/v1/orders', config),
    orderPlace: (data, config) =>
      axios.$post('api/v1/orders/add/', data, config),
    getOrder: (id, config) => axios.$get(`api/v1/orders/${id}`, config),
    updateOrder: (id, data, config) =>
      axios.$put(`api/v1/orders/update/${id}`, data, config),
    filterOrderByStatus: (status, config) =>
      axios.$get(`api/v1/orders/${status}`, config),
    getOrderByUser: (id, config) =>
      axios.$get(`api/v1/orders/user/${id}`, config),
  }
  inject('api', api)
}
