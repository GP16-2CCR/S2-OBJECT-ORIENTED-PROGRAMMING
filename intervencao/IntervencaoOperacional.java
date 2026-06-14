package intervencao;

import modelo.TrechoRodovia;

/**
 * Conceito base genérico de qualquer serviço de manutenção operacional na rodovia.
 * Uma equipe não pode executar uma "intervenção" sem saber qual procedimento aplicar.
 */
public abstract class IntervencaoOperacional {

    private final String nomeServico;

    protected IntervencaoOperacional(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public abstract String executarServico(TrechoRodovia trecho);
}
