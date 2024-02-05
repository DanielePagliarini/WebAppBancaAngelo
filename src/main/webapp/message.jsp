<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Webapp Banca</title>
    </head>
    <body>
        <center>
            <h1>Messaggio esito azione</h1>
        </center>
		<%=(String) request.getAttribute("message-to-show")%>
	</body>
</html>
