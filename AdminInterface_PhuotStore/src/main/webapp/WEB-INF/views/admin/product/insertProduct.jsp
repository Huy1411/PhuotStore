<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Product Manager"/>

<%@include file="../../layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-left">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/product">Product</a></li>
                        <li class="breadcrumb-item active">Create Product</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <section class="content">
        <f:form action="${pageContext.request.contextPath}/admin/product/saveProduct" method="POST"
                enctype="multipart/form-data" modelAttribute="newProduct">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="card card-info">
                        <div class="card-header">
                            <h3 class="card-title">Create Product </h3>
                        </div>
                        <div class="card-body">
                            <div class="col-md-12">
                                <div class="row">
                                    <spring:bind path="productName">
                                        <div class="form-group col-md-6">
                                            <label for="exampleInputEmail1">Product Name </label>
                                            <f:input path="productName" type="text"
                                                     class="form-control ${status.error ?'border border-danger':''} ${param.errorproductname !=null ?'border border-danger':''}"
                                                     placeholder="Enter product name ..."/>
                                            <f:errors path="productName" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errorproductname}</p>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="productCode">
                                        <div class="form-group col-md-6">
                                            <label for="exampleInputEmail1">Product Code </label>
                                            <f:input path="productCode" type="text"
                                                     class="form-control ${status.error ?'border border-danger':''} ${param.errorproductcode !=null ?'border border-danger':''}"
                                                     placeholder="Enter product code ... "/>
                                            <f:errors path="productCode" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errorproductcode}</p>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="productDesc">
                                        <div class="form-group col-md-12">
                                            <label for="exampleInputEmail1">Product Description </label>
                                            <f:textarea path="productDesc" style="height:500px" type="text"
                                                        class="form-control  ${status.error ?'border border-danger':''} ${param.errorproductdesc !=null ?'border border-danger':''}"
                                                        id="desId" placeholder="Enter product description"/>
                                            <f:errors path="productDesc" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errorproductdesc}</p>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="discount">
                                        <div class="form-group col-md-6">
                                            <label>Discount (%)</label>
                                            <f:input path="discount" type="number"
                                                     class="form-control ${status.error ?'border border-danger':''}${param.errordiscount !=null ?'border border-danger':''}"
                                                     value="0" placeholder="Enter discount ..."/>
                                            <f:errors path="discount" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errordiscount}</p>

                                        </div>
                                    </spring:bind>
                                    <div class="form-group col-md-6">
                                        <div class="row">
                                            <div class="col-md-2">
                                                <label for="exampleInputEmail1">Status</label>
                                            </div>
                                            <div class=" form-check col-md-5">
                                                <f:radiobutton path="status" value="SHOW" class="form-check-input"
                                                               checked="true"
                                                               id="exampleInputEmail1"/>
                                                <label class="form-check-label font-weight-bold" style="color: green ">SHOW</label>
                                                <br>
                                                <f:radiobutton path="status" value="HIDDEN" class="form-check-input"
                                                               id="exampleInputEmail1"/>
                                                <label class="form-check-label font-weight-bold" style="color:red">HIDDEN</label>
                                            </div>
                                        </div>
                                    </div>
                                    <spring:bind path="qty">
                                        <div class="form-group col-md-6">
                                            <label>Qty </label>
                                            <f:input path="qty" type="number"
                                                     class="form-control ${status.error ?'border border-danger':''}${param.errorqty !=null ?'border border-danger':''}"
                                                     placeholder="Enter qty ..."/>
                                            <f:errors path="qty" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errorqty}</p>

                                        </div>
                                    </spring:bind>
                                    <spring:bind path="price">
                                        <div class="form-group col-md-6">
                                            <label>Price</label>
                                            <f:input path="price" type="number"
                                                     class="form-control ${status.error ?'border border-danger':''}${param.errorprice !=null ?'border border-danger':''}"
                                                     placeholder="Enter price product ...."/>
                                            <f:errors path="price" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errorprice}</p>
                                        </div>
                                    </spring:bind>
                                    <div class="form-group col-md-6">
                                        <label>Category</label>
                                        <f:select class="custom-select" path="category.categoryID">
                                            <f:options items="${categories}" itemLabel="categoryName"
                                                       itemValue="categoryID"/>
                                        </f:select>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label>Brand</label>
                                        <f:select class="custom-select" path="brand.brandID">
                                            <f:options items="${brands}" itemLabel="brandName"
                                                       itemValue="brandID"/>
                                        </f:select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <button type="submit" class="btn btn-info">Create</button>
                    </div>
                </div>
            </div>
            </f:form>
    </section>

</div>
<%@include file="../../layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<%@include file="../../layout/admin/infoActionc.jsp" %>

<script type="text/javascript">
    $(function () {
        // Summernote
        $('#desId').summernote();
        click();
    })
</script>
</body>
</html>
