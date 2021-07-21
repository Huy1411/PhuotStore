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
                <h1>Combo Manager</h1>
            </div>
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a>Home</a></li>
                    <li class="breadcrumb-item active">Combo Manager</li>
                </ol>
            </div>
        </div>
    </div><!-- /.container-fluid -->
</section>
<f:form action="${pageContext.request.contextPath}/admin/combo/saveCombo" method="POST"
        enctype="multipart/form-data" modelAttribute="newCombo">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-10">
                <div class="card card-info">
                    <div class="card-header">
                        <h3 class="card-title">Add New Combo</h3>
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
                                        <label>Discount(%)</label>
                                        <f:input path="discount" type="number"
                                                 class="form-control ${status.error ?'border border-danger':''}${param.errordiscount !=null ?'border border-danger':''}"
                                                 value="0" placeholder="Enter discount ..."/>
                                        <f:errors path="discount" class="text-danger"></f:errors>
                                        <p class="text-danger">${param.errordiscount}</p>

                                    </div>
                                </spring:bind>
                                <div class="form-group col-md-6">
                                    <label for="exampleInputEmail1">Status</label>
                                    <div class="form-check">
                                        <f:radiobutton path="status" value="1" class="form-check-input" checked="true"
                                                       id="exampleInputEmail1"/>
                                        <label class="form-check-label">Show</label>
                                        <br>
                                        <f:radiobutton path="status" value="2" class="form-check-input"
                                                       id="exampleInputEmail1"/>
                                        <label class="form-check-label">Hidden</label>
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
                                <div class="form-group col-md-6">
                                    <label>Product</label>
                                    <f:select class="custom-select" path="products">
                                        <f:options items="${products}" itemLabel="productName" itemValue="productID"/>
                                    </f:select>
                                </div>
                                </spring:bind>
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
                <div class="card-footer">
                    <button type="submit" class="btn btn-info">Create</button>
                </div>
            </div>
        </div>
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
</script>
</body>
</html>
