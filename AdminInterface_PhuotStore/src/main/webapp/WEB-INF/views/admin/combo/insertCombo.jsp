<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Combo Manager"/>
<%@include file="../../layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-left">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/combo">Combo</a></li>
                        <li class="breadcrumb-item active">Create Combo</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <f:form action="${pageContext.request.contextPath}/admin/combo/saveCombo" method="POST"
            enctype="multipart/form-data" modelAttribute="newCombo">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="card card-info">
                        <div class="card-header">
                            <h3 class="card-title">Create Combo </h3>
                        </div>
                        <div class="card-body">
                            <div class="col-md-12">
                                <div class="row">
                                    <spring:bind path="comboName">
                                        <div class="form-group col-md-6">
                                            <label for="exampleInputEmail1">Combo Name </label>
                                            <f:input path="comboName" type="text"
                                                     class="form-control ${status.error ?'border border-danger':''} ${param.errorcomboname !=null ?'border border-danger':''}"
                                                     placeholder="Enter combo name ..."/>
                                            <f:errors path="comboName" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errorcomboname}</p>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="comboCode">
                                        <div class="form-group col-md-6">
                                            <label for="exampleInputEmail1">Combo Code </label>
                                            <f:input path="comboCode" type="text"
                                                     class="form-control ${status.error ?'border border-danger':''} ${param.errorcombocode !=null ?'border border-danger':''}"
                                                     placeholder="Enter combo code ... "/>
                                            <f:errors path="comboCode" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errorcombocode}</p>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="comboDesc">
                                        <div class="form-group col-md-12">
                                            <label for="exampleInputEmail1">Combo Description </label>
                                            <f:textarea path="comboDesc" style="height:500px" type="text"
                                                        class="form-control  ${status.error ?'border border-danger':''} ${param.errorcombodesc !=null ?'border border-danger':''}"
                                                        id="desId" placeholder="Enter combo description ... "/>
                                            <f:errors path="comboDesc" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errorcombodesc}</p>
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
                                    <spring:bind path="totalPrice">
                                        <div class="form-group col-md-6">
                                            <label>Price</label>
                                            <f:input path="totalPrice" type="number"
                                                     class="form-control ${status.error ?'border border-danger':''}${param.errortotalprice !=null ?'border border-danger':''}"
                                                     placeholder="Enter total price combo ...."/>
                                            <f:errors path="totalPrice" class="text-danger"></f:errors>
                                            <p class="text-danger">${param.errortotalprice}</p>
                                        </div>
                                    </spring:bind>
                                    <spring:bind path="products">
                                        <div class="form-group col-md-12">
                                            <div class="select2-purple">
                                                <label>Product</label>
                                                <f:select class="select2bs4" multiple="multiple"
                                                        data-placeholder="Select a State"
                                                        style="width: 100%;" path="products">
                                                    <c:forEach var="product" items="${products}">
                                                        <f:option value="${product.productID}">${product.productName}</f:option>
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
                            <%--                        <div class="col-md-12">--%>
                            <%--                            <div class="row">--%>
                            <%--                                <div class="form-group col-md-12">--%>
                            <%--                                    <label >File Image:</label>--%>
                            <%--                                    <input name="file_avatar" id="fileImage" type="file" class="form-control" placeholder="Nhập Giá Bán Sản Phẩm"/>--%>
                            <%--                                    <f:errors path="image" class="text-danger" ></f:errors>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                        </div>--%>


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
