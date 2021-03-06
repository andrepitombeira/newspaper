package jornal.controle.servlets.editor;

import jornal.banco.dao.JornalistaDAO;
import jornal.entidades.Editor;
import jornal.entidades.Jornalista;
import jornal.entidades.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jornal.criptografia.Criptografia;

public class EditorAdicionarJornalistaServlet extends HttpServlet {
   
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
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        Jornalista jornalista = (Jornalista)session.getAttribute("jornalista");
        session.removeAttribute("jornalista");

        try {
            if(user != null && user instanceof Editor){
                Editor editor = (Editor) user;
                JornalistaDAO dao = new JornalistaDAO();
                if(nome == null || email == null || login == null || senha == null
                        || nome.trim().equals("") || email.trim().equals("") || login.trim().equals("")|| senha.trim().equals("")){
                    session.setAttribute("error", "Preencha todos os campos.");
                    response.sendRedirect(request.getContextPath() + "/editorAdicionarJornalista.jsp");
                }
                // Se nao houver jornalista na secao. Cria um novo. Se houver edita.
                else if(jornalista == null){

                    jornalista = new Jornalista();
                    jornalista.setNome(nome);
                    jornalista.setEmail(email);
                    jornalista.setLogin(login);
                    jornalista.setSenha(Criptografia.encrypt(senha));

                    dao.inserir(jornalista);
                    editor.getJornalistas().add(jornalista);

                    session.setAttribute("sucesso", "Jornalista adicionado com sucesso.");
                    response.sendRedirect(request.getContextPath() + "/editorAdicionarJornalista.jsp");
                }else{

                    jornalista.setNome(nome);
                    jornalista.setEmail(email);
                    jornalista.setLogin(login);
                    jornalista.setSenha(Criptografia.encrypt(senha));

                    dao.atualizar(jornalista);

                    session.setAttribute("sucesso", "Jornalista atualizado com sucesso.");
                    response.sendRedirect(request.getContextPath() + "/editorAdicionarJornalista.jsp");
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
