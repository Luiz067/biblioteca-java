package model.relatorios;

import java.util.ArrayList;
import java.util.List;
import model.Emprestimo;
import interfaces.IRelatorio;

public class RelatorioLivrosEmprestados implements IRelatorio {
    private List<Emprestimo> emprestimos;
    
    public RelatorioLivrosEmprestados(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
    
    @Override
    public void gerarRelatorio() {
        System.out.println("======= " + getNomeRelatorio() + " =======");
        for (String linha : gerarListagemFormatada()) {
            System.out.println(linha);
        }
        System.out.println("==================================");
    }
    
    @Override
    public List<String> gerarListagemFormatada() {
        List<String> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getDataDevolucaoReal() == null) {
                resultado.add(e.getLivro().getTitulo() + " - Emprestado para: " + e.getUsuario().getNome());
            }
        }
        return resultado;
    }
    
    @Override
    public String getNomeRelatorio() {
        return "Relatório de Livros Emprestados";
    }
}