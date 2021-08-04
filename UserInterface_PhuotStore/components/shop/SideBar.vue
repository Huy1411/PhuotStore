<template>
  <div class="col-lg-4">
    <div class="sidebar">
      <!-- Tags Start -->
      <div class="sidebar-widget widget-categories">
        <h5 class="widget-title">Our Categories</h5>
        <ul class="sidebar-widget-list">
          <li><a @click="getProduct('ALL')"> ALL</a></li>
          <li v-for="(category, index) in categories" :key="index">
            <a @click="getProduct(category.categoryID)">
              {{ category.categoryName }}</a
            >
          </li>
        </ul>
      </div>
      <!-- Tags End -->
      <!-- Search Start -->
      <div class="sidebar-widget widget-search">
        <h6 class="widget-title">Search</h6>
        <form method="post">
          <div class="sigma_search-adv-input">
            <input
              type="text"
              class="form-control"
              placeholder="Look for Products"
              name="search"
              value=""
            />
            <button type="submit" name="button">
              <i class="fa fa-search"></i>
            </button>
          </div>
        </form>
      </div>
      <!-- Search End -->

      <!-- Filter: Price Start -->
      <div class="sidebar-widget">
        <h5 class="widget-title">Price</h5>
        <input
          type="text"
          class="js-range-slider"
          name="freshness_range"
          value=""
          data-type="double"
          data-min="0"
          data-max="500"
          data-from="10"
          data-to="100"
          data-grid="true"
          data-postfix=" $"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SideBar.vue',
  props: {
    categories: {
      type: Array,
      default: () => [],
    },
  },
  methods: {
    getProduct(categoryID) {
      this.$emit('getProduct', categoryID)
    },
  },
}
</script>
