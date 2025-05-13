package model;

public class Usuario extends Pessoa {
    private int id;
    private boolean temEmprestimo;

    public Usuario(String nome, String telefone, String endereco, String email, int id) {
        super(nome, telefone, endereco, email);
        this.id = id;
        this.temEmprestimo = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTemEmprestimo() {
        return temEmprestimo;
    }

    public void setTemEmprestimo(boolean temEmprestimo) {
        this.temEmprestimo = temEmprestimo;
    }
    
    @Override
    public String exibirInformacoes() {
        return "Usuário: " + nome + "\nID: " + id + "\nTelefone: " + telefone + 
               "\nEndereço: " + endereco + "\nEmail: " + email;
    }
}