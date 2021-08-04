<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Brand Manager"/>
<%@include file="../../layout/admin/header.jsp" %>

<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-12">
                    <ol class="breadcrumb float-sm-left">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                        <li class="breadcrumb-item active">Brand</li>
                    </ol>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="card card-warning collapsed-card">
                    <div class="card-body" style="display: flex ; color: #FFF">
                        <div class="col-md-12" style="margin-top: 10px;margin-bottom: 10px;">
                            <div class="row">
                                <div class="col-md-4 col-sm-4 col-4">
                                    <a href="${pageContext.request.contextPath}/admin/brand/"
                                       class="btn btn-block btn-outline-primary btn-lg"><b>ALL</b></a>
                                </div>
                                <div class="col-md-4 col-sm-4 col-4">
                                    <a href="${pageContext.request.contextPath}/admin/brand/show"
                                       class="btn btn-block btn-outline-success btn-lg"><b>SHOW</b></a>
                                </div>
                                <div class="col-md-4 col-sm-4 col-4">
                                    <a href="${pageContext.request.contextPath}/admin/brand/hidden"
                                       class="btn btn-block btn-outline-danger btn-lg"><b>HIDDEN</b></a>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- /.card-footer-->
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-7">
                <div class="card card-danger">
                    <div class="card-header">
                        <h3 class="card-title">Brand List</h3>
                        <div class="card-tools">
                            <div class="input-group input-group-sm" style="width: 150px;">
                                <input type="text" name="table_search" class="form-control float-right"
                                       placeholder="Search">

                                <div class="input-group-append">
                                    <button type="submit" class="btn btn-default"><i class="fas fa-search"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body table-responsive">
                        <table class="table table-hover text-nowrap">
                            <thead style="background-color: white">
                            <tr>
                                <th style="width: 10px">No.</th>
                                <th>Brand Name</th>
                                <th>Brand Code</th>
                                <th>Brand Description</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${brands}" var="brand" varStatus="itr">
                                <tr>
                                    <td>${offset+itr.index+1}</td>
                                    <td><span class="text"> ${brand.brandName}</span></td>

                                    <td><span class="text"> ${brand.brandCode}</span></td>
                                    <td><span class="text"> ${brand.brandDesc}</span></td>
                                    <td>
                                        <c:if test="${brand.status =='SHOW'}">
                                            <span class="text"> </span><span class="badge badge-success">SHOW</span>
                                        </c:if>
                                        <c:if test="${brand.status =='HIDDEN'}">
                                            <span class="text"> </span><span class="badge badge-danger">HIDDEN</span>
                                        </c:if>
                                    </td>

                                    <td class="project-actions">
                                        <a href="${pageContext.request.contextPath}/admin/brand/editBrand?id=${brand.brandID}"
                                           class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"></i></a>
                                        <a href="${pageContext.request.contextPath}/admin/brand/detailBrand?id=${brand.brandID}"
                                           class="btn btn-secondary btn-sm"><i class="fas fa-trash"></i></a>
                                        <a href="${pageContext.request.contextPath}/admin/brand/detailBrand?id=${brand.brandID}"
                                           class="btn btn-primary btn-sm"><i class="fas fa-folder"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer clearfix">
                        <ul class="pagination pagination-sm m-0 float-right">
                            <c:if test="${totalPages > 1}">
                                <c:if test="${currentPage>1}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/admin/brand/page/${currentPage-1}">«</a></li>
                                </c:if>
                                <c:forEach end="${totalPages}" begin="1" varStatus="loop">
                                    <c:if test="${currentPage != loop.index}">
                                        <li class="page-item "><a class="page-link"
                                                                  href="/admin/brand/page/${loop.index}">${loop.index}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${currentPage == loop.index}">
                                        <li class="page-item active"><a class="page-link"
                                                                        href="/admin/brand/page/${loop.index}">${loop.index}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${currentPage<totalPages}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/admin/brand/page/${currentPage+1}">»</a></li>
                                </c:if>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <!-- /.card -->
            </div>
            <div class="col-md-5">
                <!-- general form elements -->
                <div class="card card-info">
                    <div class="card-header">
                        <h3 class="card-title">Create New Brand</h3>
                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip"
                                    title="Collapse">
                                <i class="fas fa-minus"></i></button>
                        </div>
                    </div>
                    <div class="card-body" style="display: block;">
                        <!-- /.card-header -->
                        <!-- form start -->
                        <f:form action="${pageContext.request.contextPath}/admin/brand/saveBrand" method="POST"
                                modelAttribute="newBrand">
                            <spring:bind path="brandName">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Brand Name</label>
                                    <f:input path="brandName" type="text"
                                             class="form-control  ${status.error ?'border border-danger':''} ${param.errorbrandname !=null ?'border border-danger':''}"
                                             id="exampleInputEmail1" placeholder="Enter brand name ..."/>
                                    <f:errors path="brandName" class="text-danger"></f:errors>
                                    <p class="text-danger">${param.errorbrandname}</p>
                                </div>
                            </spring:bind>
                            <spring:bind path="brandCode">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Brand Code</label>
                                    <f:input path="brandCode" type="text"
                                             class="form-control  ${status.error ?'border border-danger':''} ${param.errorbrandname !=null ?'border border-danger':''}"
                                             id="exampleInputEmail1" placeholder="Enter brand code ..."/>
                                    <f:errors path="brandCode" class="text-danger"></f:errors>
                                    <p class="text-danger">${param.errorbrandname}</p>
                                </div>
                            </spring:bind>
                            <spring:bind path="brandDesc">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Brand Description </label>
                                    <f:textarea path="brandDesc" style="height:auto" type="text"
                                                class="form-control  ${status.error ?'border border-danger':''} ${param.errorbranddesc !=null ?'border border-danger':''}"
                                                id="desId" placeholder="Enter brand description ..."/>
                                    <f:errors path="brandDesc" class="text-danger"></f:errors>
                                    <p class="text-danger">${param.errorbranddesc}</p>
                                </div>
                            </spring:bind>
                            <spring:bind path="status">
                                <div class="form-group">
                                <div class="row">
                                    <div class="col-md-2">
                                        <label>Status </label>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="custom-control custom-radio">
                                            <f:radiobutton class="custom-control-input" path="status" value="SHOW"
                                                           checked="true" id="customRadio1"/>
                                            <label for="customRadio1" class="custom-control-label"
                                                   style="color: green">SHOW</label>
                                        </div>
                                        <div class="custom-control custom-radio">
                                            <f:radiobutton class="custom-control-input" path="status" value="HIDDEN"
                                                           id="customRadio2"/>
                                            <label for="customRadio2" class="custom-control-label"
                                                   style="color:red">HIDDEN</label>
                                        </div>
                                        <f:errors path="status" class="text-danger"></f:errors>
                                    </div>
                                </div>
                                </div>
                            </spring:bind>
                            <!-- /.card-body -->
                            <div class="card-footer" style="background-color: white">
                                <button type="submit" class="btn btn-info" s>Submit</button>
                            </div>
                        </f:form>
                    </div>
                    <!-- /.card -->
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layout/admin/footer.jsp"/>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<%@include file="../../layout/admin/infoActionc.jsp" %>

</body>
</html>
