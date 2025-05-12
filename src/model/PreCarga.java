package model;

import controller.LibrarySystem;

public class PreCarga {
    public static void carregar(LibrarySystem system) {
        try {
            // Pré-carga de livros
            Livro livro1 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, 5, "Fantasia", 1);
            Livro livro2 = new Livro("Harry Potter e a Pedra Filosofal", "J.K. Rowling", 1997, 3, "Fantasia", 2);
            Livro livro3 = new Livro("1984", "George Orwell", 1949, 4, "Ficção Científica", 3);
            Livro livro4 = new Livro("Dom Quixote", "Miguel de Cervantes", 1605, 2, "Clássico", 4);
            
            system.adicionarItemBiblioteca(livro1);
            system.adicionarItemBiblioteca(livro2);
            system.adicionarItemBiblioteca(livro3);
            system.adicionarItemBiblioteca(livro4);

            // Pré-carga de usuários
            Usuario usuario1 = new Usuario("João Silva", "999999999", "Rua A, 123", "joao@example.com", 1);
            Usuario usuario2 = new Usuario("Maria Santos", "888888888", "Rua B, 456", "maria@example.com", 2);
            Usuario usuario3 = new Usuario("Pedro Oliveira", "777777777", "Rua C, 789", "pedro@example.com", 3);
            
            system.adicionarPessoa(usuario1);
            system.adicionarPessoa(usuario2);
            system.adicionarPessoa(usuario3);
            
            System.out.println("Pré-carga concluída com sucesso");
        } catch (Exception e) {
            System.out.println("Erro durante a pré-carga: " + e.getMessage());
        }
    }
}