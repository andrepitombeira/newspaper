package jornal.entidades;

import java.util.ArrayList;

public class Secao implements Comparable<Secao> {

    private int id;
    private String titulo;
    private String descricao;
    private ArrayList<Noticia> noticias;

    public Secao() {
        this.noticias = new ArrayList<Noticia>();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(ArrayList<Noticia> noticias) {
        this.noticias = noticias;
    }

    public Noticia getNoticia(int id){
        for(int i=0; i<noticias.size(); i++){
            if(noticias.get(i).getId() == id)
                return noticias.get(i);
        }
        return null;
    }

    public Noticia removeNoticia(int id){
        for(int i=0; i<noticias.size(); i++){
            if(noticias.get(i).getId() == id)
                noticias.remove(i);
        }
        return null;
    }

    public int compareTo(Secao arg0) {
        return this.getTitulo().compareTo(arg0.getTitulo());
    }

}
