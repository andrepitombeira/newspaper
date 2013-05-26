package jornal.controle.servlets.editor;

import jornal.banco.dao.ClassificadoDAO;
import jornal.entidades.Classificado;
import jornal.entidades.Editor;
import jornal.entidades.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jornal.util.Util;

public class EditorAdicionarClassificadoServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        session.removeAttribute("error");
        session.removeAttribute("sucesso");

        Usuario user = (Usuario) session.getAttribute("user");
        //String idSecaoS = request.getParameter("id_secao");
        String titulo = request.getParameter("titulo");
        String texto = request.getParameter("texto");
        String precoS = request.getParameter("preco");
        String telefone = request.getParameter("telefone");
        String data = request.getParameter("data");
        Classificado classificado = (Classificado)session.getAttribute("classificado");
        session.removeAttribute("classificado");

        try {
            if(user != null && user instanceof Editor){
                Editor editor = (Editor) user;
                ClassificadoDAO dao = new ClassificadoDAO();
                double preco = Double.parseDouble(precoS);
                if(titulo == null || texto == null || precoS == null || telefone == null
                        || titulo.trim().equals("") || texto.trim().equals("") || precoS.trim().equals("")|| telefone.trim().equals("")){
                    session.setAttribute("error", "Preencha todos os campos.");
                    response.sendRedirect(request.getContextPath() + "/editorAdicionarClassificado.jsp");
                }
                // Se nao houver classificado na sessao. Cria um novo. Se houver edita.
                else if(classificado == null){

                    classificado = new Classificado();
                    classificado.setTitulo(titulo);
                    classificado.setTexto(texto);
                    classificado.setPreco(preco);
                    classificado.setTelefone(telefone);
                    classificado.setDataOferta(Util.treatToDate(data));
                    classificado.setUsuario(user);

                    dao.inserir(classificado);
                    editor.getClassificados().add(classificado);

                    session.setAttribute("sucesso", "Classificado adicionado com sucesso.");
                    response.sendRedirect(request.getContextPath() + "/editorAdicionarClassificado.jsp");
                }else{

                    classificado.setTitulo(titulo);
                    classificado.setTexto(texto);
                    classificado.setPreco(preco);
                    classificado.setTelefone(telefone);
                    classificado.setDataOferta(Util.treatToDate(data));
                    classificado.setUsuario(user);

                    dao.atualizar(classificado);

                    session.setAttribute("sucesso", "Classificado atualizado com sucesso.");
                    response.sendRedirect(request.getContextPath() + "/editorAdicionarClassificado.jsp");
                }
            }else{
                session.setAttribute("error", "Voce nao tem permissao para acessar essa area.");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }catch (Exception ex){
            ex.printStackTrace();
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="Métodos HttpServlet. Clique no sinal de + à esquerda para editar o código.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
