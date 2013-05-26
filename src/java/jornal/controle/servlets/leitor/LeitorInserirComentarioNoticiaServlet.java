package jornal.controle.servlets.leitor;

import jornal.banco.dao.ComentarioDAO;
import jornal.entidades.Comentario;
import jornal.entidades.Leitor;
import jornal.entidades.Noticia;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LeitorInserirComentarioNoticiaServlet extends HttpServlet {
   
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

        String texto = request.getParameter("texto");

        try {
            Leitor leitor = (Leitor) session.getAttribute("user");
            if(leitor == null){
                session.setAttribute("error", "Leitor nao está logado.");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }else if(texto == null || texto.trim().equals("")){
                session.setAttribute("error", "Preencha todos os campos.");
                response.sendRedirect(request.getContextPath() + "/mostrarNoticia.jsp");
            }else{
                ComentarioDAO dao = new ComentarioDAO();
                Comentario novo = new Comentario();
                Noticia noticia = (Noticia) session.getAttribute("noticia");

                novo.setLeitor(leitor);
                novo.setNoticia(noticia);
                novo.setTexto(texto);

                noticia.getComentarios().add(novo);
                session.setAttribute("noticia", noticia);

                dao.inserir(novo);
                response.sendRedirect(request.getContextPath() + "/mostrarNoticia.jsp");
            }
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
