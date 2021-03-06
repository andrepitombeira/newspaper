package jornal.entidades;

public class Usuario extends Pessoa {

    private String login;
    private String senha;

    public Usuario() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Usuario){
            Usuario u = (Usuario)object;
            if(u.getId() == this.getId() && u.getNome().equals(this.getNome()) && u.getLogin().equals(this.getLogin()) && u.getSenha().equals(this.getSenha()) && u.getEmail().equals(this.getEmail()))
                return true;
        }
        return false;
    }

}
