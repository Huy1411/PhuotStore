<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Combo Manager"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">

                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-left">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/combo">Combo</a>
                        </li>
                        <li class="breadcrumb-item active">Combo Detail</li>
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
                                    <i class="fas fa-spa"></i> Combo Information
                                </h4>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- info row -->
                        <div class="row invoice-info">
                            <!-- /.col -->
                            <div class="col-sm-4 invoice-col" style="padding: 20px">
                                <b>Combo ID :</b> ${detailCombo.comboID}<br>
                                <b>Combo Name :</b> ${detailCombo.comboName}<br>
                                <b>Combo Code :</b> ${detailCombo.comboCode}<br>
                                <b>Combo Description :</b> ${detailCombo.comboDesc}<br>
                                <b>Discount (%) :</b> ${detailCombo.discount}%<br>
                                <b>Qty :</b> ${detailCombo.qty}<br>
                                <b>Status :</b>
                                <c:if test="${detailCombo.status =='SHOW'}">
                                    <span class="badge badge-success">SHOW</span>
                                </c:if>
                                <c:if test="${detailCombo.status =='HIDDEN'}">
                                    <span class="badge badge-danger">HIDDEN</span>
                                </c:if>
                                <br>
                                <b>Products :
                                </b>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                        <!-- Table row -->
                        <div class="row">
                            <div class="card-body table-responsive p-0">
                                <table class="table table-hover text-nowrap">
                                    <thead style="background-color: white">
                                    <tr>
                                        <th style="width: 10px">No.</th>
                                        <th>Product Name</th>
                                        <th>Product Code</th>
                                        <th>Price</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${detailCombo.products}" var="product" varStatus="itr">
                                        <tr>
                                            <td>${offset+itr.index+1}</td>
                                            <td><a href="${pageContext.request.contextPath}/admin/product/detailProduct?id=${product.productID}">${product.productName}</a></td>
                                            <td><a href="${pageContext.request.contextPath}/admin/product/detailProduct?id=${product.productID}">${product.productCode}</a></td>
                                            <td>${product.price}</td>
                                        </tr>
                                    </c:forEach>
                                    <tr style="">
                                        <td></td>
                                        <td><strong> Total Price </strong></td>
                                        <td> </td>
                                        <td>${detailCombo.totalPrice}</td>

                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.invoice -->

                    <div class="card-footer">
                        <a href="${pageContext.request.contextPath}/admin/combo/editCombo?id=${detailCombo.comboID}"
                           class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"></i> Update</a>
                        <a href="${pageContext.request.contextPath}/admin/combo/deleteCombo?id=${detailCombo.comboID}"
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



