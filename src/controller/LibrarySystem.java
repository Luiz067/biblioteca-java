package controller;

import model.*;
import model.relatorios.*;
import interfaces.IRelatorio;
import java.util.*;

public class LibrarySystem {
    private List<ItemBiblioteca> itens = new ArrayList<>();
    private List<Pessoa> pessoas = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private int livroId = 1;
    private int usuarioId = 1;

    // Construtor
    public LibrarySystem() {
        // Inicializar com a pré-carga
        try {
            PreCarga.carregar(this);
        } catch (Exception e) {
            System.out.println("Erro na pré-carga: " + e.getMessage());
        }
    }

    public void cadastrarLivro(Scanner scanner) {
        try {
            System.out.println("Digite o título do livro:");
            String titulo = scanner.nextLine();
            if (titulo.trim().isEmpty()) {
                throw new BibliotecaException("O título não pode estar vazio");
            }
            
            System.out.println("Digite o autor do livro:");
            String autor = scanner.nextLine();
            if (autor.trim().isEmpty()) {
                throw new BibliotecaException("O autor não pode estar vazio");
            }
            
            System.out.println("Digite o ano de publicação do livro:");
            int anoPublicacao = Integer.parseInt(scanner.nextLine());
            if (anoPublicacao <= 0) {
                throw new BibliotecaException("Ano de publicação inválido");
            }
            
            System.out.println("Digite a quantidade de exemplares:");
            int quantidadeDisponivel = Integer.parseInt(scanner.nextLine());
            if (quantidadeDisponivel <= 0) {
                throw new BibliotecaException("A quantidade disponível deve ser maior que zero");
            }
            
            System.out.println("Digite a categoria do livro:");
            String categoria = scanner.nextLine();
            if (categoria.trim().isEmpty()) {
                throw new BibliotecaException("A categoria não pode estar vazia");
            }

            Livro livro = new Livro(titulo, autor, anoPublicacao, quantidadeDisponivel, categoria, livroId++);
            itens.add(livro);
            System.out.println("Livro cadastrado com sucesso.");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Por favor, digite um número válido.");
        } catch (BibliotecaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public void pesquisarLivro(Scanner scanner) {
        try {
            System.out.println("Digite o código, título, autor ou categoria do livro:");
            String termo = scanner.nextLine().toLowerCase();
            
            boolean encontrou = false;
            for (ItemBiblioteca item : itens) {
                if (item instanceof Livro) {
                    Livro livro = (Livro) item;
                    if (String.valueOf(livro.getCodigo()).equals(termo) || 
                        livro.getTitulo().toLowerCase().contains(termo) || 
                        livro.getAutor().toLowerCase().contains(termo) ||
                        livro.getCategoria().toLowerCase().contains(termo)) {
                        
                        System.out.println(livro.exibirDetalhes());
                        encontrou = true;
                    }
                }
            }
            
            if (!encontrou) {
                System.out.println("Nenhum livro encontrado com o termo: " + termo);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar livro: " + e.getMessage());
        }
    }

    public void cadastrarUsuario(Scanner scanner) {
        try {
            System.out.println("Digite o nome do usuário:");
            String nome = scanner.nextLine();
            if (nome.trim().isEmpty()) {
                throw new BibliotecaException("O nome não pode estar vazio");
            }
            
            System.out.println("Digite o telefone do usuário:");
            String telefone = scanner.nextLine();
            if (telefone.trim().isEmpty()) {
                throw new BibliotecaException("O telefone não pode estar vazio");
            }
            
            System.out.println("Digite o endereço do usuário:");
            String endereco = scanner.nextLine();
            if (endereco.trim().isEmpty()) {
                throw new BibliotecaException("O endereço não pode estar vazio");
            }
            
            System.out.println("Digite o e-mail do usuário:");
            String email = scanner.nextLine();
            if (email.trim().isEmpty() || !email.contains("@")) {
                throw new BibliotecaException("E-mail inválido");
            }

            Usuario usuario = new Usuario(nome, telefone, endereco, email, usuarioId++);
            pessoas.add(usuario);
            System.out.println("Usuário cadastrado com sucesso.");
        } catch (BibliotecaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public void emprestarLivro(Scanner scanner) {
        try {
            System.out.println("Digite o código do livro:");
            int codigoLivro = Integer.parseInt(scanner.nextLine());
            
            System.out.println("Digite o ID do usuário:");
            int idUsuario = Integer.parseInt(scanner.nextLine());
            
            ItemBiblioteca item = null;
            for (ItemBiblioteca i : itens) {
                if (i.getCodigo() == codigoLivro) {
                    item = i;
                    break;
                }
            }
            
            if (!(item instanceof Livro)) {
                throw new BibliotecaException("Item não encontrado ou não é um livro");
            }
            
            Livro livro = (Livro) item;
            
            Usuario usuario = null;
            for (Pessoa p : pessoas) {
                if (p instanceof Usuario && ((Usuario) p).getId() == idUsuario) {
                    usuario = (Usuario) p;
                    break;
                }
            }
            
            if (usuario == null) {
                throw new BibliotecaException("Usuário não encontrado");
            }
            
            if (livro.getQuantidadeDisponivel() <= 0) {
                throw new BibliotecaException("Livro não disponível para empréstimo");
            }
            
            if (usuario.isTemEmprestimo()) {
                throw new BibliotecaException("Usuário já possui um empréstimo ativo");
            }
                
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
            
        } catch (NumberFormatException e) {
            System.out.println("Erro: Por favor, digite um número válido.");
        } catch (BibliotecaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public void devolverLivro(Scanner scanner) {
        try {
            System.out.println("Digite o código do livro:");
            int codigoLivro = Integer.parseInt(scanner.nextLine());
            
            System.out.println("Digite o ID do usuário:");
            int idUsuario = Integer.parseInt(scanner.nextLine());
            
            Emprestimo emprestimo = null;
            for (Emprestimo e : emprestimos) {
                if (e.getLivro().getCodigo() == codigoLivro && e.getUsuario().getId() == idUsuario && e.getDataDevolucaoReal() == null) {
                    emprestimo = e;
                    break;
                }
            }
            
            if (emprestimo == null) {
                throw new BibliotecaException("Empréstimo não encontrado ou já devolvido");
            }
            
            Date dataDevolucaoReal = new Date();
            emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
            emprestimo.calcularAtraso();
            emprestimo.getLivro().devolver();
            emprestimo.getUsuario().setTemEmprestimo(false);
            
            System.out.println("Devolução realizada com sucesso.");
            if (emprestimo.getDiasAtraso() > 0) {
                System.out.println("Atenção: Livro devolvido com " + emprestimo.getDiasAtraso() + " dias de atraso.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Erro: Por favor, digite um número válido.");
        } catch (BibliotecaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public void relatorioLivrosEmprestados() {
        try {
            IRelatorio relatorio = new RelatorioLivrosEmprestados(emprestimos);
            relatorio.gerarRelatorio();
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    public void relatorioAtrasos() {
        try {
            IRelatorio relatorio = new RelatorioAtrasos(emprestimos);
            relatorio.gerarRelatorio();
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }
    
    public void relatorioLivrosPopulares() {
        try {
            IRelatorio relatorio = new RelatorioLivrosPopulares(emprestimos);
            relatorio.gerarRelatorio();
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    public void adicionarItemBiblioteca(ItemBiblioteca item) {
        if (item != null) {
            itens.add(item);
        }
    }
    
    public void adicionarPessoa(Pessoa pessoa) {
        if (pessoa != null) {
            pessoas.add(pessoa);
        }
    }
}