<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>
    $(document).ready(function () {
        //attach an onclick function to remove buttons
        $('.remove_button').click(function () {
            //log name button, and contents of associated text box
            console.log(this.name);
            console.log($('#' + this.name).val());

            //clear the value / contents of the textbox
            $('#' + this.name).val('');

            //submit the form
            $('#vehicleMake').submit();
        });
    });
</script>

<div class="wrapper">

    <%--Side Bar Here--%>
    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">
            <c:set var="idx" value="0" scope="page" />
            <form:form class="form-horizontal" modelAttribute="vehicleMake" action="/admin/vehicle/make/update" method="post">
                <form:hidden path="id" />
                <form:hidden path="version" />

                <div class="row">
                    <div class="form-group">
                        <label for="inputVehicleMakeName" class="col-sm-2 control-label">Vehicle Make</label>
                        <div class="col-sm-8">
                            <form:input path="vehicleMakeName" type="text" id="inputVehicleMakeName" cssClass="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-10">
                        <hr />
                    </div>
                </div>

                <c:forEach items="${vehicleMake.vehicleModelList}" var="vehicleModel">
                    <form:hidden path="vehicleModelList[${idx}].id" />
                    <form:hidden path="vehicleModelList[${idx}].version" />
                    <div class="row">
                        <div class="form-group">
                            <label for="${idx}" class="col-sm-3 control-label" >Vehicle Model</label>
                            <div class="col-sm-7">
                                <div class="input-group">

                                    <form:input path="vehicleModelList[${idx}].vehicleModelName" id="${idx}" cssClass="form-control"/>
                                    <span class="input-group-btn">
                                        <button name="${idx}" class="btn btn-default remove_button" type="button">Remove</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:set var="idx" value="${idx + 1}" scope="page" />
                </c:forEach>

                <div class="row">
                    <div class="form-group">
                        <label for="inputNewVehicleModel" class="col-sm-3 control-label" >Add New Vehicle Model</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="inputNewVehicleModel" id="inputNewVehicleModel">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <button class="btn btn-primary">UPDATE</button>
                </div>

            </form:form>

        </div>

        <div class="col-sm-4">
            <%--Alerts--%>
            <%--Success Alert--%>
            <div class="${successAlert == null ? 'hidden' : successAlert}" id="successAlert">
                <div class="alert alert-dismissible alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times</button>
                    <strong>Well done!</strong> You successfully read <a href="#" class="alert-link">this important alert message</a>.
                </div>
            </div>

            <%--Warning Alert--%>
            <div class="${warningAlert == null ? 'hidden' : warningAlert}" id="warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Caution!</strong> <a href="#" class="alert-link">Be careful while you cross the train tracks</a>.
                </div>
            </div>

            <%--Error Alert--%>
            <div class="${errorAlert == null ? 'hidden' : errorAlert}" id="errorAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Oh snap!</strong> <a href="#" class="alert-link">Change a few things up</a> and try submitting again.
                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="../../includes/footer.jsp" %>