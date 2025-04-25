package controller;

import model.*;
import java.util.*;


import java.util.*;

public class LibrarySystem {
    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private int livroId = 1;
    private int usuarioId = 1;

    public void cadastrarLivro(Scanner scanner) {
        System.out.println("Digite o título do livro:");
        String titulo = scanner.nextLine();
        System.out.println("Digite o autor do livro:");
        String autor = scanner.nextLine();
        System.out.println("Digite o ano de publicação do livro:");
        int anoPublicacao = scanner.nextInt();
        System.out.println("Digite a quantidade de exemplares:");
        int quantidadeDisponivel = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.println("Digite a categoria do livro:");
        String categoria = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, anoPublicacao, quantidadeDisponivel, categoria, livroId++);
        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso.");
    }

    public void pesquisarLivro(Scanner scanner) {
        System.out.println("Digite o código, título, autor ou categoria do livro:");
        String termo = scanner.nextLine().toLowerCase();

        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(termo) || livro.getAutor().toLowerCase().contains(termo) ||
                livro.getCategoria().toLowerCase().contains(termo)) {
                System.out.println("Livro encontrado: " + livro.getTitulo() + " de " + livro.getAutor());
            }
        }
    }

    public void cadastrarUsuario(Scanner scanner) {
        System.out.println("Digite o nome do usuário:");
        String nome = scanner.nextLine();
        System.out.println("Digite o telefone do usuário:");
        String telefone = scanner.nextLine();
        System.out.println("Digite o endereço do usuário:");
        String endereco = scanner.nextLine();
        System.out.println("Digite o e-mail do usuário:");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, telefone, endereco, email, usuarioId++);
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso.");
    }

    public void emprestarLivro(Scanner scanner) {
        System.out.println("Digite o código do livro:");
        int codigoLivro = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.println("Digite o ID do usuário:");
        int idUsuario = scanner.nextInt();

        Livro livro = null;
        for (Livro l : livros) {
            if (l.getCodigo() == codigoLivro) {
                livro = l;
                break;
            }
        }

        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getId() == idUsuario) {
                usuario = u;
                break;
            }
        }

        if (livro != null && usuario != null) {
            if (livro.getQuantidadeDisponivel() > 0 && !usuario.isTemEmprestimo()) {
                usuario.setTemEmprestimo(true);
                livro.emprestar();
                Date dataEmprestimo = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataEmprestimo);
                cal.add(Calendar.DAY_OF_MONTH, 7);  // Devolução em 7 dias
                Date dataDevolucaoPrevista = cal.getTime();

                Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo, dataDevolucaoPrevista);
                emprestimos.add(emprestimo);
                System.out.println("Empréstimo realizado com sucesso.");
            } else {
                System.out.println("Livro indisponível ou usuário já tem empréstimo.");
            }
        }
    }

    public void devolverLivro(Scanner scanner) {
        System.out.println("Digite o código do livro:");
        int codigoLivro = scanner.nextInt();
        System.out.println("Digite o ID do usuário:");
        int idUsuario = scanner.nextInt();

        Emprestimo emprestimo = null;
        for (Emprestimo e : emprestimos) {
            if (e.getLivro().getCodigo() == codigoLivro && e.getUsuario().getId() == idUsuario) {
                emprestimo = e;
                break;
            }
        }

        if (emprestimo != null) {
            Date dataDevolucaoReal = new Date();
            emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
            emprestimo.calcularAtraso();
            emprestimo.getLivro().devolver();
            emprestimo.getUsuario().setTemEmprestimo(false);
            System.out.println("Devolução realizada com sucesso.");
        }
    }

    public void relatorioLivrosEmprestados() {
        System.out.println("Livros Emprestados:");
        for (Emprestimo e : emprestimos) {
            if (e.getDataDevolucaoReal() == null) {
                System.out.println(e.getLivro().getTitulo() + " - Emprestado para: " + e.getUsuario().getNome());
            }
        }
    }

    public void relatorioAtrasos() {
        System.out.println("Livros Devolvidos com Atraso:");
        emprestimos.sort((e1, e2) -> e2.getDiasAtraso() - e1.getDiasAtraso());
        for (Emprestimo e : emprestimos) {
            if (e.getDiasAtraso() > 0) {
                System.out.println(e.getLivro().getTitulo() + " - " + e.getDiasAtraso() + " dias de atraso");
            }
        }
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
}
