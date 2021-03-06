package jornal.banco.dao;

import jornal.banco.conexao.ConectionFactory;
import jornal.entidades.Comentario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComentarioDAO {

    private Connection connection;

    public ComentarioDAO() throws SQLException {
        connection = ConectionFactory.getConexao();
    }

    public void inserir(Comentario comentario) throws SQLException {

        String sql = " insert into jornal.comentario (id, id_autor, id_noticia, texto) values (?, ?, ?, ?); ";
        PreparedStatement stmt = connection.prepareStatement(sql);

        comentario.setId(this.proxId());

        stmt.setInt(1, comentario.getId());
        stmt.setInt(2, comentario.getLeitor().getId());
        stmt.setInt(3, comentario.getNoticia().getId());
        stmt.setString(4, comentario.getTexto());

        stmt.execute();
        stmt.close();

    }

    public void atualizar(Comentario comentario) throws SQLException {
        String sql = " update jornal.comentario " +
                " SET id_autor = ?, id_noticia = ?, texto = ? " +
                " where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, comentario.getLeitor().getId());
        stmt.setInt(2, comentario.getNoticia().getId());
        stmt.setString(3, comentario.getTexto());
        stmt.setInt(4, comentario.getId());

        stmt.execute();
        stmt.close();
    }

    public void excluir(int id) throws SQLException {

        String sql = " delete from jornal.comentario where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, id);

        stmt.execute();
        stmt.close();

    }

    public Comentario buscarPorId(int id) throws SQLException {

        String sql = " select * from jornal.comentario " +
                " where id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();

        Comentario comentarioBusca = null;

        while (rs.next()) {
            comentarioBusca = new Comentario();

            comentarioBusca.setId(rs.getInt("id"));
            comentarioBusca.setTexto(rs.getString("texto"));

            LeitorDAO daoLeitor = new LeitorDAO();
            comentarioBusca.setLeitor(daoLeitor.buscarPorId(rs.getInt("id_autor")));

            NoticiaDAO daoNoticia = new NoticiaDAO();
            comentarioBusca.setNoticia(daoNoticia.buscarPorId(rs.getInt("id_noticia")));
        }

        rs.close();
        stmt.close();

        return comentarioBusca;
    }

    public ArrayList<Comentario> buscarTodos() throws SQLException {

        String sql = " select * from jornal.comentario; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();

        while (rs.next()) {
            Comentario comentarioBusca = new Comentario();

            comentarioBusca.setId(rs.getInt("id"));
            comentarioBusca.setTexto(rs.getString("texto"));

            LeitorDAO daoLeitor = new LeitorDAO();
            comentarioBusca.setLeitor(daoLeitor.buscarPorId(rs.getInt("id_autor")));

            NoticiaDAO daoNoticia = new NoticiaDAO();
            comentarioBusca.setNoticia(daoNoticia.buscarPorId(rs.getInt("id_noticia")));

            comentarios.add(comentarioBusca);
        }

        rs.close();
        stmt.close();

        return comentarios;
    }

    public ArrayList<Comentario> buscarPorIdNoticia(int id) throws SQLException {

        String sql = " select * from jornal.comentario where id_noticia = ?; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();

        while (rs.next()) {
            Comentario comentarioBusca = new Comentario();

            comentarioBusca.setId(rs.getInt("id"));
            comentarioBusca.setTexto(rs.getString("texto"));

            LeitorDAO daoLeitor = new LeitorDAO();
            comentarioBusca.setLeitor(daoLeitor.buscarPorId(rs.getInt("id_autor")));

            NoticiaDAO daoNoticia = new NoticiaDAO();
            comentarioBusca.setNoticia(daoNoticia.buscarPorId(rs.getInt("id_noticia")));

            comentarios.add(comentarioBusca);
        }

        rs.close();
        stmt.close();

        return comentarios;
    }

    private int proxId() throws SQLException {

        int max = 0;
        String sql = " select max(id) from jornal.comentario; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            max = rs.getInt("max");
        }

        rs.close();
        stmt.close();

        return max + 1;

    }
}
