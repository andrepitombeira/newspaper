package jornal.controle.servlets;

import jornal.banco.dao.NoticiaDAO;
import jornal.entidades.Noticia;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jornal.banco.dao.SecaoDAO;
import jornal.entidades.Secao;

public class ListarNoticiasPorSecaoServlet extends HttpServlet {
   
    /** 
     * Lista as notícias de uma secao.
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
        session.removeAttribute("noticias");
        session.removeAttribute("secao");

        String idS = request.getParameter("idSecao");
        try {
            
            int id = Integer.parseInt(idS);

            SecaoDAO daoSecao = new SecaoDAO();
            Secao s = daoSecao.buscarPorId(id);
            session.setAttribute("secao", s);

            NoticiaDAO daoNoticia = new NoticiaDAO();
            ArrayList<Noticia> noticias = daoNoticia.buscarPorIdSecao(id);

            session.setAttribute("noticias", noticias);
            response.sendRedirect(request.getContextPath() + "/listarNoticiasPorSecao.jsp?");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
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
