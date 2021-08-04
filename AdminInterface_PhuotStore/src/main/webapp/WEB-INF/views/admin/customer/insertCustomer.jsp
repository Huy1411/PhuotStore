<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="User Manager"/>
<%@include file="../../layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-left">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/user">User</a>
                        </li>
                        <li class="breadcrumb-item active">Create User</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <f:form action="${pageContext.request.contextPath}/admin/user/saveUser" method="POST"
            enctype="multipart/form-data" modelAttribute="newUser">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="card card-info">
                        <div class="card-header">
                            <h3 class="card-title">Create User </h3>
                        </div>
                        <div class="card-body">
                            <div class="col-md-12">
                                <div class="row">
                                    <spring:bind path="username">
                                        <div class="form-group col-md-6">
                                            <label for="exampleInputEmail1">User Name </label>
                                            <f:input path="username" type="text"
                                                     class="form-control ${status.error ?'border border-danger':''} ${param.errorusername !=null ?'border border-danger':''}"
                                                     placeholder="Enter user name ..."/>
                                            <f:errors path="username" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errorusername}</p>
                                        </div>
                                    </spring:bind>
<%--                                    <spring:bind path="avatar">--%>
<%--                                        <div class="form-group col-md-6">--%>
<%--                                            <label for="exampleInputEmail1">Avatar</label>--%>
<%--                                            <br/>--%>
<%--                                            <f:input path="avatar" type="file" id="fileImage"--%>
<%--                                                     class=" ${status.error ?'border border-danger':''} ${param.erroravatar !=null ?'border border-danger':''}"--%>
<%--                                                     placeholder="Enter avatar ... "/>--%>
<%--                                            <f:errors path="avatar" class="text-danger"> </f:errors>--%>
<%--                                            <p class="text-danger">${param.erroravatar}</p>--%>
<%--                                        </div>--%>
<%--                                    </spring:bind>--%>
                                    <spring:bind path="email">
                                        <div class="form-group col-md-6">
                                            <label for="exampleInputEmail1">Email </label>
                                            <f:input path="email" type="text"
                                                     class="form-control ${status.error ?'border border-danger':''} ${param.erroremail !=null ?'border border-danger':''}"
                                                     placeholder="Enter email..."/>
                                            <f:errors path="email" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.erroremail}</p>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="password">
                                        <div class="form-group col-md-6">
                                            <label for="exampleInputEmail1">Password </label>
                                            <f:input path="password" type="password"
                                                     class="form-control ${status.error ?'border border-danger':''} ${param.errorpassword !=null ?'border border-danger':''}"
                                                     placeholder="Enter password..."/>
                                            <f:errors path="password" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errorpassword}</p>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="phone">
                                        <div class="form-group col-md-6">
                                            <label id="exampleInputEmail1">Phone </label>
                                            <f:input path="phone" type="text"
                                                     class="form-control ${status.error ?'border border-danger':''} ${param.errorphone !=null ?'border border-danger':''}"
                                                     placeholder="Enter phone..."/>
                                            <f:errors path="phone" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errorphone}</p>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="address">
                                        <div class="form-group col-md-6">
                                            <label for="exampleInputEmail1">Address </label>
                                            <f:input path="address" type="text"
                                                     class="form-control ${status.error ?'border border-danger':''} ${param.erroraddress !=null ?'border border-danger':''}"
                                                     placeholder="Enter address..."/>
                                            <f:errors path="address" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.erroraddress}</p>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="roles">
                                        <div class="form-group col-md-12">
                                            <div class="select2-purple">
                                                <label>Role</label>
                                                <f:select class="select2bs4" multiple="multiple"
                                                          data-placeholder="Select a State"
                                                          style="width: 100%;" path="roles">
                                                    <c:forEach var="role" items="${roles}">
                                                        <f:option
                                                            value="${role.roleID}">${role.roleName}</f:option>
                                                    </c:forEach>
                                                </f:select>
                                            </div>
                                        </div>
                                    </spring:bind>
                                </div>
                            </div>
                        </div>
                            <%--                        <div class="col-md-12">--%>
                            <%--                            <div class="row">--%>
                            <%--                                <spring:bind path="price">--%>
                            <%--                                    <div class="form-group col-md-6">--%>
                            <%--                                        <label >Price:</label>--%>
                            <%--                                        <f:input path="price" type="number" class="form-control ${status.error ?'border border-danger':''}" placeholder="Enter price product...."/>--%>
                            <%--                                        <f:errors path="price" class="text-danger" ></f:errors>--%>
                            <%--                                    </div>--%>
                            <%--                                </spring:bind>--%>
                            <%--                                <spring:bind path="discount">--%>
                            <%--                                    <div class="form-group col-md-6">--%>
                            <%--                                        <label >Discount(%):</label>--%>
                            <%--                                        <f:input path="discount" type="number" class="form-control ${status.error ?'border border-danger':''}" value="0" placeholder="Enter discount..."/>--%>
                            <%--                                        <f:errors path="discount" class="text-danger"  ></f:errors>--%>
                            <%--                                    </div>--%>
                            <%--                                </spring:bind>--%>
                            <%--                            </div>--%>
                            <%--                        </div>--%>
                            <%--                        <div class="col-md-12">--%>
                            <%--                            <div class="row">--%>
                            <%--                                <spring:bind path="priority">--%>
                            <%--                                    <div class="form-group col-md-6">--%>
                            <%--                                        <label >Priority:</label>--%>
                            <%--&lt;%&ndash;                                        <f:input path="priority" type="number" class="form-control ${status.error ?'border border-danger':''}" placeholder="Enter priority..."/>&ndash;%&gt;--%>
                            <%--                                        <f:select path="priority" class="custom-select">--%>
                            <%--                                            <f:option value="5">Default</f:option>--%>
                            <%--                                            <f:option value="4">Top 4</f:option>--%>
                            <%--                                            <f:option value="3">Top 3</f:option>--%>
                            <%--                                            <f:option value="2">Top 2</f:option>--%>
                            <%--                                            <f:option value="1">Top 1</f:option>--%>
                            <%--                                        </f:select>--%>
                            <%--                                        <f:errors path="priority" class="text-danger"  ></f:errors>--%>
                            <%--                                    </div>--%>
                            <%--                                </spring:bind>--%>
                            <%--                            </div>--%>
                            <%--                        </div>--%>
                            <%--                        <div class="col-md-12">--%>
                            <%--                            <div class="row">--%>
                            <%--                                <div class="form-group col-md-12">--%>
                            <%--                                    <label >Description:</label>--%>
                            <%--                                    <f:textarea path="descriptions" style="height:500px" type="text" class="form-control" id="desId" placeholder="Enter Open Time"/>--%>
                            <%--                                    <f:errors path="descriptions" class="text-danger"  ></f:errors>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                        </div>--%>

                            <%--                                        <div class="col-md-12">--%>
                            <%--                                            <div class="row">--%>
                            <%--                                                <div class="form-group col-md-12">--%>
                            <%--                                                    <label for="exampleInputEmail1">Avatar</label>--%>
                            <%--                                                    <f:input  name="file_avatar" id="fileImage" type="file" class="form-control" placeholder="Nhập Giá Bán Sản Phẩm"/>--%>
                            <%--                                                    <f:errors path="image" class="text-danger" ></f:errors>--%>
                            <%--                                                </div>--%>
                            <%--                                            </div>--%>
                            <%--                                        </div>--%>



                    </div>
                </div>
            </div>

            <div class="card-footer">
                <button type="submit" class="btn btn-info">Create</button>
            </div>
        </div>
    </f:form>
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
<script>
    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2()

        //Initialize Select2 Elements
        $('.select2bs4').select2({
            theme: 'bootstrap4'
        })

        //Datemask dd/mm/yyyy
        $('#datemask').inputmask('dd/mm/yyyy', {'placeholder': 'dd/mm/yyyy'})
        //Datemask2 mm/dd/yyyy
        $('#datemask2').inputmask('mm/dd/yyyy', {'placeholder': 'mm/dd/yyyy'})
        //Money Euro
        $('[data-mask]').inputmask()

        //Date range picker
        $('#reservationdate').datetimepicker({
            format: 'L'
        });
        //Date range picker
        $('#reservation').daterangepicker()
        //Date range picker with time picker
        $('#reservationtime').daterangepicker({
            timePicker: true,
            timePickerIncrement: 30,
            locale: {
                format: 'MM/DD/YYYY hh:mm A'
            }
        })
        //Date range as a button
        $('#daterange-btn').daterangepicker(
            {
                ranges: {
                    'Today': [moment(), moment()],
                    'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                    'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                    'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                    'This Month': [moment().startOf('month'), moment().endOf('month')],
                    'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                },
                startDate: moment().subtract(29, 'days'),
                endDate: moment()
            },
            function (start, end) {
                $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
            }
        )

        //Timepicker
        $('#timepicker').datetimepicker({
            format: 'LT'
        })

        //Bootstrap Duallistbox
        $('.duallistbox').bootstrapDualListbox()

        //Colorpicker
        $('.my-colorpicker1').colorpicker()
        //color picker with addon
        $('.my-colorpicker2').colorpicker()

        $('.my-colorpicker2').on('colorpickerChange', function (event) {
            $('.my-colorpicker2 .fa-square').css('color', event.color.toString());
        });

        $("input[data-bootstrap-switch]").each(function () {
            $(this).bootstrapSwitch('state', $(this).prop('checked'));
        });

    })
</script>
</body>
</html>
