package jornal.controle.servlets.jornalista;

import jornal.banco.dao.NoticiaDAO;
import jornal.entidades.Jornalista;
import jornal.entidades.Noticia;
import jornal.entidades.Secao;
import jornal.entidades.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jornal.util.Util;

public class JornalistaAdicionarNoticiaServlet extends HttpServlet {
   
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
        String idSecaoS = request.getParameter("id_secao");
        String titulo = request.getParameter("titulo");
        String subtitulo = request.getParameter("subtitulo");
        String texto = request.getParameter("texto");
        String data = request.getParameter("data");
        Noticia noticia = (Noticia)session.getAttribute("noticia");
        
        try {
            if(user != null && user instanceof Jornalista){
                Jornalista j = (Jornalista) user;
                NoticiaDAO dao = new NoticiaDAO();
                if(idSecaoS == null || titulo == null || subtitulo == null || texto == null || data == null
                        || idSecaoS.trim().equals("null") || titulo.trim().equals("") || subtitulo.trim().equals("") || texto.trim().equals("null")|| data.trim().equals("")){
                    session.setAttribute("error", "Preencha todos os campos.");
                    response.sendRedirect(request.getContextPath() + "/jornalistaAdicionarNoticia.jsp");
                }
                // Se nao houver noticia na secao. Cria uma nova. Se houver edita.
                else if(noticia == null){
                    int idSecao = Integer.parseInt(idSecaoS);

                    noticia = new Noticia();
                    noticia.setJornalista(j);
                    noticia.setSecao(j.getSecao(idSecao));
                    noticia.setTitulo(titulo);
                    noticia.setSubtitulo(subtitulo);
                    noticia.setTexto(texto);
                    noticia.setData(Util.treatToDate(data));

                    dao.inserir(noticia);
                    j.getNoticias().add(noticia);

                    session.setAttribute("sucesso", "Noticia adicionada com sucesso.");
                    response.sendRedirect(request.getContextPath() + "/jornalistaAdicionarNoticia.jsp");
                }else{
                    int idSecao = Integer.parseInt(idSecaoS);

                    //noticia.setJornalista(j);
                    noticia.setSecao(j.getSecao(idSecao));
                    noticia.setTitulo(titulo);
                    noticia.setSubtitulo(subtitulo);
                    noticia.setTexto(texto);
                    noticia.setData(Util.treatToDate(data));

                    dao.atualizar(noticia);
                    
                    session.setAttribute("sucesso", "Noticia atualizada com sucesso.");
                    response.sendRedirect(request.getContextPath() + "/jornalistaAdicionarNoticia.jsp");
                }
            }else{
                session.setAttribute("error", "Voce nao tem permissao para acessar essa area.");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
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
