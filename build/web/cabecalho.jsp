
<%
jornal.entidades.Usuario u = (jornal.entidades.Usuario) session.getAttribute("user");
%>

<tr>
    <td height="172" colspan="3" valign="top"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="800" height="171">
            <param name="movie" value="olho.swf" />
            <param name="quality" value="high" />
            <embed src="layout/olho.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="800" height="171"></embed>
    </object></td>
    <td width="2">&nbsp;</td>
</tr>
<tr>
    <td height="15" colspan="3" valign="top"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="800" height="14">
            <param name="movie" value="tarja.swf" />
            <param name="quality" value="high" />
            <embed src="layout/tarja.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="800" height="14"></embed>
    </object></td>
    <td></td>
</tr>
<tr>
    <td height="41" colspan="3" valign="top" bgcolor="#F5F5F5">
        <%if(u == null){%>
        <form id="form1" name="form1" method="post" action="LoginServlet">
            -
            <label>Login :
                <input type="text" name="login" />
            </label>
            -
            <label>Senha :
                <input type="password" name="senha" />
            </label>
            -
            <label>Tipo :
                <select name="conta">
                    <option value="leitor" selected>Leitor</option>
                    <option value="jornalista">Jornalista</option>
                    <option value="editor">Editor</option>
                </select>
            </label>
            <label>
                <input name="O K" type="submit" id="O K" value="O K" />
            <span class="style8">---</span>      </label>
        </form>
        <form id="form2" name="form2" method="post" action="cadastrarLeitor.jsp">
            <label>
                <input name="Fazer Cadastro" type="submit" id="Fazer Cadastro" value="Fazer Cadastro" />
            </label>
            <hr />
        </form>
        <%}else{%>
        Bem vindo, <%=u.getNome()%>
        <%}%>
    </td>
    <td></td>
</tr>