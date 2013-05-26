

<%
java.util.ArrayList<jornal.entidades.Secao> secoes;
jornal.banco.dao.SecaoDAO daoSecao = new jornal.banco.dao.SecaoDAO();
secoes = daoSecao.buscarTodos();
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
        <td width="119" height="17" valign="top" bgcolor="#C4E1E1"> <div align="left"><a href="ListarClassificadosServlet">- Classificados </a></div></td>
        <td width="2"></td>
    </tr>
    <tr>
        <td height="2"></td>
        <td></td>
    </tr>
<% for(jornal.entidades.Secao secao : secoes) { %>
    <tr>
        <td height="17" valign="top" bgcolor="#C4E1E1"><div align="left"><a href="ListarNoticiasPorSecaoServlet?idSecao=<%=secao.getId()%>">- <%=secao.getTitulo()%> </a></div></td>
        <td></td>
    </tr>
    <tr>
        <td height="2"></td>
        <td></td>
    </tr>
<% } %>
<% if(userM != null && userM instanceof jornal.entidades.Editor) { %>
    <tr>
        <td height="17" valign="top" bgcolor="#C4E1E1"><div align="left"><a href="editor.jsp">- Restrita </a></div></td>
        <td></td>
    </tr>
    <tr>
        <td height="2"></td>
        <td></td>
    </tr>
<% } %>
<% if(userM != null && userM instanceof jornal.entidades.Jornalista) { %>
    <tr>
        <td height="17" valign="top" bgcolor="#C4E1E1"><div align="left"><a href="jornalista.jsp">- Restrita </a></div></td>
        <td></td>
    </tr>
    <tr>
        <td height="2"></td>
        <td></td>
    </tr>
<% } %>
<% if(userM != null) { %>
    <tr>
        <td height="17" valign="top" bgcolor="#C4E1E1"><div align="left"><a href="LogoutServlet">- Logout </a></div></td>
        <td></td>
    </tr>
    <tr>
        <td height="2"></td>
        <td></td>
    </tr>
<% } %>
    <tr>
        <td height="140">&nbsp;</td>
        <td></td>
    </tr>
</table>