<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Category Manager"/>
<%@include file="../../layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-12">
                    <ol class="breadcrumb float-sm-left">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home" >Home</a></li>
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/category" >Category</a></li>
                        <li class="breadcrumb-item active">Edit Category</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <f:form action="${pageContext.request.contextPath}/admin/category/updateCategory" method="POST"
            enctype="multipart/form-data"
            modelAttribute="editCategory">
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card card-info">
                            <div class="card-header">
                                <h3 class="card-title">Edit Category </h3>
                            </div>
                            <div class="card-body">
                                <f:input path="categoryID" type="hidden"/>
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="form-group col-md-6">
                                            <label for="exampleInputEmail1">Category Name</label>
                                            <f:input path="categoryName" type="text"
                                                     class="form-control ${param.errorcategoryname !=null ?'border border-danger':''}"
                                                     placeholder="Enter category name ..."/>
                                            <p class="text-danger">${param.errorcategoryname}</p>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="exampleInputEmail1">Category Code</label>
                                            <f:input path="categoryCode" type="text"
                                                     class="form-control ${param.errorcategorycode !=null ?'border border-danger':''}"
                                                     placeholder="Enter category code ..."/>
                                            <p class="text-danger">${param.errorcategorycode}</p>
                                        </div>
                                        <div class="form-group col-md-12">
                                            <label for="exampleInputEmail1">Category Description</label>
                                            <f:textarea path="categoryDesc" style="height:500px" type="text"
                                                        class="form-control ${param.errorcategorydesc !=null ?'border border-danger':''}"
                                                        id="desId" placeholder="Enter category description ..."/>
                                            <p class="text-danger">${param.errorcategorydesc}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="row">
                                        <div class="form-group col-md-2">
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
                            </div>
                        </div>
                        <div class="card-footer">
                            <button type="submit" class="btn btn-info">Update</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </f:form>
</div>
<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>

<script type="text/javascript">
    $(function () {
        // Summernote
        $('#desId').summernote();

        click();
    })
</script>
</body>
</html>
