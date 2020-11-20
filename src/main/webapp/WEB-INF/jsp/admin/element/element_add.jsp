<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--Side Bar Here--%>
    <%@include file="element_sidebar.jsp" %>

    <div id="main-wrapper" class="col-md-11 pull-right">
        <div class="col-lg-8 col-md-7 col-sm-6">
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
                        <label for="inputNewElements" class="col-lg-2 control-label">Element Type</label>
                        <div class="col-lg-10">
                                <%--paths are connected to the view object in VO package and are binded to the models must match fields--%>
                            <form:textarea path="newElements"  class="form-control" rows="3" id="inputNewElements"
                                        placeholder="Element Type"></form:textarea>
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
    </div>
</div>

<%@include file="../../includes/footer.jsp" %>