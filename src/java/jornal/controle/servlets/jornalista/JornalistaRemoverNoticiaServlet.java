package jornal.controle.servlets.jornalista;

import jornal.banco.dao.NoticiaDAO;
import jornal.entidades.Jornalista;
import jornal.entidades.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JornalistaRemoverNoticiaServlet extends HttpServlet {
   
    /** 
     * Apaga uma noticia do jornalista.
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
        String idNoticiaS = request.getParameter("idNoticia");
        try {
            int id = Integer.parseInt(idNoticiaS);
            if(user != null && user instanceof Jornalista){
                Jornalista j = (Jornalista) user;
                if(j.getNoticia(id) != null){
                    NoticiaDAO dao = new NoticiaDAO();
                    dao.excluir(id);
                    j.removerNoticia(id);
                    session.setAttribute("sucesso", "Notícia removida com sucesso.");
                    response.sendRedirect(request.getContextPath() + "/jornalistaListarNoticias.jsp");
                }else{
                    session.setAttribute("error", "Voce nao tem permissao para apagar essa notícia");
                    response.sendRedirect(request.getContextPath() + "/jornalistaListarNoticias.jsp");
                }
            }else{
                session.setAttribute("error", "Voce nao tem permissao para acessar essa area.");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception ex){
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
