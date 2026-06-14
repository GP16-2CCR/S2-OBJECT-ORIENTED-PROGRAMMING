package crescimento;

public class CrescimentoSeco implements ComportamentoCrescimento {

    private static final double TAXA_CRESCIMENTO_CM_DIA = 0.8;

    @Override
    public double calcularCrescimentoDiarioCm() {
        return TAXA_CRESCIMENTO_CM_DIA;
    }

    @Override
    public String getDescricao() {
        return "Crescimento lento em solo seco (" + TAXA_CRESCIMENTO_CM_DIA + " cm/dia)";
    }
}
