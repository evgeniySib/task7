    <%@ page import="java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html>
<body>
     ${err1}
     ${err2}
     ${err3}
    <table border="1">
<c:forEach var="v" items= "${guestMsg}">

    <tr>
    <td>${v.id}</td><td>${v.nickName}</td><td>${v.message}</td><td>${v.postDate}</td>
    </tr>
    </c:forEach>
    </table>

    <form method="POST">
    Name
    <input type="text" name="name" />
    Message
    <input type="text" name="message" />
    <input type="submit" value="Submit">
    </form>

</body>
</html>