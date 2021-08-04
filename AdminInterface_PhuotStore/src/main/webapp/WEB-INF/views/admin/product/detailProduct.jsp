<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Product Manager"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">

                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-left">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/product">Product</a>
                        </li>
                        <li class="breadcrumb-item active">Product Detail</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <!-- Main content -->
                    <div class="invoice p-3 mb-3">
                        <!-- title row -->
                        <div class="row">
                            <div class="col-12">
                                <h4>
                                    <i class="fas fa-spa"></i> Product Information
                                </h4>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- info row -->
                        <div class="row invoice-info">
                            <!-- /.col -->
                            <div class="col-sm-4 invoice-col" style="padding: 20px">
                                <b>Product ID :</b> ${detailProduct.productID}<br>
                                <b>Procuct Name :</b> ${detailProduct.productName}<br>
                                <b>Product Code :</b> ${detailProduct.productCode}<br>
                                <b>Product Description :</b> ${detailProduct.productDesc}<br>
                                <b>Discount (%) :</b> ${detailProduct.discount} %<br>
                                <b>Qty :</b> ${detailProduct.qty}<br>
                                <b>Price :</b> ${detailProduct.price}<br>
                                <b>Status :</b>
                                <c:if test="${detailProduct.status =='SHOW'}">
                                    <span class="badge badge-success">SHOW</span>
                                </c:if>
                                <c:if test="${detailProduct.status =='HIDDEN'}">
                                    <span class="badge badge-danger">HIDDEN</span>
                                </c:if>
                                <br>
                                <b>Brand :</b>
                                <!-- Table row -->
                                <div class="row">
                                    <div class="card-body table-responsive p-0">
                                        <table class="table table-hover text-nowrap">
                                            <thead style="background-color: white">
                                            <tr>
                                                <td>Brand Name</td>
                                                <td>Brand Code</td>
                                                <td>Brand Description</td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><a href="${pageContext.request.contextPath}/admin/brand/detailBrand?id=${detailProduct.brand.brandID}">${detailProduct.brand.brandName}</a></td>
                                                    <td><a href="${pageContext.request.contextPath}/admin/brand/detailBrand?id=${detailProduct.brand.brandID}">${detailProduct.brand.brandCode}</a></td>
                                                    <td>${detailProduct.brand.brandDesc}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.col -->
                                </div>
                                <!-- /.row -->
                                <br>
                                <b>Category :</b>
                                <!-- Table row -->
                                <div class="row">
                                    <div class="card-body table-responsive p-0">
                                        <table class="table table-hover text-nowrap">
                                            <thead style="background-color: white">
                                            <tr>
                                                <td>Category Name</td>
                                                <td>Category Code</td>
                                                <td>Category Description</td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><a href="${pageContext.request.contextPath}/admin/category/detailCategory?id=${detailProduct.category.categoryID}">${detailProduct.category.categoryName}</a></td>
                                                    <td><a href="${pageContext.request.contextPath}/admin/category/detailCategory?id=${detailProduct.category.categoryID}">${detailProduct.category.categoryCode}</a></td>
                                                    <td>${detailProduct.category.categoryDesc}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.col -->
                                </div>
                                <!-- /.row -->
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->

                    </div>
                    <!-- /.invoice -->
                    <div class="card-footer">
                        <a href="${pageContext.request.contextPath}/admin/product/editProduct?id=${detailProduct.productID}"
                           class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"></i> Update</a>
                        <a href="${pageContext.request.contextPath}/admin/product/deleteProduct?id=${detailProduct.productID}"
                           class="btn btn-secondary btn-sm"><i class="fas fa-trash"></i> Delete</a>
                    </div>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</div>

<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>

</body>
</html>



