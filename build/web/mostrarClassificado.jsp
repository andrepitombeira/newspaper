<%-- 
    Document   : mostrarClassificado
    Created on : 18/11/2009, 10:41:39
    Author     : fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        jornal.entidades.Classificado classificado = (jornal.entidades.Classificado) session.getAttribute("classificado");
        if (classificado == null) {
            response.sendRedirect("index.jsp");
        }
        jornal.entidades.Usuario user = (jornal.entidades.Usuario) session.getAttribute("user");
        jornal.entidades.Editor editor = null;
        jornal.entidades.Jornalista jornalista = null;
        if (user != null && user instanceof jornal.entidades.Jornalista) {
            jornalista = (jornal.entidades.Jornalista) user;
        }
        if (user != null && user instanceof jornal.entidades.Editor) {
            editor = (jornal.entidades.Editor) user;
        }
%>

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
            .style10 {color: #666666}
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
                        <%@ include file="menu.jsp" %>
                    </td>
                    <td width="7" height="326"></td>
                    <td width="668" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <!--DWLayoutTable-->
                            <%@include file="error.jsp" %>
                            <tr>
                                <td width="22" height="14"></td>
                                <td width="544" valign="top" bgcolor="#FFFFFF"><div align="center"><strong>CLASSIFICADOS</strong></div></td>
                                <td width="102"></td>
                            </tr>
                            <tr>
                                <td height="13"></td>
                                <td valign="top"><hr /></td>
                                <td></td>
                            </tr>
                            <% if (editor != null) {%>
                            <tr>
                                <td height="13"></td>
                                <td valign="top">
                                    <label></label>
                                    <label></label>
                                    <div align="left">
                                        <a href="EditorRemoverClassificadoServlet?idClassificado=<%=classificado.getId()%>">Remover</a>
                                    </div>
                                </td>
                                <td></td>
                            </tr>
                            <% }%>
                            <tr>
                                <td height="33">&nbsp;</td>
                                <td valign="top">
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                        <!--DWLayoutTable-->
                                        <tr>
                                            <td width="544" height="14" valign="top"><div align="left">
                                                    <p class="style9"><%=classificado.getTitulo()%></p>
                                            </div></td>
                                        </tr>
                                        <tr>
                                            <td height="19" valign="top">
                                                <div align="left">
                                                    <p align="justify"><%=classificado.getTexto()%></p>
                                                    <p align="justify">Telefone: <%=classificado.getTelefone()%></p>
                                                    <p align="justify">Preço: <%=classificado.getPreco()%></p>
                                                    <p align="left">- Ultimo lance: <%=classificado.getMelhorOferta()%>, feito por <%=classificado.getUsuario().getNome()%> em <%=jornal.util.Util.treatInterface(classificado.getDataOferta())%>. </p>
                                                    <p align="justify">&nbsp;</p>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td height="132"></td>
                                <td valign="top">
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                        <!--DWLayoutTable-->
                                        <tr>
                                            <td width="544" height="132" valign="top">
                                                <% if (user != null && user instanceof jornal.entidades.Leitor) {%>
                                                <form id="form2" name="form2" method="post" action="LeitorInserirOfertaClassificadoServlet">
                                                    <label>
                                                        <div align="left"><span class="style9">FAZER OFERTA </span><br />
                                                            <input name="oferta" type="text" size="30" maxlength="5" />
                                                            <br />
                                                            <input name="Inserir" type="submit" id="Inserir" value="Inserir" />
                                                            <label>
                                                                <input name="Limpar" type="reset" id="Limpar" value="Limpar" />
                                                            </label>

                                                        </div>
                                                    </label>
                                                </form>
                                                <% } else if (user == null) {%>
                                                <p align="left" class="style9"><a href="cadastrarLeitor.jsp">Cadastrar Usu&aacute;rio</a></p>
                                                <% }%>
                                                <p>&nbsp;</p>
                                            </td>
                                        </tr>
                                </table>          </td>

                                <td></td>
                            </tr>
                            <tr>
                                <td height="269"></td>
                                <td></td>
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
