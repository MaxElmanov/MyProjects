<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users page</title>
</head>
<body>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Age</th>
        </tr>
        <#list listUsers as u>
            <tr>
                <td>${u.id}</td>
                <td>${u.name}</td>
                <td>${u.email}</td>
                <td>${u.age}</td>
            </tr>
        </#list>
    </table>
</body>
</html>