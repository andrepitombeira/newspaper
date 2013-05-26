package jornal.entidades;

import java.util.ArrayList;

public class Jornalista extends Usuario {

    private ArrayList<Noticia> noticias;
    private ArrayList<Secao> secoes;

    public Jornalista() {
        this.noticias = new ArrayList<Noticia>();
        this.secoes = new ArrayList<Secao>();
    }

    public ArrayList<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(ArrayList<Noticia> noticias) {
        this.noticias = noticias;
    }

    public ArrayList<Secao> getSecoes() {
        return secoes;
    }

    public void setSecoes(ArrayList<Secao> secoes) {
        this.secoes = secoes;
    }

    public Noticia getNoticia(int id){
        for(int i = 0; i < noticias.size(); i++){
            if(noticias.get(i).getId() == id)
                return noticias.get(i);
        }
        return null;
    }

    public Secao getSecao(int id){
        for(int i = 0; i < secoes.size(); i++){
            if(secoes.get(i).getId() == id)
                return secoes.get(i);
        }
        return null;
    }

    public Noticia removerNoticia(int id){
        for(int i = 0; i < noticias.size(); i++){
            if(noticias.get(i).getId() == id)
                return noticias.remove(i);
        }
        return null;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Jornalista){
            Jornalista j = (Jornalista)object;
            if(j.getId() == this.getId() && j.getNome().equals(this.getNome()) && j.getLogin().equals(this.getLogin()) && j.getSenha().equals(this.getSenha()) && j.getEmail().equals(this.getEmail()))
                return true;
        }
        return false;
    }
    
}
