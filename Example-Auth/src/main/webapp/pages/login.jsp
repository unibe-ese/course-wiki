<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />


<h1>Login</h1>


<div id="login-error">${error}</div>


<c:url value="/j_spring_security_check" var="loginUrl"/>
<form action="${loginUrl}" method="post" >
 
 
<p>
 <label for="j_username">Username</label>
 <input id="j_username" name="j_username" type="text" />
</p><p>
 
 
 <label for="j_password">Password</label>
 <input id="j_password" name="j_password" type="password" />
</p><input  type="submit" value="Login"/>        
  
</form></body>


<c:import url="template/footer.jsp" />
