package jornal.controle.servlets.leitor;

import jornal.banco.dao.ClassificadoDAO;
import jornal.entidades.Classificado;
import jornal.entidades.Leitor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LeitorInserirOfertaClassificadoServlet extends HttpServlet {
   
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

        String valor = request.getParameter("oferta");

        try {
            Leitor leitor = (Leitor) session.getAttribute("user");
            if(leitor == null){
                session.setAttribute("error", "Leitor nao está logado.");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }else if(valor == null || valor.trim().equals("")){
                session.setAttribute("error", "Preencha todos os campos.");
                response.sendRedirect(request.getContextPath() + "/mostrarClassificado.jsp");
            }else{
                ClassificadoDAO dao = new ClassificadoDAO();
                double oferta = Double.parseDouble(valor);

                Classificado classificado = (Classificado)session.getAttribute("classificado");

                if(oferta > classificado.getPreco() && oferta > classificado.getMelhorOferta()){
                    classificado.setUsuario(leitor);
                    classificado.setDataOferta(new Date());
                    classificado.setMelhorOferta(oferta);

                    dao.atualizar(classificado);
                    session.setAttribute("classificado", classificado);
                    response.sendRedirect(request.getContextPath() + "/mostrarClassificado.jsp");
                } else {
                    session.setAttribute("error", "Oferta menor que ultimo lance dado.");
                    response.sendRedirect(request.getContextPath() + "/mostrarClassificado.jsp");
                }
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
