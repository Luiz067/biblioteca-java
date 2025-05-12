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
            try {
                System.out.println("\n===== SISTEMA DE GERENCIAMENTO DE BIBLIOTECA =====");
                System.out.println("1. Cadastro de Livro");
                System.out.println("2. Pesquisa de Livro");
                System.out.println("3. Cadastro de Usuário");
                System.out.println("4. Empréstimo de Livro");
                System.out.println("5. Devolução de Livro");
                System.out.println("6. Relatório de Livros Emprestados");
                System.out.println("7. Relatório de Atrasos");
                System.out.println("8. Relatório de Livros Populares");
                System.out.println("9. Sair");
                System.out.print("Escolha uma opção: ");

                int option = Integer.parseInt(scanner.nextLine());
                
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
                        library.relatorioLivrosPopulares();
                        break;
                    case 9:
                        System.out.println("Saindo do sistema...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
                
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
                
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
                scanner.nextLine(); // Limpar buffer
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine(); // Limpar buffer
            }
        }
    }
}