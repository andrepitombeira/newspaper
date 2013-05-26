<%-- 
    Document   : error
    Created on : 23/11/2009, 15:11:49
    Author     : fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
String sucesso = (String) session.getAttribute("sucesso");
String error = (String) session.getAttribute("error");
%>

<h3>
    <% if(error != null){ %>
    <b>
    <font color="red">
    <%=error%>
    </font>
    </b>
<% } %>
<% if(sucesso != null){ %>
    <b>
    <font color="blue">
    <%=sucesso%>
    </font>
    </b>
<% } %></h3>

