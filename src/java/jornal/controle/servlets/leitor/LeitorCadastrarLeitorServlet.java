package jornal.controle.servlets.leitor;

import jornal.banco.dao.UsuarioDAO;
import jornal.entidades.Leitor;
import jornal.entidades.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jornal.banco.dao.LeitorDAO;
import jornal.criptografia.Criptografia;

public class LeitorCadastrarLeitorServlet extends HttpServlet {
   
    /** 
     * Cadastra um leitor no sistema.
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

        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");

        try {
            if(nome == null || login == null || senha == null || email == null
                    || nome.trim().equals("") || login.trim().equals("") || senha.trim().equals("") || email.trim().equals("") ){
                session.setAttribute("error", "Preencha todos os campos.");
            }else{
                UsuarioDAO daoUsuario = new UsuarioDAO();
                Usuario u = daoUsuario.buscarPorLogin(login);
                if(u == null){
                    LeitorDAO daoLeitor = new LeitorDAO();
                    Leitor novo = new Leitor();
                    novo.setNome(nome);
                    novo.setLogin(login);
                    novo.setSenha(Criptografia.encrypt(senha));
                    novo.setEmail(email);
                    daoLeitor.inserir(novo);
                    session.setAttribute("sucesso", "Leitor cadastrado com sucesso.");
                }else{
                    session.setAttribute("error", "Login invalido, por favor tente outro login.");
                }
            }
            response.sendRedirect(request.getContextPath() + "/cadastrarLeitor.jsp");
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
