package modelo;

import java.util.Objects;

public final class DadosSensor {

    private final double km;
    private final double alturaVegetacaoCm;
    private final double umidadePercentual;
    private final long timestampMillis;

    public DadosSensor(double km, double alturaVegetacaoCm, double umidadePercentual, long timestampMillis) {
        this.km = km;
        this.alturaVegetacaoCm = alturaVegetacaoCm;
        this.umidadePercentual = umidadePercentual;
        this.timestampMillis = timestampMillis;
    }

    public double getKm() {
        return km;
    }

    public double getAlturaVegetacaoCm() {
        return alturaVegetacaoCm;
    }

    public double getUmidadePercentual() {
        return umidadePercentual;
    }

    public long getTimestampMillis() {
        return timestampMillis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DadosSensor that = (DadosSensor) o;
        return Double.compare(that.km, km) == 0
                && Double.compare(that.alturaVegetacaoCm, alturaVegetacaoCm) == 0
                && Double.compare(that.umidadePercentual, umidadePercentual) == 0
                && timestampMillis == that.timestampMillis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(km, alturaVegetacaoCm, umidadePercentual, timestampMillis);
    }
}
