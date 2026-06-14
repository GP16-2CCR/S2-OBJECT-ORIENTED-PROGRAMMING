package servico;

import intervencao.IntervencaoOperacional;
import intervencao.Pulverizacao;
import intervencao.RocadaMecanizada;
import iot.MonitoravelViaIoT;
import modelo.TrechoRodovia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MotorPrioridade {

    private static final double PESO_ALTURA = 0.5;
    private static final double PESO_DIAS_SEM_MANUTENCAO = 0.3;
    private static final double PESO_CRESCIMENTO = 0.2;

    public RelatorioPrioridade gerarRelatorio(TrechoRodovia[] trechos) {
        List<ItemRelatorio> itens = new ArrayList<ItemRelatorio>();

        for (TrechoRodovia trecho : trechos) {
            atualizarTrechoComIoT(trecho);
            calcularPrioridade(trecho);

            if (trecho.necessitaRocadaMecanizada()) {
                IntervencaoOperacional intervencao = new RocadaMecanizada();
                itens.add(new ItemRelatorio(trecho, intervencao, trecho.getNivelPrioridade()));
            } else if (trecho.necessitaPulverizacao()) {
                IntervencaoOperacional intervencao = new Pulverizacao();
                itens.add(new ItemRelatorio(trecho, intervencao, trecho.getNivelPrioridade()));
            }
        }

        Collections.sort(itens, new Comparator<ItemRelatorio>() {
            @Override
            public int compare(ItemRelatorio a, ItemRelatorio b) {
                return Integer.compare(b.getPrioridade(), a.getPrioridade());
            }
        });

        return new RelatorioPrioridade(itens);
    }

    private void atualizarTrechoComIoT(TrechoRodovia trecho) {
        if (trecho.isEquipadoComIoT()) {
            MonitoravelViaIoT monitor = trecho;
            trecho.atualizarViaSensor(monitor.transmitirDadosSensor());
        }
    }

    private void calcularPrioridade(TrechoRodovia trecho) {
        double scoreAltura = Math.min(trecho.getAlturaVegetacaoCm() / 60.0, 1.0) * 100;
        double scoreDias = Math.min(trecho.getDiasSemManutencao() / 30.0, 1.0) * 100;
        double scoreCrescimento = Math.min(
                trecho.getComportamentoCrescimento().calcularCrescimentoDiarioCm() / 3.0,
                1.0
        ) * 100;

        int prioridade = (int) Math.round(
                scoreAltura * PESO_ALTURA
                        + scoreDias * PESO_DIAS_SEM_MANUTENCAO
                        + scoreCrescimento * PESO_CRESCIMENTO
        );

        if (trecho.isPossuiInvasoras()) {
            prioridade = Math.min(prioridade + 15, 100);
        }

        trecho.setNivelPrioridade(prioridade);
    }

    public static final class ItemRelatorio {

        private final TrechoRodovia trecho;
        private final IntervencaoOperacional intervencao;
        private final int prioridade;

        public ItemRelatorio(TrechoRodovia trecho, IntervencaoOperacional intervencao, int prioridade) {
            this.trecho = trecho;
            this.intervencao = intervencao;
            this.prioridade = prioridade;
        }

        public TrechoRodovia getTrecho() {
            return trecho;
        }

        public IntervencaoOperacional getIntervencao() {
            return intervencao;
        }

        public int getPrioridade() {
            return prioridade;
        }
    }
}
