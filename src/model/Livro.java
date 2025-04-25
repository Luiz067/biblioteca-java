package model;

public class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int quantidadeDisponivel;
    private String categoria;
    private int codigo;

    public Livro(String titulo, String autor, int anoPublicacao, int quantidadeDisponivel, String categoria, int codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.categoria = categoria;
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void emprestar() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
        }
    }

    public void devolver() {
        quantidadeDisponivel++;
    }
}
