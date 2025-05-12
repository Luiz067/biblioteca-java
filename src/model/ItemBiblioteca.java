package model;

public abstract class ItemBiblioteca {
    protected String titulo;
    protected int anoPublicacao;
    protected int quantidadeDisponivel;
    protected int codigo;
    
    public ItemBiblioteca(String titulo, int anoPublicacao, int quantidadeDisponivel, int codigo) {
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.codigo = codigo;
    }
    
    // Getters e setters básicos
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    // Métodos comuns
    public void emprestar() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
        } else {
            throw new BibliotecaException("Item não disponível para empréstimo");
        }
    }

    public void devolver() {
        quantidadeDisponivel++;
    }
    
    // Método abstrato
    public abstract String exibirDetalhes();
}