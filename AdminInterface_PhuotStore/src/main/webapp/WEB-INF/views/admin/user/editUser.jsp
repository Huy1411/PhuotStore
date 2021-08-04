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
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/user">User</a></li>
                    <li class="breadcrumb-item active"> Edit User</li>
                </ol>
            </div>
        </div>
    </div><!-- /.container-fluid -->
</section>
<f:form action="${pageContext.request.contextPath}/admin/user/updateUser" method="POST"
        enctype="multipart/form-data"
        modelAttribute="editUser">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card card-info">
                    <div class="card-header">
                        <h3 class="card-title">Edit User </h3>
                    </div>
                    <div class="card-body">
                        <f:input path="userID" type="hidden"/>
                        <div class="col-md-12">
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label for="exampleInputEmail1">User Name</label>
                                    <f:input path="username" type="text"
                                             class="form-control ${param.errorusername !=null ?'border border-danger':''}"
                                             placeholder="Enter user name ..."/>
                                    <p class="text-danger">${param.errorusername}</p>
                                </div>
<%--                                <div class="form-group col-md-6">--%>
<%--                                    <label for="exampleInputEmail1">Avatar</label>--%>
<%--                                    <br/>--%>
<%--                                    <f:input path="avatar" type="file" id="fileImage"--%>
<%--                                             class=" ${status.error ?'border border-danger':''} ${param.erroravatar !=null ?'border border-danger':''}"--%>
<%--                                             placeholder="Enter avatar ... "/>--%>
<%--                                    <f:errors path="avatar" class="text-danger"> </f:errors>--%>
<%--                                    <p class="text-danger">${param.erroravatar}</p>--%>
<%--                                </div>--%>
                                <div class="form-group col-md-6">
                                    <label for="exampleInputEmail1">Email </label>
                                    <f:input path="email" type="text"
                                             class="form-control ${status.error ?'border border-danger':''} ${param.erroremail !=null ?'border border-danger':''}"
                                             placeholder="Enter email..."/>
                                    <f:errors path="email" class="text-danger"></f:errors>
                                    <p class="text-danger">${param.erroremail}</p>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="exampleInputEmail1">Password </label>
                                    <f:input path="password" type="password"
                                             class="form-control ${status.error ?'border border-danger':''} ${param.errorpassword !=null ?'border border-danger':''}"
                                             placeholder="Enter password..."/>
                                    <f:errors path="password" class="text-danger"></f:errors>
                                    <p class="text-danger">${param.errorpassword}</p>
                                </div>
                                <div class="form-group col-md-6">
                                    <label id="exampleInputEmail1">Phone </label>
                                    <f:input path="phone" type="text"
                                             class="form-control ${status.error ?'border border-danger':''} ${param.errorphone !=null ?'border border-danger':''}"
                                             placeholder="Enter phone..."/>
                                    <f:errors path="phone" class="text-danger"></f:errors>
                                    <p class="text-danger">${param.errorphone}</p>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="exampleInputEmail1">Address </label>
                                    <f:input path="address" type="text"
                                             class="form-control ${status.error ?'border border-danger':''} ${param.erroraddress !=null ?'border border-danger':''}"
                                             placeholder="Enter address..."/>
                                    <f:errors path="address" class="text-danger"></f:errors>
                                    <p class="text-danger">${param.erroraddress}</p>
                                </div>

                                <div class="form-group col-md-12">
                                    <div class="select2-purple">
                                        <label>Role</label>
                                        <f:select class="select2bs4" multiple="multiple"
                                                  style="width: 100%;" path="roles">
                                            <c:forEach var="role" items="${roles}">
                                                <f:option value="${role.roleID}">${role.roleName}</f:option>
                                            </c:forEach>
                                        </f:select>
                                    </div>
                                </div>

                            </div>
                        </div>
                            <%--                        <div class="col-md-12">--%>
                            <%--                            <div class="row">--%>
                            <%--                                <div class="form-group col-md-6">--%>
                            <%--                                    <label>Price:</label>--%>
                            <%--                                    <f:input path="price" type="number" class="form-control " placeholder="Enter price product...."/>--%>
                            <%--                                </div>--%>
                            <%--                                <div class="form-group col-md-6">--%>
                            <%--                                    <label >Discount(%):</label>--%>
                            <%--                                    <f:input path="discount" type="number" class="form-control " value="0" placeholder="Enter discount..."/>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                        </div>--%>
                            <%--                        <div class="col-md-12">--%>
                            <%--                            <div class="row">--%>
                            <%--                                <div class="form-group col-md-6">--%>
                            <%--                                    <label >Priority:</label>--%>
                            <%--                                    <f:select path="priority" class="custom-select">--%>
                            <%--                                        <f:option value="5">Default</f:option>--%>
                            <%--                                        <f:option value="4">Top 4</f:option>--%>
                            <%--                                        <f:option value="3">Top 3</f:option>--%>
                            <%--                                        <f:option value="2">Top 2</f:option>--%>
                            <%--                                        <f:option value="1">Top 1</f:option>--%>
                            <%--                                    </f:select>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                        </div>--%>
                            <%--                        <div class="col-md-12">--%>
                            <%--                            <div class="row">--%>
                            <%--                                <div class="form-group col-md-12">--%>
                            <%--                                    <label >Description:</label>--%>
                            <%--                                    <f:textarea path="descriptions" style="height:500px" type="text" class="form-control" id="desId" placeholder="Enter Open Time"/>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                        </div>--%>
                            <%--                        <div class="col-md-12">--%>
                            <%--                            <div class="row">--%>
                            <%--                                <div class="form-group col-md-12">--%>
                            <%--                                    <label >File Image:</label>--%>
                            <%--                                    <input name="file_avatar" id="fileImage" type="file" class="form-control" placeholder="Nhập Giá Bán Sản Phẩm"/>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                        </div>--%>

                    </div>
                </div>
                <div class="card-footer">
                    <button type="submit" class="btn btn-info">Update</button>
                </div>
            </div>
        </div>
    </div>
    </div>
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
