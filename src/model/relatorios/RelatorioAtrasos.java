package model.relatorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Emprestimo;
import interfaces.IRelatorio;

public class RelatorioAtrasos implements IRelatorio {
    private List<Emprestimo> emprestimos;
    
    public RelatorioAtrasos(List<Emprestimo> emprestimos) {
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
        List<Emprestimo> emprestimosComAtraso = emprestimos.stream()
            .filter(e -> e.getDiasAtraso() > 0)
            .sorted((e1, e2) -> e2.getDiasAtraso() - e1.getDiasAtraso())
            .collect(Collectors.toList());
            
        List<String> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimosComAtraso) {
            resultado.add(e.getLivro().getTitulo() + " - " + e.getDiasAtraso() + " dias de atraso - Usuário: " + e.getUsuario().getNome());
        }
        return resultado;
    }
    
    @Override
    public String getNomeRelatorio() {
        return "Relatório de Livros em Atraso";
    }
}