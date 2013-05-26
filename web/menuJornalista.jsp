<%-- 
    Document   : menuJornalista
    Created on : 18/11/2009, 10:41:56
    Author     : fernando
--%>

<%
jornal.entidades.Usuario userM = (jornal.entidades.Usuario)session.getAttribute("user");
%>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <!--DWLayoutTable-->
    <tr>
        <td width="119" height="17" valign="top" bgcolor="#C4E1E1"> <div align="left"><a href="index.jsp">- Home </a></div></td>
        <td width="2"></td>
    </tr>
    <tr>
        <td height="2"></td>
        <td></td>
    </tr>
    <tr>
        <td width="119" height="17" valign="top" bgcolor="#C4E1E1"> <div align="left"><a href="JornalistaListarNoticiasServlet">- Listar Notícias </a></div></td>
        <td width="2"></td>
    </tr>
    <tr>
        <td height="2"></td>
        <td></td>
    </tr>
    <tr>
        <td height="17" valign="top" bgcolor="#C4E1E1"><div align="left"><a href="LogoutServlet">- Logout </a></div></td>
        <td></td>
    </tr>
    <tr>
        <td height="140">&nbsp;</td>
        <td></td>
    </tr>
</table>