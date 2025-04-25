package model;

import java.util.Date;

import controller.LibrarySystem;

public class PreCarga {

    public static void carregar(LibrarySystem system) {
        // Pré-carga de livros
        Livro livro1 = new Livro("Livro A", "Autor A", 2020, 5, "Ficção", 1);
        Livro livro2 = new Livro("Livro B", "Autor B", 2019, 3, "Não-Ficção", 2);
        system.adicionarLivro(livro1);
        system.adicionarLivro(livro2);

        // Pré-carga de usuários
        Usuario usuario1 = new Usuario("João", "999999999", "Rua 1", "joao@example.com", 1);
        Usuario usuario2 = new Usuario("Maria", "888888888", "Rua 2", "maria@example.com", 2);
        system.adicionarUsuario(usuario1);
        system.adicionarUsuario(usuario2);
    }
}
