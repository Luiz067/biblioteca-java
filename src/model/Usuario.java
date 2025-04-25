package model;

public class Usuario {
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private int id;
    private boolean temEmprestimo;

    public Usuario(String nome, String telefone, String endereco, String email, int id) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.id = id;
        this.temEmprestimo = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
