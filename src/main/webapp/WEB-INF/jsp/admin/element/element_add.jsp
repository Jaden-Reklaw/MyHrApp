<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>
    $(document).ready(function () {
        $("#successAlert").delay(8000).fadeOut(2000);
        $("#warningAlert").delay(8000).fadeOut(2000);
    });
</script>

<div class="d-flex m-4">

    <%--Side Bar Here--%>
    <%@include file="element_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">
        <%--Values in post request are connected to the action on element_add AdminController--%>
            <form:form cssClass="form-horizontal" modelAttribute="elementVO" action="/admin/element/add">
                <fieldset>
                    <legend>Element Management</legend>
                        <%--Element Type Input--%>
                    <div class="form-group">
                        <label for="inputNewElementType" class="col-lg-2 control-label">Element Type</label>
                        <div class="col-lg-10">
                            <%--paths are connected to the view object in VO package and are binded to the models must match fields--%>
                            <form:input path="newElementType" type="text" class="form-control" id="inputNewElementType"
                                        placeholder="Element Type"></form:input>
                        </div>
                    </div>
                        <%--Element Input--%>
                    <div class="form-group">
                        <label for="inputNewElements" class="col-lg-2 control-label">Elements</label>
                        <div class="col-lg-10">
                                <%--paths are connected to the view object in VO package and are binded to the models must match fields--%>
                            <form:textarea path="newElements"  class="form-control" rows="3" id="inputNewElements"
                                        placeholder="Elements Go Here"></form:textarea>
                            <span class="help-block">Enter each new Element on a new line.</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <form:button type="reset" value="cancel" class="btn btn-default">Cancel</form:button>
                            <form:button type="submit" value="save" class="btn btn-primary">Save</form:button>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>

        <%--Alerts--%>
        <div class="col-sm-4">

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