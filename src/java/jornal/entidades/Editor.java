package jornal.entidades;

import java.util.ArrayList;

public class Editor extends Usuario {

    private ArrayList<Jornalista> jornalistas;
    private ArrayList<Secao> secoes;
    private ArrayList<Classificado> classificados;

    public Editor() {
        this.jornalistas = new ArrayList<Jornalista>();
        this.secoes = new ArrayList<Secao>();
        this.classificados = new ArrayList<Classificado>();
    }

    public ArrayList<Classificado> getClassificados() {
        return classificados;
    }

    public void setClassificados(ArrayList<Classificado> classificados) {
        this.classificados = classificados;
    }

    public ArrayList<Jornalista> getJornalistas() {
        return jornalistas;
    }

    public void setJornalistas(ArrayList<Jornalista> jornalistas) {
        this.jornalistas = jornalistas;
    }

    public ArrayList<Secao> getSecoes() {
        return secoes;
    }

    public void setSecoes(ArrayList<Secao> secoes) {
        this.secoes = secoes;
    }

    public Jornalista getJornalista(int id){
        for(int i=0; i< jornalistas.size(); i++){
            if(jornalistas.get(i).getId() == id)
                return jornalistas.get(i);
        }
        return null;
    }

    public Secao getSecao(int id){
        for(int i=0; i< secoes.size(); i++){
            if(secoes.get(i).getId() == id)
                return secoes.get(i);
        }
        return null;
    }

    public Classificado getClassificado(int id){
        for(int i=0; i< classificados.size(); i++){
            if(classificados.get(i).getId() == id)
                return classificados.get(i);
        }
        return null;
    }

    public Jornalista removerJornalista(int id){
        for(int i=0; i< jornalistas.size(); i++){
            if(jornalistas.get(i).getId() == id)
                return jornalistas.remove(i);
        }
        return null;
    }

    public Secao removerSecao(int id){
        for(int i=0; i< secoes.size(); i++){
            if(secoes.get(i).getId() == id)
                return secoes.remove(i);
        }
        return null;
    }

    public Classificado removerClassificado(int id){
        for(int i=0; i< classificados.size(); i++){
            if(classificados.get(i).getId() == id)
                return classificados.remove(i);
        }
        return null;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Editor){
            Editor e = (Editor)object;
            if(e.getId() == this.getId() && e.getNome().equals(this.getNome()) && e.getLogin().equals(this.getLogin()) && e.getSenha().equals(this.getSenha()) && e.getEmail().equals(this.getEmail()))
                return true;
        }
        return false;
    }

}
