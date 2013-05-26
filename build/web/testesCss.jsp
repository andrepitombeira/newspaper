<%-- 
    Document   : testesCss
    Created on : 18/11/2009, 15:00:41
    Author     : fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" type="text/css" href="layout/stilo.css">

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>ZOI - Zona Organizada da Informação</title>
    </head>

    <body>
        

            <table width="800" border="0" cellpadding="0" cellspacing="0">
                <!--DWLayoutTable-->

                <tr>
                    <td height="172" colspan="5" valign="top"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="800" height="171">
                            <param name="movie" value="olho.swf" />
                            <param name="quality" value="high" />
                            <embed src="layout/olho.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="800" height="171"></embed>
                    </object></td>
                </tr>
                <tr>
                    <td height="14" colspan="5" valign="top"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="800" height="14">
                            <param name="movie" value="tarja.swf" />
                            <param name="quality" value="high" />
                            <embed src="layout/tarja.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="800" height="14"></embed>
                    </object></td>
                </tr>
                <tr>
                    <td width="205" height="30" valign="top" bgcolor="#F5F5F5"><form id="form1" name="form1" method="post" action="">
                            <label><span class="style1">- Login :</span>
                                <input type="text" name="textfield" />
                            </label>
                    </form></td>
                    <td width="209" valign="top" bgcolor="#F5F5F5"><form id="form2" name="form2" method="post" action="">
                            <label><span class="style1">- Senha :
                                </span>
                                <input type="password" name="textfield2" />
                            </label>
                    </form></td>
                    <td width="153" valign="top" bgcolor="#F5F5F5"><form id="form4" name="form4" method="post" action="">
                            <label><span class="style1">- Tipo : </span>
                                <select name="select">
                                    <option value="editor">Editor</option>
                                    <option value="jornalista">Jornalista</option>
                                    <option value="leitor">Leitor</option>
                                </select>
                            </label>
                    </form></td>
                    <td width="36" valign="top" bgcolor="#F5F5F5"><form id="form3" name="form3" method="post" action="">
                            <label>
                                <input name="O K" type="submit" id="O K" value="O K" />
                            </label>
                    </form></td>
                    <td width="197" valign="top" bgcolor="#F5F5F5"><div align="center"><span class="style1">Cadastra ? , clique<span class="style2"> <a href="cadastrar_unuario.html" target="_main">AQUI</a></span></span><span class="style2"><a href="cadastro.html">. </a></span></div></td>
                </tr>
            </table>

<%@ include file="menu.jsp" %>
<%@ include file="footer.jsp" %>
