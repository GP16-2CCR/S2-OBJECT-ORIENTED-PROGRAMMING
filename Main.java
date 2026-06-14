import crescimento.ComportamentoCrescimento;
import modelo.TipoTerreno;
import modelo.TrechoRodovia;
import servico.GerenciadorTrechos;
import servico.MotorPrioridade;
import servico.RelatorioPrioridade;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final GerenciadorTrechos gerenciador = new GerenciadorTrechos();
    private static final Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  MOTIVA RODOVIAS — Sprint 2");
        System.out.println("  Monitoramento e Priorizacao de Rocada");
        System.out.println("==============================================");
        System.out.println();

        boolean executando = true;
        while (executando) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    cadastrarTrecho();
                    break;
                case 2:
                    listarTrechos();
                    break;
                case 3:
                    removerTrecho();
                    break;
                case 4:
                    simularPassagemDias();
                    break;
                case 5:
                    gerarRelatorio();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Encerrando sistema. Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
            System.out.println();
        }
    }

    private static void exibirMenu() {
        System.out.println("-------------- MENU --------------");
        System.out.println("1 - Cadastrar trecho");
        System.out.println("2 - Listar trechos");
        System.out.println("3 - Remover trecho");
        System.out.println("4 - Simular passagem de dias");
        System.out.println("5 - Gerar Relatorio de Prioridade");
        System.out.println("0 - Sair");
        System.out.println("----------------------------------");
    }

    private static void cadastrarTrecho() {
        System.out.println("--- Cadastro de Trecho ---");

        double km = lerDouble("KM do trecho: ");

        if (gerenciador.buscarPorKm(km) != null) {
            System.out.println("ERRO: ja existe trecho neste KM.");
            return;
        }

        System.out.println("Tipo de terreno: 1-Umido | 2-Seco");
        int tipoOpcao = lerInteiro("Opcao: ");
        TipoTerreno tipo = tipoOpcao == 1 ? TipoTerreno.UMIDO : TipoTerreno.SECO;

        double altura = lerDouble("Altura da vegetacao (cm): ");
        boolean iot = lerSimNao("Possui sensor IoT? (s/n): ");
        boolean invasoras = lerSimNao("Possui plantas invasoras? (s/n): ");
        int dias = lerInteiro("Dias sem manutencao: ");

        ComportamentoCrescimento comportamento = GerenciadorTrechos.criarComportamento(tipo);
        TrechoRodovia trecho = new TrechoRodovia(km, tipo, comportamento, altura, iot, invasoras, dias);

        try {
            gerenciador.adicionar(trecho);
            System.out.println("Trecho cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private static void listarTrechos() {
        System.out.println("--- Trechos Cadastrados ---");
        List<TrechoRodovia> trechos = gerenciador.listar();

        if (trechos.isEmpty()) {
            System.out.println("Nenhum trecho cadastrado.");
            return;
        }

        for (int i = 0; i < trechos.size(); i++) {
            System.out.println((i + 1) + ". " + trechos.get(i));
        }
        System.out.println("Total: " + trechos.size() + " trecho(s)");
    }

    private static void removerTrecho() {
        if (gerenciador.estaVazio()) {
            System.out.println("Nenhum trecho para remover.");
            return;
        }

        double km = lerDouble("KM do trecho a remover: ");
        if (gerenciador.removerPorKm(km)) {
            System.out.println("Trecho KM " + km + " removido.");
        } else {
            System.out.println("Trecho nao encontrado.");
        }
    }

    private static void simularPassagemDias() {
        if (gerenciador.estaVazio()) {
            System.out.println("Cadastre trechos antes de simular.");
            return;
        }

        int dias = lerInteiro("Quantos dias deseja simular? ");
        if (dias <= 0) {
            System.out.println("Informe um valor maior que zero.");
            return;
        }

        gerenciador.simularDiasEmTodos(dias);
        System.out.println("Crescimento simulado em todos os trechos por " + dias + " dia(s).");
        listarTrechos();
    }

    private static void gerarRelatorio() {
        if (gerenciador.estaVazio()) {
            System.out.println("Cadastre trechos antes de gerar o relatorio.");
            return;
        }

        MotorPrioridade motor = new MotorPrioridade();
        RelatorioPrioridade relatorio = motor.gerarRelatorio(gerenciador.paraArray());
        relatorio.imprimir();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(leitor.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero inteiro.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(leitor.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero.");
            }
        }
    }

    private static boolean lerSimNao(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String resposta = leitor.nextLine().trim().toLowerCase();
            if (resposta.equals("s") || resposta.equals("sim")) {
                return true;
            }
            if (resposta.equals("n") || resposta.equals("nao") || resposta.equals("não")) {
                return false;
            }
            System.out.println("Responda com s ou n.");
        }
    }
}
