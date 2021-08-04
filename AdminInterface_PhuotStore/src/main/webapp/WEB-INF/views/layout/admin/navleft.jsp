<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="/" class="brand-link">
        <img src="<c:url value="/assets/dist/img/AdminLTELogo.png"/>"
             alt="AdminLTE Logo"
             class="brand-image img-circle elevation-3"
             style="opacity: .8">
        <span class="brand-text font-weight-light">Admin</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="https://picsum.photos/160/160" class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <a href="/logout" class="d-block">${pageContext.request.userPrincipal.name}</a>
                </c:if>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <li class="nav-item">
                    <a href="/" class="nav-link">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li class="nav-item has-treeview">
                    <a href="${pageContext.request.contextPath}/admin/brand" class="nav-link">
                        <i class="nav-icon fas fa-table"></i>
                        <p>
                            Brand Manager
                        </p>
                    </a>
                </li>
                <li class="nav-item has-treeview">
                    <a href="${pageContext.request.contextPath}/admin/category" class="nav-link">
                        <i class="nav-icon far fa-plus-square"></i>
                        <p>
                            Category Manager
                        </p>
                    </a>
                </li>

                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-th"></i>
                        <p>
                            Product Manager
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/admin/product/" class="nav-link">
                                <i class=" far fa-circle nav-icon"></i>
                                <p>All  Products</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/admin/product/show" class="nav-link">
                                <i class=" far fa-circle nav-icon"></i>
                                <p>List Products Show</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/admin/product/hidden" class="nav-link">
                                <i class=" far fa-circle nav-icon"></i>
                                <p>List Products Hidden</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-copy"></i>
                        <p>
                            Combo Manager
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="../../admin/combo/insertCombo" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Create Combo</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/admin/combo/" class="nav-link">
                                <i class=" far fa-circle nav-icon"></i>
                                <p>All  Combos</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/admin/combo/show" class="nav-link">
                                <i class=" far fa-circle nav-icon"></i>
                                <p>List Combos Show</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/admin/product/hidden" class="nav-link">
                                <i class=" far fa-circle nav-icon"></i>
                                <p>List Combos Hidden</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-images"></i>
                        <p>
                            Image Manager
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/admin/image" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>List Image</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="../../admin/image/insertImage" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Create Image</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-th"></i>
                        <p>
                            User Manager
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/admin/user/" class="nav-link">
                                <i class=" far fa-circle nav-icon"></i>
                                <p>All Users</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/admin/user/admin" class="nav-link">
                                <i class=" far fa-circle nav-icon"></i>
                                <p>List Admin</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/admin/user/manager" class="nav-link">
                                <i class=" far fa-circle nav-icon"></i>
                                <p>List Manager</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/admin/user/employee" class="nav-link">
                                <i class=" far fa-circle nav-icon"></i>
                                <p>List Empoloyee</p>
                            </a>
                        </li>
                    </ul>
                </li>

                <%--          <li class="nav-item has-treeview">--%>
                <%--          <a href="${pageContext.request.contextPath}/admin/order" class="nav-link">--%>
                <%--            <i class="nav-icon fas fa-chart-pie"></i>--%>
                <%--            <p>--%>
                <%--              Orders Manager--%>
                <%--            </p>--%>
                <%--          </a>--%>
                <%--          </li>--%>
                <%--          <li class="nav-item has-treeview">--%>
                <%--            <a href="${pageContext.request.contextPath}/admin/feedback" class="nav-link">--%>
                <%--              <i class="nav-icon fas fa-chart-pie"></i>--%>
                <%--              <p>--%>
                <%--                Feedback Manager--%>
                <%--              </p>--%>
                <%--            </a>--%>
                <%--          </li>--%>
                <%--          <li class="nav-item has-treeview">--%>
                <%--            <a href="#" class="nav-link"><i class="nav-icon fas fa-tree"></i><p>Account Admin<i class="right fas fa-angle-left"></i></p></a>--%>
                <%--            <ul class="nav nav-treeview">--%>
                <%--              <li class="nav-item">--%>
                <%--                <a href="${pageContext.request.contextPath}/admin/role" class="nav-link"><i class="far fa-circle nav-icon"></i><p>Role Manager</p></a>--%>
                <%--              </li>--%>
                <%--              <li class="nav-item">--%>
                <%--                <a href="${pageContext.request.contextPath}/admin/roleUser" class="nav-link"><i class="far fa-circle nav-icon"></i><p>Role User Manager</p></a>--%>
                <%--              </li>--%>
                <%--            </ul>--%>
                <%--          </li>--%>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>

<%--<!-- jQuery -->--%>
<%--<script src="plugins/jquery/jquery.min.js"></script>--%>
<%--<!-- jQuery UI 1.11.4 -->--%>
<%--<script src="plugins/jquery-ui/jquery-ui.min.js"></script>--%>
<%--<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->--%>
<%--<script>--%>
<%--    $.widget.bridge('uibutton', $.ui.button)--%>
<%--</script>--%>
<%--<!-- Bootstrap 4 -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>--%>
<%--<!-- ChartJS -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/chart.js/Chart.min.js"></script>--%>
<%--<!-- Sparkline -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/sparklines/sparkline.js"></script>--%>
<%--<!-- JQVMap -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/jqvmap/jquery.vmap.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>--%>
<%--<!-- jQuery Knob Chart -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/jquery-knob/jquery.knob.min.js"></script>--%>
<%--<!-- daterangepicker -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/moment/moment.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/daterangepicker/daterangepicker.js"></script>--%>
<%--<!-- Tempusdominus Bootstrap 4 -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>--%>
<%--<!-- Summernote -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/summernote/summernote-bs4.min.js"></script>--%>
<%--<!-- overlayScrollbars -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>--%>
<%--<!-- AdminLTE App -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/dist/js/adminlte.js"></script>--%>
<%--<!-- AdminLTE dashboard demo (This is only for demo purposes) -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/dist/js/pages/dashboard.js"></script>--%>
<%--<!-- AdminLTE for demo purposes -->--%>
<%--<script src="${pageContext.request.contextPath}/assets/dist/js/demo.js"></script>--%>
