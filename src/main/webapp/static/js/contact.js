$(document).ready(function () {
    getContacts();
});

//Function to create a table
function createTable(data) {
    $.each(data, function(index, single) {
        $('#contact-table').find('tbody')
            .append(
                "<tr>" +
                "<td>" + single.id + "</td>" +
                "<td>" + single.firstName+ " " + single.lastName + "</td>" +
                "<td>" + single.companyName + "</td>" +
                "<td>" + "<select class='form-control'><option>(Select Project)</option></select>"+ "</td>" +
                "<td>" + "<button onclick='editContact("+ single.id + ")'>Edit</button>" + "</td>" +
                "<td>" + "<button data-toggle='modal' data-target='#confirmDeleteModal' data-record-id='" + single.id + "'>Delete</button>" + "</td>" +
                "</tr>"
            );
    });
}

//Get Contacts from database
function getContacts() {
    $.getJSON("/api/contact/", {ajax: true}, function (data) {
        console.log(data);
        createTable(data);
    });
}