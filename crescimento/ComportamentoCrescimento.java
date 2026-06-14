package crescimento;

/**
 * Define como a vegetação evolui em diferentes condições ambientais do trecho.
 */
public interface ComportamentoCrescimento {

    double calcularCrescimentoDiarioCm();

    String getDescricao();
}
