package model;

public class Livro extends ItemBiblioteca {
    private String autor;
    private String categoria;

    public Livro(String titulo, String autor, int anoPublicacao, int quantidadeDisponivel, String categoria, int codigo) {
        super(titulo, anoPublicacao, quantidadeDisponivel, codigo);
        this.autor = autor;
        this.categoria = categoria;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String exibirDetalhes() {
        return "Livro: " + titulo + "\nAutor: " + autor + "\nCategoria: " + categoria + 
               "\nAno: " + anoPublicacao + "\nQuantidade Disponível: " + quantidadeDisponivel;
    }
}