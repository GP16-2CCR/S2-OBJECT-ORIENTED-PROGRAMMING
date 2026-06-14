package intervencao;

import modelo.TrechoRodovia;

public class RocadaMecanizada extends IntervencaoOperacional {

    public RocadaMecanizada() {
        super("Roçada Mecanizada");
    }

    @Override
    public String executarServico(TrechoRodovia trecho) {
        return String.format(
                "[%s] Equipe mecanizada despachada para KM %.1f — vegetação %.1f cm (prioridade %d).",
                getNomeServico(),
                trecho.getKm(),
                trecho.getAlturaVegetacaoCm(),
                trecho.getNivelPrioridade()
        );
    }
}
