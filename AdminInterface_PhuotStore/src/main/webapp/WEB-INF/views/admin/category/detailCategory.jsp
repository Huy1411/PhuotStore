<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Brand Manager"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">

                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-left">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/category">Category</a>
                        </li>
                        <li class="breadcrumb-item active">Category Detail</li>
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
                                    <i class="fas fa-spa"></i> Category Information
                                </h4>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- info row -->
                        <div class="row invoice-info">
                            <!-- /.col -->
                            <div class="col-sm-4 invoice-col" style="padding: 20px">
                                <b>Category ID :</b> ${detailCategory.categoryID}<br>
                                <b>Category Name :</b> ${detailCategory.categoryName}<br>
                                <b>Category Code :</b> ${detailCategory.categoryCode}<br>
                                <b>Category Description :</b> ${detailCategory.categoryDesc}<br>
                                <b>Status :</b>
                                <c:if test="${detailCategory.status =='SHOW'}">
                                    <span class="badge badge-success">SHOW</span>
                                </c:if>
                                <c:if test="${detailCategory.status =='HIDDEN'}">
                                    <span class="badge badge-danger">HIDDEN</span>
                                </c:if>
                                <br>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.invoice -->
                    <div class="card-footer">
                        <a href="${pageContext.request.contextPath}/admin/category/editCategory?id=${detailCategory.categoryID}"
                           class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"></i> Update</a>
                        <a href="${pageContext.request.contextPath}/admin/category/deleteCategory?id=${detailCategory.categoryID}"
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



