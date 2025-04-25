// ALUNOS:
// Luiz Gustavo de Oliveira Amaral - 32830238
// Axel Biernastki Otto - 37612956
// Igor Ferreira Chagas - 37617681
package app;

import controller.LibrarySystem;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibrarySystem library = new LibrarySystem();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastro de Livro");
            System.out.println("2. Pesquisa de Livro");
            System.out.println("3. Cadastro de Usuário");
            System.out.println("4. Empréstimo de Livro");
            System.out.println("5. Devolução de Livro");
            System.out.println("6. Relatório de Livros Emprestados");
            System.out.println("7. Relatório de Atrasos");
            System.out.println("8. Sair");

            int option = scanner.nextInt();
            scanner.nextLine(); 
            switch (option) {
                case 1:
                    library.cadastrarLivro(scanner);
                    break;
                case 2:
                    library.pesquisarLivro(scanner);
                    break;
                case 3:
                    library.cadastrarUsuario(scanner);
                    break;
                case 4:
                    library.emprestarLivro(scanner);
                    break;
                case 5:
                    library.devolverLivro(scanner);
                    break;
                case 6:
                    library.relatorioLivrosEmprestados();
                    break;
                case 7:
                    library.relatorioAtrasos();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
