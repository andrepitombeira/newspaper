package jornal.entidades;

import java.util.ArrayList;
import java.util.Date;

public class Noticia implements Comparable<Noticia> {

    private int id;
    private Secao secao;
    private Jornalista jornalista;
    private String titulo;
    private String subtitulo;
    private String texto;
    private Date data;
    private ArrayList<Comentario> comentarios;

    public Noticia() {
        this.comentarios = new ArrayList<Comentario>();
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jornalista getJornalista() {
        return jornalista;
    }

    public void setJornalista(Jornalista jornalista) {
        this.jornalista = jornalista;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Comentario getComentario(int id){
        for(int i = 0; i<comentarios.size(); i++){
            if(comentarios.get(i).getId() == id)
                return comentarios.get(i);
        }
        return null;
    }

    public Comentario removerComentario(int id){
        for(int i = 0; i<comentarios.size(); i++){
            if(comentarios.get(i).getId() == id)
                return comentarios.remove(i);
        }
        return null;
    }

    public int compareTo(Noticia arg0) {
        return arg0.getData().compareTo(this.getData());
    }

}
