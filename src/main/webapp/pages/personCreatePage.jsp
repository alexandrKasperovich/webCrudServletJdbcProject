<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PersonCreatePage</title>
</head>
<body>
in this page you create new person in list :))))<br>

<form action="/webCrudServletJdbc/mvc/person/create" method="post">
    <label> name: </label>
    <input type="text" name="NewPersonNameParam" value=""/> <br>

    <label> age: </label>
    <input type="number" name="NewPersonAgeParam" value=""/> <br>

    <button type="submit">  create   </button>
</form>


<form action="/webCrudServletJdbc/mvc/person/list" method="get">
    <button type="submit">  cancel   </button>
</form>



</body>
</html>