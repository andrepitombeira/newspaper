package jornal.controle.servlets;

import jornal.banco.dao.EditorDAO;
import jornal.banco.dao.JornalistaDAO;
import jornal.banco.dao.LeitorDAO;
import jornal.criptografia.Criptografia;
import jornal.entidades.Editor;
import jornal.entidades.Jornalista;
import jornal.entidades.Leitor;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jornal.banco.dao.NoticiaDAO;
import jornal.banco.dao.SecaoDAO;
import jornal.entidades.Secao;

public class LoginServlet extends HttpServlet {

    /**
     * Servlet responsavel por fazer login dos usuarios no sistema do Jornal.
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        session.removeAttribute("error");
        session.removeAttribute("sucesso");

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String conta = request.getParameter("conta");

        try {
            if (login == null || senha == null || conta == null || login.trim().equals("") || senha.trim().equals("") || conta.trim().equals("null")) {
                session.setAttribute("error", "Preencha todos os campos.");
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
                senha = Criptografia.encrypt(senha);
                System.out.println(senha);
                if (conta.trim().equals("leitor")) {
                    LeitorDAO dao = new LeitorDAO();
                    Leitor leitor = dao.buscarPorLogin(login);

                    if (leitor != null && senha.trim().equals(leitor.getSenha())) {
                        session.setAttribute("user", leitor);
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    } else {
                        session.setAttribute("error", "Usuário e senha não conferem.");
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    }
                } else if (conta.trim().equals("jornalista")) {
                    JornalistaDAO dao = new JornalistaDAO();
                    Jornalista jornalista = dao.buscarPorLogin(login);

                    if (jornalista != null && senha.trim().equals(jornalista.getSenha())) {
                        SecaoDAO daoSecao = new SecaoDAO();
                        jornalista.setSecoes(daoSecao.buscarTodos());

                        NoticiaDAO daoNoticia = new NoticiaDAO();
                        jornalista.setNoticias(daoNoticia.buscarPorIdJornalista(jornalista.getId()));

                        session.setAttribute("user", jornalista);
                        response.sendRedirect(request.getContextPath() + "/jornalista.jsp");
                    } else {
                        session.setAttribute("error", "Usuário e senha não conferem.");
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    }
                } else if (conta.trim().equals("editor")) {
                    EditorDAO dao = new EditorDAO();
                    Editor editor = dao.buscarPorLogin(login);

                    if (editor != null && senha.trim().equals(editor.getSenha())) {
                        session.setAttribute("user", editor);
                        response.sendRedirect(request.getContextPath() + "/editor.jsp");
                    } else {
                        session.setAttribute("error", "Usuário e senha não conferem.");
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    }
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
