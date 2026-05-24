import java.util.Arrays;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.TextStyle;

import java.util.Locale;

public class A3SistemadePonto {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String[] nomes = new String[0];
        String[] rgs = new String[0];

        int[] profissaoIndex = new int[0];

        double[] saldos = new double[0];

        double[] entradas = new double[0];
        double[] saidaAlmoco = new double[0];
        double[] retornoAlmoco = new double[0];

        boolean[] ativos = new boolean[0];
        boolean[] emAlmoco = new boolean[0];

        int[] diasTrabalhados = new int[0];

        String[] historicoDias = new String[0];

        int totalFuncionarios = 0;

        String[] profissoes = {
                "Engenheiro Civil",
                "Mestre de Obra",
                "Pedreiro",
                "Pintor",
                "Eletricista",
                "Encanador",
                "Armador",
                "Ajudante"
        };

        double[] diarias = {
                600,
                400,
                290,
                290,
                290,
                290,
                290,
                145
        };

        String[] produtos = {
                "Marmita Simples",
                "Marmita Executiva",
                "Cafe Completo",
                "Cafe Puro",
                "Passagem",
                "Refrigerante 350ml",
                "Cigarro Picado",
                "Cigarro Maco"
        };

        double[] precos = {
                15.0,
                28.50,
                6.0,
                2.5,
                6.0,
                4.0,
                1.5,
                10.0
        };

        int opcao;

