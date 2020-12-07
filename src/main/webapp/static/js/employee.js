$(document).ready(function () {
    buildTable();
});

function buildTable() {
    $.getJSON('/api/employee/', {ajax: 'true'}, function(data) {
        $.each(data, function(index, single) {
            let fullName = single.firstName + " " + single.lastName;
            $('#employee-table').find('tbody')
                .append(
                    "<tr>" +
                    "<td>" + single.id + "</td>" +
                    "<td>" + single.firstName+ " " + single.lastName + "</td>" +
                    "<td>" + single.background + "</td>" +
                    "<td>" + "<select><option>{Select Project}</option></select>"+ "</td>" +
                    "<td>" + "<button onclick='editEmployee("+ single.id + ")'>Edit</button>" + "</td>" +
                    "<td>" + "<button onclick='deleteEmployee("+ single.id + ")'>Delete</button>" + "</td>" +
                    "</tr>"
                );
        });
    });
}

function insertEmployee() {
    //clear fields in modal
    $('#employeeId').val("");
    $('#employeeVersion').val("");
    $('#inputFirstName').val("");
    $('#inputLastName').val("");
    $('#textAreaBackground').val("");

    //Show modal
    $('#employeeModal').modal('show');
}

function saveEmployee() {
    //Save to values from page
    let id = $('#employeeId').val();
    let version = $('#employeeVersion').val();
    let firstName = $('#inputFirstName').val();
    let lastName = $('#inputLastName').val();
    let background = $('#textAreaBackground').val();

    //populate object to send to backend using json
    const employee = {
        id: id,
        version: version,
        firstName: firstName,
        lastName: lastName,
        background: background,
        project: []
    }

    console.log(employee);

    //Send to backend using ajax
    $.ajax({
        type: "post",
        data: JSON.stringify(employee),
        url: "/api/employee/",
        async: true,
        dataType: "json",
        contentType: "application/json",
        success: function () {
            window.location.reload();
        }
    });
}

function editEmployee(id) {
    //get employee with passed in ID
    $.getJSON('/api/employee/' + id, {
        ajax: true}, function (employee) {
        console.log(employee);
        //populate values(hidden and input)
        $('#employeeId').val(employee.id);
        $('#employeeVersion').val(employee.version);
        $('#inputFirstName').val(employee.firstName);
        $('#inputLastName').val(employee.lastName);
        $('#textAreaBackground').val(employee.background);

        //open modal
        $('#employeeModal').modal('show');
    });
}

function deleteEmployee(id) {

    $('#confirmDeleteModal').modal('show');

    $('#confirmDelete').click(function () {
        $.ajax({
            type: "Delete",
            url: "/api/employee/" + id,
            async: true,
            dataType: "json",
            contentType: "application/json",
            success: function (){
                window.location.reload();
            },
            error: function () {
                alert("Error Deleting Employee!");
            }
        });
    });
}