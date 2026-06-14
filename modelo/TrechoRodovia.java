package modelo;

import crescimento.ComportamentoCrescimento;
import iot.MonitoravelViaIoT;

public class TrechoRodovia implements MonitoravelViaIoT {

    private static final double LIMITE_ROCADA_MECANIZADA_CM = 40.0;
    private static final double LIMITE_PULVERIZACAO_CM = 25.0;

    private final double km;
    private final TipoTerreno tipoTerreno;
    private final ComportamentoCrescimento comportamentoCrescimento;
    private final boolean equipadoComIoT;
    private final boolean possuiInvasoras;

    private double alturaVegetacaoCm;
    private int diasSemManutencao;
    private int nivelPrioridade;

    public TrechoRodovia(
            double km,
            TipoTerreno tipoTerreno,
            ComportamentoCrescimento comportamentoCrescimento,
            double alturaVegetacaoCm,
            boolean equipadoComIoT,
            boolean possuiInvasoras,
            int diasSemManutencao
    ) {
        this.km = km;
        this.tipoTerreno = tipoTerreno;
        this.comportamentoCrescimento = comportamentoCrescimento;
        this.alturaVegetacaoCm = alturaVegetacaoCm;
        this.equipadoComIoT = equipadoComIoT;
        this.possuiInvasoras = possuiInvasoras;
        this.diasSemManutencao = diasSemManutencao;
        this.nivelPrioridade = 0;
    }

    public double getKm() {
        return km;
    }

    public TipoTerreno getTipoTerreno() {
        return tipoTerreno;
    }

    public ComportamentoCrescimento getComportamentoCrescimento() {
        return comportamentoCrescimento;
    }

    public double getAlturaVegetacaoCm() {
        return alturaVegetacaoCm;
    }

    public void setAlturaVegetacaoCm(double alturaVegetacaoCm) {
        this.alturaVegetacaoCm = alturaVegetacaoCm;
    }

    public boolean isEquipadoComIoT() {
        return equipadoComIoT;
    }

    public boolean isPossuiInvasoras() {
        return possuiInvasoras;
    }

    public int getDiasSemManutencao() {
        return diasSemManutencao;
    }

    public void setDiasSemManutencao(int diasSemManutencao) {
        this.diasSemManutencao = diasSemManutencao;
    }

    public int getNivelPrioridade() {
        return nivelPrioridade;
    }

    public void setNivelPrioridade(int nivelPrioridade) {
        this.nivelPrioridade = nivelPrioridade;
    }

    public boolean necessitaRocadaMecanizada() {
        return alturaVegetacaoCm >= LIMITE_ROCADA_MECANIZADA_CM && !possuiInvasoras;
    }

    public boolean necessitaPulverizacao() {
        return possuiInvasoras || (alturaVegetacaoCm >= LIMITE_PULVERIZACAO_CM && alturaVegetacaoCm < LIMITE_ROCADA_MECANIZADA_CM);
    }

    public boolean necessitaIntervencao() {
        return necessitaRocadaMecanizada() || necessitaPulverizacao();
    }

    public void simularPassagemDias(int dias) {
        double crescimento = comportamentoCrescimento.calcularCrescimentoDiarioCm() * dias;
        alturaVegetacaoCm += crescimento;
        diasSemManutencao += dias;
    }

    @Override
    public DadosSensor transmitirDadosSensor() {
        if (!equipadoComIoT) {
            throw new IllegalStateException("Trecho KM " + km + " não possui sensor IoT instalado.");
        }

        double umidade = tipoTerreno == TipoTerreno.UMIDO ? 78.0 : 32.0;
        return new DadosSensor(km, alturaVegetacaoCm, umidade, System.currentTimeMillis());
    }

    public void atualizarViaSensor(DadosSensor dados) {
        if (dados.getKm() != km) {
            throw new IllegalArgumentException("Dados do sensor não correspondem ao KM do trecho.");
        }
        alturaVegetacaoCm = dados.getAlturaVegetacaoCm();
    }

    @Override
    public String toString() {
        return String.format(
                "KM %.1f | %s | %.1f cm | IoT: %s | Invasoras: %s",
                km,
                tipoTerreno.getDescricao(),
                alturaVegetacaoCm,
                equipadoComIoT ? "Sim" : "Não",
                possuiInvasoras ? "Sim" : "Não"
        );
    }
}
