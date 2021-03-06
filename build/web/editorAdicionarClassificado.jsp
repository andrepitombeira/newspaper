<%-- 
    Document   : editorAdicionarClassificado
    Created on : 18/11/2009, 10:45:13
    Author     : fernando
--%>

<%
        jornal.entidades.Usuario usuario = (jornal.entidades.Usuario) session.getAttribute("user");
        jornal.entidades.Editor editor = null;
        jornal.entidades.Classificado classificado = (jornal.entidades.Classificado) session.getAttribute("classificado");
        if (usuario == null || !(usuario instanceof jornal.entidades.Editor)) {
            response.sendRedirect("index.jsp");
        } else {
            editor = (jornal.entidades.Editor) usuario;
        }


        String texto = "";
        String titulo = "";
        String telefone = "";
        String preco = "";
        String data = "";
        if (classificado != null) {
            texto = classificado.getTexto();
            titulo = classificado.getTitulo();
            telefone = classificado.getTelefone();
            preco = classificado.getPreco()+"";
            data = jornal.util.Util.treatInterface(classificado.getDataOferta());
        }

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>ZOI - Zona Organizada da Informação</title>
        <style type="text/css">
            <!--
            #Layer1 {
                position:absolute;
                left:281px;
                top:246px;
                width:798px;
                height:107px;
                z-index:1;
            }
            body,td,th {
                font-size: 12px;
                font-family: Verdana, Arial, Helvetica, sans-serif;
                color: #999999;
            }
            a {
                font-size: 12px;
                color: #009900;
                font-family: Verdana, Arial, Helvetica, sans-serif;
            }
            a:hover {
                color: #333333;
                text-decoration: none;
            }
            a:link {
                color: #009900;
                text-decoration: none;
            }
            body {
                background-color: #F5F5F5;
            }
            a:visited {
                text-decoration: none;
            }
            a:active {
                text-decoration: none;
            }
            .style8 {color: #FFFFFF}
            .style9 {color: #333333}
            -->
        </style>
    </head>
    <body>
        <style type="text/css">
            body{
                background-color:000000;
                background-image:url('layout/fundo.jpg');
                background-attachment: fixed;
                background-position: center;


            }
        </style>
        <center>
            <table width="800" height="383" border="0" cellpadding="0" bgcolor="#F5F5F5">
                <!--DWLayoutTable-->
                <%@ include file="cabecalho.jsp" %>
                <tr>
                    <td width="121" rowspan="2" valign="top">
                        <!-- Área para o menu -->
                        <%@ include file="menuEditor.jsp" %>
                    </td>
                    <td width="7" height="326"></td>
                    <td width="668" valign="top">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <!--DWLayoutTable-->
                            <tr>
                                <td width="22" height="14"></td>
                                <td width="544" valign="top" bgcolor="#FFFFFF"><div align="center"><strong> Adicionar Classificado </strong></div></td>
                                <td width="102"></td>
                            </tr>
                            <tr>
                                <td height="13"></td>
                                <td valign="top"><hr /></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td height="13"></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td height="33">&nbsp;</td>
                                <td valign="top">
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                        <!--DWLayoutTable-->
                                            <tr>
                                             <td width="544" height="276" valign="top">
                                                 <%@include file="error.jsp" %>
                                                 <form id="form2" name="form2" method="post" action="EditorAdicionarClassificadoServlet">
                                                     <label>Título :<br />
                                                        <input type="text" name="titulo" value="<%=titulo%>"/>
                                                    </label>
                                                <p>
                                                    <label>Texto :<br />
                                                         <textarea name="texto" cols="60" rows="10" ><%=texto%></textarea>
                                                    </label>
                                                </p>
                                                <p>
                                                    <label>Preço :<br />
                                                        <input name="preco" type="text" value="<%=preco%>"/>
                                                </label>
                                                </p>
                                                <p>
                                                    <label>Telefone :<br />
                                                        <input name="telefone" type="text" value="<%=telefone%>"/>
                                                    </label>
                                                </p>
                                                <label>
                                                        <div align="left">Data :<br />
                                                            <input name="data" type="text" size="12" maxlength="10" value="<%=data%>" />
                                                        Ex : 12/12/2000 </div>
                                                    </label>
                                                <p>
                                                            <div align="left">

                                                                <br />
                                                                <input name="Limpar" type="reset" id="Limpar" value="Limpar" />
                                                                <input name="Enviar" type="submit" id="Enviar" value="Enviar" />
                                                            </div>
                                                        </label>
                                                    </p>
                                                </form>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="253">&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td height="0"></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td height="432">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td></td>
                </tr>
            </table>
        </center>
    </body>
</html>


