package intervencao;

import modelo.TrechoRodovia;

public class Pulverizacao extends IntervencaoOperacional {

    public Pulverizacao() {
        super("Pulverização Química");
    }

    @Override
    public String executarServico(TrechoRodovia trecho) {
        return String.format(
                "[%s] Equipe manual com pulverizador no KM %.1f — controle de invasoras (vegetação %.1f cm).",
                getNomeServico(),
                trecho.getKm(),
                trecho.getAlturaVegetacaoCm()
        );
    }
}
