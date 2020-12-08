<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>

<c:url value="../../static/js/employee.js" var="employee" />
<script src="${employee}"></script>

<div class="container">
    <h2>Employee Page</h2>

    <button onclick="insertEmployee()" class="btn btn-dark m-2">Add New Employee</button>

    <table id="employee-table" class="table table-striped table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Background</th>
            <th>Projects</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <%--append jquery data here--%>
        </tbody>
    </table>

    <div id="employeeModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Employee Details</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <%--Form--%>
                    <form class="form-horizontal">
                        <fieldset>
                            <hidden id="employeeId" />
                            <hidden id="employeeVersion" />

                            <div class="form-group">
                                <label for="inputFirstName" class="col-lg-2 control-label">First Name</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="inputFirstName" placeholder="First Name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputLastName" class="col-lg-2 control-label">Last Name</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="inputLastName" placeholder="Last Name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="textAreaBackground" class="col-lg-2 control-label">Background</label>
                                <div class="col-lg-10">
                                    <textarea class="form-control" rows="3" id="textAreaBackground"></textarea>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="saveEmployee()">Save Changes</button>
                </div>
            </div>

        </div>
    </div>

    <div id="confirmDeleteModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Confirm Delete</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you wish to delete? This can not be undone!</p>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger btn-ok" data-dismiss="modal" id="confirmDelete">Delete Employee</button>
                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="includes/footer.jsp" %>