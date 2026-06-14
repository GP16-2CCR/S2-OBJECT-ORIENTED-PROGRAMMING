package servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelatorioPrioridade {

    private final List<MotorPrioridade.ItemRelatorio> itens;

    public RelatorioPrioridade(List<MotorPrioridade.ItemRelatorio> itens) {
        this.itens = Collections.unmodifiableList(new ArrayList<MotorPrioridade.ItemRelatorio>(itens));
    }

    public List<MotorPrioridade.ItemRelatorio> getItens() {
        return itens;
    }

    public int getTotalIntervencoes() {
        return itens.size();
    }

    public void imprimir() {
        String linha = repetir("=", 70);
        System.out.println(linha);
        System.out.println("       RELATÓRIO DE PRIORIDADE — MOTIVA RODOVIAS");
        System.out.println(linha);

        if (itens.isEmpty()) {
            System.out.println("Nenhum trecho requer intervenção no momento.");
            return;
        }

        for (MotorPrioridade.ItemRelatorio item : itens) {
            System.out.printf(
                    "KM %6.1f | Prioridade: %3d | %s%n",
                    item.getTrecho().getKm(),
                    item.getPrioridade(),
                    item.getIntervencao().getNomeServico()
            );
            System.out.println("  -> " + item.getIntervencao().executarServico(item.getTrecho()));
            System.out.println("  -> " + item.getTrecho().getComportamentoCrescimento().getDescricao());
            System.out.println(repetir("-", 70));
        }

        System.out.printf("Total de trechos com intervenção recomendada: %d%n", itens.size());
        System.out.println(linha);
    }

    private static String repetir(String texto, int vezes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < vezes; i++) {
            builder.append(texto);
        }
        return builder.toString();
    }
}
