package jornal.controle.servlets.editor;

import jornal.banco.dao.SecaoDAO;
import jornal.entidades.Editor;
import jornal.entidades.Secao;
import jornal.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditorAdicionarSecaoServlet extends HttpServlet {
   
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
        String descricao = request.getParameter("descricao");
        Secao secao = (Secao)session.getAttribute("secao");
        session.removeAttribute("secao");

        try {
            if(user != null && user instanceof Editor){
                Editor editor = (Editor) user;
                SecaoDAO dao = new SecaoDAO();
                if(titulo == null || descricao == null 
                        || titulo.trim().equals("") || descricao.trim().equals("")){
                    session.setAttribute("error", "Preencha todos os campos.");
                    response.sendRedirect(request.getContextPath() + "/editorAdicionarSecao.jsp");
                }
                // Se nao houver secao na sessao. Cria um novo. Se houver edita.
                else if(secao == null){

                    secao = new Secao();
                    secao.setTitulo(titulo);
                    secao.setDescricao(descricao);
                    
                    dao.inserir(secao);
                    editor.getSecoes().add(secao);

                    session.setAttribute("sucesso", "Secao adicionada com sucesso.");
                    response.sendRedirect(request.getContextPath() + "/editorAdicionarSecao.jsp");
                }else{

                    secao.setTitulo(titulo);
                    secao.setDescricao(descricao);

                    dao.atualizar(secao);

                    session.setAttribute("sucesso", "Secao atualizada com sucesso.");
                    response.sendRedirect(request.getContextPath() + "/editorAdicionarSecao.jsp");
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
