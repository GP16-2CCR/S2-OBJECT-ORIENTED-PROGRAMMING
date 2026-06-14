package servico;

import crescimento.ComportamentoCrescimento;
import crescimento.CrescimentoSeco;
import crescimento.CrescimentoUmido;
import modelo.TipoTerreno;
import modelo.TrechoRodovia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GerenciadorTrechos {

    private final List<TrechoRodovia> trechos = new ArrayList<TrechoRodovia>();

    public void adicionar(TrechoRodovia trecho) {
        if (buscarPorKm(trecho.getKm()) != null) {
            throw new IllegalArgumentException("Ja existe trecho cadastrado no KM " + trecho.getKm());
        }
        trechos.add(trecho);
    }

    public boolean removerPorKm(double km) {
        Iterator<TrechoRodovia> it = trechos.iterator();
        while (it.hasNext()) {
            if (it.next().getKm() == km) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public TrechoRodovia buscarPorKm(double km) {
        for (TrechoRodovia trecho : trechos) {
            if (trecho.getKm() == km) {
                return trecho;
            }
        }
        return null;
    }

    public List<TrechoRodovia> listar() {
        return new ArrayList<TrechoRodovia>(trechos);
    }

    public int quantidade() {
        return trechos.size();
    }

    public boolean estaVazio() {
        return trechos.isEmpty();
    }

    public TrechoRodovia[] paraArray() {
        return trechos.toArray(new TrechoRodovia[trechos.size()]);
    }

    public void simularDiasEmTodos(int dias) {
        for (TrechoRodovia trecho : trechos) {
            trecho.simularPassagemDias(dias);
        }
    }

    public static ComportamentoCrescimento criarComportamento(TipoTerreno tipo) {
        if (tipo == TipoTerreno.UMIDO) {
            return new CrescimentoUmido();
        }
        return new CrescimentoSeco();
    }
}
