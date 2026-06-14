package crescimento;

public class CrescimentoUmido implements ComportamentoCrescimento {

    private static final double TAXA_CRESCIMENTO_CM_DIA = 2.5;

    @Override
    public double calcularCrescimentoDiarioCm() {
        return TAXA_CRESCIMENTO_CM_DIA;
    }

    @Override
    public String getDescricao() {
        return "Crescimento acelerado em solo úmido (" + TAXA_CRESCIMENTO_CM_DIA + " cm/dia)";
    }
}
