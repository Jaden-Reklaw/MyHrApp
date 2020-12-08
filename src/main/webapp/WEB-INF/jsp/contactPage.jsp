<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>

<c:url value="../../static/js/contact.js" var="contact" />
<script src="${contact}"></script>

<div class="container">
    <h2>Contact Page</h2>

    <button onclick="insertContact()" class="btn btn-dark m-2">Add New Contact</button>

    <table id="contact-table" class="table table-striped table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Contact Name</th>
            <th>Company</th>
            <th>Addresses</th>
            <th>Modify</th>
            <th>Remove</th>
        </tr>
        </thead>
        <tbody>
        <%--append jquery data here--%>
        </tbody>
    </table>
</div>

<%@include file="includes/footer.jsp" %>