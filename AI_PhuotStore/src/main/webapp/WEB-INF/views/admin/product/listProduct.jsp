<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Product Manager"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Product Manager</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a>Home</a></li>
                        <li class="breadcrumb-item active">Product Manager</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="card card-warning collapsed-card">
                        <div class="card-header">
                            <h3 class="card-title">Look Up Product</h3>

                            <div class="card-tools">

                                <button type="button" class="btn btn-tool" data-card-widget="collapse"><i
                                    class="fas fa-plus"></i>
                                </button>

                                <button type="button" class="btn btn-tool" data-card-widget="remove"><i
                                    class="fas fa-times"></i>
                                </button>
                            </div>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body" style="display: none; color: #FFF">
                            <div class="col-md-12" style="margin-top: 10px;margin-bottom: 10px;">
                                <div class="row">
                                    <div class="col-md-4 col-sm-4 col-4">
                                        <a href="${pageContext.request.contextPath}/admin/product/"
                                           class="btn btn-block btn-primary">All</a>
                                    </div>
                                    <div class="col-md-4 col-sm-4 col-4">
                                        <a href="${pageContext.request.contextPath}/admin/product/show"
                                           class="btn btn-block btn-success">Show</a>
                                    </div>
                                    <div class="col-md-4 col-sm-4 col-4">
                                        <a href="${pageContext.request.contextPath}/admin/product/hidden"
                                           class="btn btn-block btn-danger">Hidden</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.card-footer-->
                    </div>
                </div>
                <div class="col-12">
                    <div class="row">
                        <div class="col-3">
                            <a class="btn btn-block btn-info btn-sm"
                               href="${pageContext.request.contextPath}/admin/product/insertProduct">Create Product</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">Product List</h3>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body p-0">
                        <table class="table table-striped projects">
                            <thead>
                            <tr>
                                <th style="width: 10px">No.</th>
                                <th>Product Name</th>
                                <th>Product Code</th>
                                <th>Product Description</th>
                                <th>Discount</th>

                                <th>Qty</th>
                                <th>Price</th>
                                <th>Category</th>
                                <th>Brand</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${products}" var="product" varStatus="itr">
                                <tr>
                                    <td>${offset+itr.index+1}
                                    </td>
                                    <td>
                                        <span class="text"> ${product.productName}</span>
                                    </td>
                                    <td>
                                        <span class="text"> ${product.productCode}</span>
                                    </td>
                                    <td>
                                        <span class="text"> ${product.productDesc}</span>
                                    </td>
                                    <td>
                                        <span class="text"> ${product.discount}</span>
                                    </td>
                                    <td>
                                        <span class="text"> ${product.qty}</span>
                                    </td>
                                    <td>
                                        <span class="text"> ${product.price}</span>
                                    </td>
                                    <td>
                                        <span class="text"> ${product.category.categoryName}</span>
                                    </td>
                                    <td>
                                        <span class="text"> ${product.brand.brandName}</span>
                                    </td>
                                    <td>
                                        <c:if test="${product.status ==1}">
                                            <span class="text-bold"> </span>
                                            <span class="badge badge-success">Show</span>
                                        </c:if>
                                        <c:if test="${product.status ==2}">
                                            <span class="text-bold"> </span>
                                            <span class="badge badge-danger">Hidden</span>
                                        </c:if>
                                    </td>
                                    <td class="project-actions ">
                                        <a href="${pageContext.request.contextPath}/admin/product/editProduct?id=${product.productID}"
                                           class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"> </i> Update</a>
                                        <a href="${pageContext.request.contextPath}/admin/product/deleteProduct?id=${product.productID}"
                                           class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> Delete</a>
                                        <a href="${pageContext.request.contextPath}/admin/product/detailProduct?id=${product.productID}"
                                           class="btn btn-primary btn-sm"><i class="fas fa-folder"> </i>Detail</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.card-body -->
                    <div class="card-footer clearfix">
                        <ul class="pagination pagination-sm m-0 float-right">
                            <c:if test="${totalPages > 1}">
                                <c:if test="${currentPage>1}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/admin/product/page/${currentPage-1}">«</a>
                                    </li>
                                </c:if>
                                <c:forEach end="${totalPages}" begin="1" varStatus="loop">
                                    <c:if test="${currentPage != loop.index}">
                                        <li class="page-item "><a class="page-link"
                                                                  href="/admin/product/page/${loop.index}">${loop.index}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${currentPage == loop.index}">
                                        <li class="page-item active"><a class="page-link"
                                                                        href="/admin/product/page/${loop.index}">${loop.index}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${currentPage<totalPages}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/admin/product/page/${currentPage+1}">»</a>
                                    </li>
                                </c:if>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <!-- /.card -->
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>
</body>
</html>


