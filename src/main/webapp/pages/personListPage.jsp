<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>personListPage</title>
</head>
<body>
THIS IS PERSON LIST PAGE
<br>
${message}

<form action="/webCrudServletJdbc/mvc/person/create" method="get">
    <button type="submit">  create new person   </button>
</form>


<table border="3">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>AGE</th>
    </tr>

    <c:forEach items="${personList}" var="person">
       <tr>
         <td>${person.getId()}</td>
         <td>${person.getName()}</td>
         <td>${person.getAge()}</td>
         <td>
                 <form action="/webCrudServletJdbc/mvc/person/delete" method="post">
                     <input type="hidden" name="DeleteIdParam" value="${person.getId()}"/>
                     <button type="submit">  delete   </button>
                 </form>

                 <form action="/webCrudServletJdbc/mvc/person/update" method="get">
                    <input type="hidden" name="UpdateIdParam" value="${person.getId()}"/>
                    <button type="submit">  update   </button>
                  </form>
         </td>
       </tr>
      </c:forEach>

</table>
</form>


</body>
</html>