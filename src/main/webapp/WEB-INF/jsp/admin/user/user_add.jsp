<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>
    $(document).ready(function () {
        $("#successAlert").delay(8000).fadeOut(2000);
        $("#warningAlert").delay(8000).fadeOut(2000);
    });
</script>

<div class="wrapper">
    <div class="container">

        <%--Values in post request are connected to the action on element_add AdminController--%>
        <form:form cssClass="form-horizontal" modelAttribute="userVO" action="/admin/element/add">
            <fieldset>
                <legend>Add User</legend>
                <div class="form-group">
                    <label for="firstName" class="col-lg-2 control-label">First Name:</label>
                    <div class="col-lg-10">
                            <%--paths are connected to the view object in VO package and are binded to the models must match fields--%>
                        <form:input path="firstName" type="text" class="form-control" id="firstName"
                                    placeholder="First Name"></form:input>
                    </div>
                </div>
            </fieldset>

        </form:form>
    </div>

</div>

<%@include file="../../includes/footer.jsp" %>

