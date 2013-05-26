package jornal.banco.dao;

import jornal.banco.conexao.ConectionFactory;
import jornal.entidades.Jornalista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JornalistaDAO {

    private Connection connection;

    public JornalistaDAO() throws SQLException {
        connection = ConectionFactory.getConexao();
    }

    public void inserir(Jornalista jornalista) throws SQLException{

        String sql = " insert into jornal.usuario (id, login, senha, nome, email) values (?, ?, ?, ?, ?); " +
                " insert into jornal.role (id, usuario_id, role) values (?, ?, ?); ";
        PreparedStatement stmt = connection.prepareStatement(sql);

        jornalista.setId(this.proxId());

        stmt.setInt(1, jornalista.getId());
        stmt.setString(2, jornalista.getLogin());
        stmt.setString(3, jornalista.getSenha());
        stmt.setString(4, jornalista.getNome());
        stmt.setString(5, jornalista.getEmail());

        stmt.setInt(6, this.proxIdRole());
        stmt.setInt(7, jornalista.getId());
        stmt.setString(8, "Jornalista");

        stmt.execute();
        stmt.close();

    }

    public void atualizar(Jornalista jornalista) throws SQLException {
        String sql = "update jornal.usuario SET nome = ?, login = ?, senha = ?, email = ? where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, jornalista.getNome());
        stmt.setString(2, jornalista.getLogin());
        stmt.setString(3, jornalista.getSenha());
        stmt.setString(4, jornalista.getEmail());
        stmt.setInt(5, jornalista.getId());

        stmt.execute();
        stmt.close();
    }

    public void excluir(int id) throws SQLException {

        String sql = " delete from jornal.role where usuario_id = ?; " +
                " delete from jornal.usuario where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();

    }

    public Jornalista buscarPorId(int id) throws SQLException{

        String sql = "select jornal.usuario.* from jornal.usuario, jornal.role " +
                " where jornal.usuario.id = jornal.role.usuario_id and jornal.role.role = ? and " +
                " jornal.usuario.id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "Jornalista");
        stmt.setLong(2, id);
        ResultSet rs = stmt.executeQuery();

        Jornalista jornalistaBusca = null;

        while (rs.next()) {
            jornalistaBusca = new Jornalista();

            jornalistaBusca.setId(rs.getInt("id"));
            jornalistaBusca.setNome(rs.getString("nome"));
            jornalistaBusca.setEmail(rs.getString("email"));
            jornalistaBusca.setLogin(rs.getString("login"));
            jornalistaBusca.setSenha(rs.getString("senha"));
        }

        rs.close();
        stmt.close();

        return jornalistaBusca;
    }

    public Jornalista buscarPorLogin(String login) throws SQLException{

        String sql = "select jornal.usuario.* from jornal.usuario, jornal.role " +
                " where jornal.usuario.id = jornal.role.usuario_id and jornal.role.role = ? and " +
                " jornal.usuario.login=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "Jornalista");
        stmt.setString(2, login);
        ResultSet rs = stmt.executeQuery();

        Jornalista jornalistaBusca = null;

        while (rs.next()) {
            jornalistaBusca = new Jornalista();
            
            jornalistaBusca.setId(rs.getInt("id"));
            jornalistaBusca.setNome(rs.getString("nome"));
            jornalistaBusca.setEmail(rs.getString("email"));
            jornalistaBusca.setLogin(rs.getString("login"));
            jornalistaBusca.setSenha(rs.getString("senha"));
        }

        rs.close();
        stmt.close();

        return jornalistaBusca;
    }

    public ArrayList<Jornalista> buscarPorIdEditor(int id) throws SQLException{

        String sql = "select jornal.usuario.* from jornal.usuario, jornal.role " +
                " where jornal.usuario.id = jornal.role.usuario_id and jornal.role.role = ? ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "Jornalista");
        ResultSet rs = stmt.executeQuery();

        ArrayList<Jornalista> jornalistas = new ArrayList<Jornalista>();

        while (rs.next()) {
            Jornalista jornalistaBusca = new Jornalista();

            jornalistaBusca.setId(rs.getInt("id"));
            jornalistaBusca.setNome(rs.getString("nome"));
            jornalistaBusca.setEmail(rs.getString("email"));
            jornalistaBusca.setLogin(rs.getString("login"));
            jornalistaBusca.setSenha(rs.getString("senha"));

            jornalistas.add(jornalistaBusca);
        }

        rs.close();
        stmt.close();

        return jornalistas;
    }

    private int proxId() throws SQLException{

        int max = 0;
        String sql = " select max(id) from jornal.usuario; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            max = rs.getInt("max");
        }

        rs.close();
        stmt.close();

        return max+1;

    }

    private int proxIdRole() throws SQLException{

        int max = 0;
        String sql = " select max(id) from jornal.role; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            max = rs.getInt("max");
        }

        rs.close();
        stmt.close();

        return max+1;

    }

}
