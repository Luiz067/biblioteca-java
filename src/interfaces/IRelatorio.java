package interfaces;

import java.util.List;

public interface IRelatorio {
    void gerarRelatorio();
    List<String> gerarListagemFormatada();
    String getNomeRelatorio();
}