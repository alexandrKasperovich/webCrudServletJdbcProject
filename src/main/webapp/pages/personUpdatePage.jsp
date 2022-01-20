<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PersonCreatePage</title>
</head>
<body>
in this page you update person in list :))))<br>

<form action="/webCrudServletJdbc/mvc/person/update" method="post">
    <label> id: </label>
    <input type="number" name="UpdatedPersonIdParam" value="${person.getId()}" readonly/> <br>

    <label> name: </label>
    <input type="text" name="UpdatedPersonNameParam" value="${person.getName()}"/> <br>

    <label> age: </label>
    <input type="number" name="UpdatedPersonAgeParam" value="${person.getAge()}"/> <br>


    <button type="submit">  update   </button>
</form>


<form action="/webCrudServletJdbc/mvc/person/list" method="get">
    <button type="submit">  cancel   </button>
</form>



</body>
</html>