        do {

            linha();

            System.out.println("======= SISTEMA DE PONTO =======");

            linha();

            System.out.println("1 - Entrada");
            System.out.println("2 - Loja");
            System.out.println("3 - Saida");
            System.out.println("4 - Almoco");
            System.out.println("5 - Listar Funcionarios");
            System.out.println("0 - Encerrar");

            linha();

            opcao = lerInt("Escolha: ");

            switch (opcao) {

                // =====================================
                // ENTRADA
                // =====================================

                case 1:

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("RG: ");
                    String rg = sc.nextLine();

                    if (!confirmarBiometria()) {

                        System.out.println(
                                "Biometria nao confirmada!"
                        );

                        break;
                    }

                    int f = buscarPorRG(
                            rgs,
                            rg,
                            totalFuncionarios
                    );

                    // RG DUPLICADO
                    if (f != -1 &&
                            !nomes[f].equalsIgnoreCase(nome)) {

                        System.out.println(
                                "RG pertence a outro funcionario!"
                        );

                        break;
                    }

                    // NOVO FUNCIONARIO
                    if (f == -1) {

                        nomes = Arrays.copyOf(
                                nomes,
                                totalFuncionarios + 1
                        );

                        rgs = Arrays.copyOf(
                                rgs,
                                totalFuncionarios + 1
                        );

                        profissaoIndex = Arrays.copyOf(
                                profissaoIndex,
                                totalFuncionarios + 1
                        );

                        saldos = Arrays.copyOf(
                                saldos,
                                totalFuncionarios + 1
                        );

                        entradas = Arrays.copyOf(
                                entradas,
                                totalFuncionarios + 1
                        );

                        saidaAlmoco = Arrays.copyOf(
                                saidaAlmoco,
                                totalFuncionarios + 1
                        );

                        retornoAlmoco = Arrays.copyOf(
                                retornoAlmoco,
                                totalFuncionarios + 1
                        );

                        ativos = Arrays.copyOf(
                                ativos,
                                totalFuncionarios + 1
                        );

                        emAlmoco = Arrays.copyOf(
                                emAlmoco,
                                totalFuncionarios + 1
                        );

                        diasTrabalhados = Arrays.copyOf(
                                diasTrabalhados,
                                totalFuncionarios + 1
                        );

                        historicoDias = Arrays.copyOf(
                                historicoDias,
                                totalFuncionarios + 1
                        );

                        nomes[totalFuncionarios] = nome;

                        rgs[totalFuncionarios] = rg;

                        profissaoIndex[totalFuncionarios] =
                                escolherProfissao(profissoes);

                        saldos[totalFuncionarios] = 0;

                        ativos[totalFuncionarios] = false;

                        emAlmoco[totalFuncionarios] = false;

                        entradas[totalFuncionarios] = 0;

                        saidaAlmoco[totalFuncionarios] = 0;

                        retornoAlmoco[totalFuncionarios] = 0;

                        diasTrabalhados[totalFuncionarios] = 0;

                        historicoDias[totalFuncionarios] = "";

                        f = totalFuncionarios;

                        totalFuncionarios++;
                    }

                    if (ativos[f]) {

                        System.out.println(
                                "Funcionario ja esta trabalhando!"
                        );

                        break;
                    }

                    entradas[f] =
                            lerHora("Hora entrada: ");

                    ativos[f] = true;

                    emAlmoco[f] = false;

                    saidaAlmoco[f] = 0;

                    retornoAlmoco[f] = 0;

                    LocalDate hoje =
                            LocalDate.now();

                    DayOfWeek diaSemana =
                            hoje.getDayOfWeek();

                    String nomeDia =
                            diaSemana.getDisplayName(
                                    TextStyle.FULL,
                                    new Locale("pt", "BR")
                            );

                    linha();

                    System.out.println(
                            "Data: "
                                    + hoje.getDayOfMonth()
                                    + "/"
                                    + hoje.getMonthValue()
                                    + "/"
                                    + hoje.getYear()
                    );

                    System.out.println(
                            "Dia da semana: "
                                    + nomeDia
                    );

                    // BENEFICIO SEGUNDA
                    if (diaSemana == DayOfWeek.MONDAY) {

                        double passagem = 60;

                        double cafe = 25;

                        double almoco = 75;

                        double totalBeneficios =
                                passagem
                                        + cafe
                                        + almoco;

                        saldos[f] += totalBeneficios;

                        System.out.printf(
                                "Beneficios semanais: R$ %.2f\n",
                                totalBeneficios
                        );
                    }

                    System.out.println(
                            "Entrada registrada!"
                    );

                    break;

                // =====================================
                // LOJA
                // =====================================

                case 2:

                    int idxLoja =
                            selecionarFuncionario(
                                    nomes,
                                    totalFuncionarios
                            );

                    if (idxLoja == -1) break;

                    if (!confirmarBiometria()) {

                        System.out.println(
                                "Biometria nao confirmada!"
                        );

                        break;
                    }

                    if (!ativos[idxLoja]) {

                        System.out.println(
                                "Funcionario nao esta ativo!"
                        );

                        break;
                    }

                    int escolha;

                    do {

                        linha();

                        System.out.printf(
                                "Saldo atual: R$ %.2f\n",
                                saldos[idxLoja]
                        );

                        linha();

                        System.out.println(
                                "======= LOJA ======="
                        );

                        for (int i = 0;
                             i < produtos.length;
                             i++) {

                            System.out.printf(
                                    "%d - %s | R$ %.2f\n",
                                    (i + 1),
                                    produtos[i],
                                    precos[i]
                            );
                        }

                        System.out.println("0 - Sair");

                        escolha =
                                lerInt("Escolha: ");

                        if (escolha > 0
                                &&
                                escolha <= produtos.length) {

                            int qtd =
                                    lerInt("Quantidade: ");

                            if (qtd <= 0) {

                                System.out.println(
                                        "Quantidade invalida!"
                                );

                                continue;
                            }

                            double valor =
                                    precos[escolha - 1]
                                            * qtd;

                            if (saldos[idxLoja] < valor) {

                                System.out.println(
                                        "Saldo insuficiente!"
                                );

                            } else {

                                saldos[idxLoja] -= valor;

                                System.out.printf(
                                        "Compra: %s x%d | Total R$ %.2f\n",
                                        produtos[escolha - 1],
                                        qtd,
                                        valor
                                );
                            }
                        }

                    } while (escolha != 0);

                    break;

                // =====================================
                // SAIDA
                // =====================================

                case 3:

                    int idxSaida =
                            selecionarFuncionario(
                                    nomes,
                                    totalFuncionarios
                            );

                    if (idxSaida == -1) break;

                    if (!confirmarBiometria()) {

                        System.out.println(
                                "Biometria nao confirmada!"
                        );

                        break;
                    }

                    if (!ativos[idxSaida]) {

                        System.out.println(
                                "Funcionario nao esta ativo!"
                        );

                        break;
                    }

                    if (emAlmoco[idxSaida]) {

                        System.out.println(
                                "Funcionario ainda esta em almoco!"
                        );

                        break;
                    }

                    double saida =
                            lerHora(
                                    "Hora saida final: "
                            );

                    if (saida <= entradas[idxSaida]) {

                        System.out.println(
                                "Horario invalido!"
                        );

                        break;
                    }

                    if (retornoAlmoco[idxSaida] > 0
                            &&
                            saida <= retornoAlmoco[idxSaida]) {

                        System.out.println(
                                "Saida nao pode ser antes do retorno!"
                        );

                        break;
                    }

                    double horasManha;

                    double horasTarde;

                    // TEM ALMOCO
                    if (saidaAlmoco[idxSaida] > 0
                            &&
                            retornoAlmoco[idxSaida] > 0) {

                        horasManha =
                                saidaAlmoco[idxSaida]
                                        - entradas[idxSaida];

                        horasTarde =
                                saida
                                        - retornoAlmoco[idxSaida];

                    } else {

                        horasManha =
                                saida
                                        - entradas[idxSaida];

                        horasTarde = 0;
                    }

                    double horas =
                            horasManha + horasTarde;

                    if (horas <= 0) {

                        System.out.println(
                                "Horas invalidas!"
                        );

                        break;
                    }

                    // VALOR HORA
                    double valorHora =
                            diarias[profissaoIndex[idxSaida]]
                                    / 8.0;

                    double pagamento;

                    // ATE 8 HORAS
                    if (horas <= 8) {

                        pagamento =
                                valorHora * horas;

                    } else {

                        double normal =
                                valorHora * 8;

                        double horasExtras =
                                horas - 8;

                        // EXTRA 20%
                        double valorExtra =
                                valorHora * 1.2;

                        double extra =
                                horasExtras
                                        * valorExtra;

                        pagamento =
                                normal + extra;

                        System.out.printf(
                                "Horas extras: %.2f\n",
                                horasExtras
                        );

                        System.out.printf(
                                "Valor hora extra: R$ %.2f\n",
                                valorExtra
                        );

                        System.out.printf(
                                "Total extra: R$ %.2f\n",
                                extra
                        );
                    }

                    saldos[idxSaida] += pagamento;

                    ativos[idxSaida] = false;

                    diasTrabalhados[idxSaida]++;

                    LocalDate hojeSaida =
                            LocalDate.now();

                    historicoDias[idxSaida] +=
                            hojeSaida.getDayOfMonth()
                                    + "/"
                                    + hojeSaida.getMonthValue()
                                    + " ";

                    linha();

                    System.out.printf(
                            "Horas trabalhadas: %.2f\n",
                            horas
                    );

                    System.out.printf(
                            "Valor hora: R$ %.2f\n",
                            valorHora
                    );

                    System.out.printf(
                            "Pagamento dia: R$ %.2f\n",
                            pagamento
                    );

                    System.out.printf(
                            "Saldo atual: R$ %.2f\n",
                            saldos[idxSaida]
                    );

                    break;

                // =====================================
                // ALMOCO
                // =====================================

                case 4:

                    int idxAlmoco =
                            selecionarFuncionario(
                                    nomes,
                                    totalFuncionarios
                            );

                    if (idxAlmoco == -1) break;

                    if (!confirmarBiometria()) {

                        System.out.println(
                                "Biometria nao confirmada!"
                        );

                        break;
                    }

                    if (!ativos[idxAlmoco]) {

                        System.out.println(
                                "Funcionario nao esta trabalhando!"
                        );

                        break;
                    }

                    // SAIDA ALMOCO
                    if (!emAlmoco[idxAlmoco]) {

                        saidaAlmoco[idxAlmoco] =
                                lerHora(
                                        "Hora saida almoco: "
                                );

                        if (saidaAlmoco[idxAlmoco]
                                <= entradas[idxAlmoco]) {

                            System.out.println(
                                    "Horario invalido!"
                            );

                            break;
                        }

                        emAlmoco[idxAlmoco] = true;

                        System.out.println(
                                "Saida para almoco registrada!"
                        );

                    } else {

                        retornoAlmoco[idxAlmoco] =
                                lerHora(
                                        "Hora retorno almoco: "
                                );

                        if (retornoAlmoco[idxAlmoco]
                                <= saidaAlmoco[idxAlmoco]) {

                            System.out.println(
                                    "Horario invalido!"
                            );

                            break;
                        }

                        double tempoAlmoco =
                                retornoAlmoco[idxAlmoco]
                                        - saidaAlmoco[idxAlmoco];

                        if (tempoAlmoco < 1) {

                            System.out.println(
                                    "Almoco minimo 1 hora!"
                            );

                            break;
                        }

                        emAlmoco[idxAlmoco] = false;

                        System.out.println(
                                "Retorno almoco registrado!"
                        );
                    }

                    break;

                // =====================================
                // LISTAR
                // =====================================

                case 5:

                    if (totalFuncionarios == 0) {

                        System.out.println(
                                "Nenhum funcionario!"
                        );

                    } else {

                        for (int i = 0;
                             i < totalFuncionarios;
                             i++) {

                            linha();

                            System.out.println(
                                    "Nome: "
                                            + nomes[i]
                            );

                            System.out.println(
                                    "RG: "
                                            + rgs[i]
                            );

                            System.out.println(
                                    "Profissao: "
                                            + profissoes[profissaoIndex[i]]
                            );

                            System.out.println(
                                    "Dias trabalhados: "
                                            + diasTrabalhados[i]
                            );

                            System.out.println(
                                    "Datas trabalhadas: "
                                            + historicoDias[i]
                            );

                            System.out.printf(
                                    "Salario semanal: R$ %.2f\n",
                                    saldos[i]
                            );

                            System.out.println(
                                    "Status: "
                                            + (ativos[i]
                                            ? "Trabalhando"
                                            : "Fora da obra")
                            );

                            System.out.println(
                                    "Em almoco: "
                                            + (emAlmoco[i]
                                            ? "Sim"
                                            : "Nao")
                            );
                        }
                    }

                    break;

                // =====================================
                // ENCERRAR
                // =====================================

                case 0:

                    System.out.println(
                            "Sistema encerrado!"
                    );

                    break;

                default:

                    System.out.println(
                            "Opcao invalida!"
                    );
            }

        } while (opcao != 0);
    }

    // =====================================
    // FUNCOES
    // =====================================

    static void linha() {

        System.out.println(
                "====================================="
        );
    }

    static boolean confirmarBiometria() {

        System.out.print(
                "Biometria confirmada? (s/n): "
        );

        String bio = sc.nextLine();

        return bio.equalsIgnoreCase("s");
    }

    static int selecionarFuncionario(
            String[] nomes,
            int total
    ) {

        if (total == 0) {

            System.out.println(
                    "Nenhum funcionario!"
            );

            return -1;
        }

        linha();

        for (int i = 0; i < total; i++) {

            System.out.println(
                    i + " - " + nomes[i]
            );
        }

        int escolha;

        do {

            escolha =
                    lerInt(
                            "Escolha funcionario: "
                    );

        } while (escolha < 0 || escolha >= total);

        return escolha;
    }

    static int buscarPorRG(
            String[] rgs,
            String rg,
            int total
    ) {

        for (int i = 0; i < total; i++) {

            if (rgs[i].equals(rg)) {

                return i;
            }
        }

        return -1;
    }

    static int lerInt(String msg) {

        while (true) {

            try {

                System.out.print(msg);

                return Integer.parseInt(
                        sc.nextLine()
                );

            } catch (Exception e) {

                System.out.println(
                        "Numero invalido!"
                );
            }
        }
    }

    static double lerHora(String msg) {

        while (true) {

            try {

                System.out.print(msg);

                double h =
                        Double.parseDouble(
                                sc.nextLine()
                        );

                if (h >= 0 && h <= 24) {

                    return h;

                } else {

                    System.out.println(
                            "Digite hora entre 0 e 24!"
                    );
                }

            } catch (Exception e) {

                System.out.println(
                        "Numero invalido!"
                );
            }
        }
    }

    static int escolherProfissao(
            String[] profissoes
    ) {

        int escolha;

        do {

            linha();

            System.out.println(
                    "======= PROFISSOES ======="
            );

            for (int i = 0;
                 i < profissoes.length;
                 i++) {

                System.out.println(
                        (i + 1)
                                + " - "
                                + profissoes[i]
                );
            }

            escolha =
                    lerInt("Escolha: ") - 1;

        } while (
                escolha < 0
                        ||
                        escolha >= profissoes.length
        );

        return escolha;
    }
}

