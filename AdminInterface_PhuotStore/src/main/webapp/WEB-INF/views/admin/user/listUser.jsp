<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="User Manager"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-left">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                        <li class="breadcrumb-item active">User</li>
                    </ol>
                </div>
            </div>
        </div>
    </section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-3">
                <a class="btn btn-block btn-info btn-sm"
                   href="${pageContext.request.contextPath}/admin/user/insertUser">Create User</a>
            </div>
        </div>

        <!-- /.card-header -->
        <div class="card-body card-danger">
            <div class="card-header card-danger">
                <h3 class="card-title">User List</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm" style="width: 150px;">
                        <input type="text" name="table_search" class="form-control float-right" placeholder="Search">

                        <div class="input-group-append">
                            <button type="submit" class="btn btn-default"><i class="fas fa-search"></i></button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.card-header -->
            <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                    <thead style="background-color: white">
                    <tr>
                        <th style="width: 10px">No.</th>
                        <th>User Name</th>
                        <th>Avatar</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user" varStatus="itr">
                        <tr>
                            <td>${offset+itr.index+1}
                            </td>
                            <td>
                                <span class="text"> ${user.username}</span>
                            </td>
                            <td>
                                <span class="text"> ${user.avatar}</span>
                            </td>
                            <td>
                                <span class="text"> ${user.email}</span>
                            </td>
                            <td>
                                <span class="text"> ${user.phone}</span>
                            </td>
                            <td>
                                <span class="text"> ${user.address}</span>
                            </td>
                            <td class="project-actions ">
                                <a href="${pageContext.request.contextPath}/admin/user/editUser?id=${user.userID}"
                                   class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"></i></a>
                                <a href="${pageContext.request.contextPath}/admin/user/detailUser?id=${user.userID}"
                                   class="btn btn-danger btn-sm"><i class="fas fa-trash"></i></a>
                                <a href="${pageContext.request.contextPath}/admin/user/detailUser?id=${user.userID}"
                                   class="btn btn-primary btn-sm"><i class="fas fa-folder"></i></a>
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
                                                     href="/admin/user/page/${currentPage-1}">??</a>
                            </li>
                        </c:if>
                        <c:forEach end="${totalPages}" begin="1" varStatus="loop">
                            <c:if test="${currentPage != loop.index}">
                                <li class="page-item "><a class="page-link"
                                                          href="/admin/user/page/${loop.index}">${loop.index}</a>
                                </li>
                            </c:if>
                            <c:if test="${currentPage == loop.index}">
                                <li class="page-item active"><a class="page-link"
                                                                href="/admin/user/page/${loop.index}">${loop.index}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${currentPage<totalPages}">
                            <li class="page-item"><a class="page-link"
                                                     href="/admin/user/page/${currentPage+1}">??</a>
                            </li>
                        </c:if>
                    </c:if>
                </ul>
            </div>
        </div>
        <!-- /.card -->
    </div>
</div>
<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>
</body>
</html>



