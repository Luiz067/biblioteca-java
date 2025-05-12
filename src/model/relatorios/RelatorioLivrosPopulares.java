package model.relatorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Emprestimo;
import model.Livro;
import interfaces.IRelatorio;

public class RelatorioLivrosPopulares implements IRelatorio {
    private List<Emprestimo> emprestimos;
    
    public RelatorioLivrosPopulares(List<Emprestimo> emprestimos) {
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
        Map<Livro, Integer> contagem = new HashMap<>();
        
        // Contar empréstimos por livro
        for (Emprestimo e : emprestimos) {
            Livro livro = e.getLivro();
            contagem.put(livro, contagem.getOrDefault(livro, 0) + 1);
        }
        
        // Converter para lista e ordenar
        List<Map.Entry<Livro, Integer>> lista = new ArrayList<>(contagem.entrySet());
        lista.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        // Formatar resultado
        List<String> resultado = new ArrayList<>();
        for (Map.Entry<Livro, Integer> entry : lista) {
            resultado.add(entry.getKey().getTitulo() + " - Emprestado " + entry.getValue() + " vezes");
        }
        
        return resultado;
    }
    
    @Override
    public String getNomeRelatorio() {
        return "Relatório de Livros Mais Populares";
    }
}