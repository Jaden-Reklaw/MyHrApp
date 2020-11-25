<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--Side Bar Here--%>
    <%@include file="vehicle_sidebar.jsp" %>
    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-12">
            <%--List of Existing Elements--%>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Vehicle Model</th>
                    <th>Vehicles</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="vehicleModel" items="${vehicleModelList}">
                    <tr>
                        <td>${vehicleModel.id}</td>
                        <td>${vehicleModel.vehicleModelName}</td>
                        <td>Vehicle Models Go Here</td>
                        <td><a href="/admin/vehicle/model/edit/${vehicleModel.id}">Edit</a></td>
                        <td><a href="/admin/vehicle/model/delete/${vehicleModel.id}">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="../../includes/footer.jsp" %>