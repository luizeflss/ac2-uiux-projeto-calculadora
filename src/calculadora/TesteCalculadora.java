package calculadora;

/**
 * Testes unitários da classe {@link Calculadora}.
 *
 * <p>Cada teste exibe as entradas utilizadas, o resultado esperado e o
 * resultado obtido. Ao final é exibido um resumo, e o processo termina com
 * código de saída 1 se algum teste falhar.</p>
 *
 * <p>Execução: {@code java -cp bin calculadora.TesteCalculadora}</p>
 *
 * @author Luiz Eduardo
 * @version 2.0
 */
public class TesteCalculadora {

    /** Quantidade de testes aprovados. */
    private static int aprovados = 0;

    /** Quantidade de testes reprovados. */
    private static int reprovados = 0;

    /** Instância da calculadora sob teste. */
    private static final Calculadora calculadora = new Calculadora();

    /** Classe de testes: não deve ser instanciada. */
    private TesteCalculadora() {
    }

    /**
     * Executa todos os testes e exibe o resumo.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        System.out.println("===== TESTES DA CALCULADORA =====");
        System.out.println();

        testeSoma();
        testeSubtracao();
        testeMultiplicacao();
        testeDivisao();
        testeDivisaoComResultadoDecimal();
        testeDivisaoPorZero();
        testeOperacaoInvalida();
        testeOperacaoNula();

        System.out.println("=================================");
        System.out.println("Aprovados: " + aprovados + " | Reprovados: " + reprovados);

        // Código de saída diferente de zero permite detectar falhas em scripts.
        if (reprovados > 0) {
            System.exit(1);
        }
    }

    /** Testa a soma: 10 + 5 deve resultar em 15. */
    static void testeSoma() {
        verificarResultado("Soma", 10, 5, "+", 15.0);
    }

    /** Testa a subtração: 10 - 5 deve resultar em 5. */
    static void testeSubtracao() {
        verificarResultado("Subtracao", 10, 5, "-", 5.0);
    }

    /** Testa a multiplicação: 10 * 5 deve resultar em 50. */
    static void testeMultiplicacao() {
        verificarResultado("Multiplicacao", 10, 5, "*", 50.0);
    }

    /** Testa a divisão exata: 10 / 5 deve resultar em 2. */
    static void testeDivisao() {
        verificarResultado("Divisao", 10, 5, "/", 2.0);
    }

    /** Testa a divisão não exata: 7 / 2 deve resultar em 3.5. */
    static void testeDivisaoComResultadoDecimal() {
        verificarResultado("Divisao com resultado decimal", 7, 2, "/", 3.5);
    }

    /** Testa a divisão por zero: deve lançar {@link ArithmeticException}. */
    static void testeDivisaoPorZero() {
        verificarErro("Divisao por zero", 10, 0, "/", ArithmeticException.class);
    }

    /** Testa uma operação inexistente: deve lançar {@link IllegalArgumentException}. */
    static void testeOperacaoInvalida() {
        verificarErro("Operacao invalida", 10, 5, "%", IllegalArgumentException.class);
    }

    /** Testa uma operação nula: deve lançar {@link IllegalArgumentException}. */
    static void testeOperacaoNula() {
        verificarErro("Operacao nula", 10, 5, null, IllegalArgumentException.class);
    }

    /**
     * Verifica se uma operação retorna o valor esperado.
     *
     * @param nome     nome do teste exibido no relatório
     * @param a        primeiro operando
     * @param b        segundo operando
     * @param op       símbolo da operação
     * @param esperado resultado esperado
     */
    private static void verificarResultado(String nome, int a, int b, String op, double esperado) {
        String obtido;
        boolean passou;
        try {
            double resultado = calculadora.calc(a, b, op);
            obtido = String.valueOf(resultado);
            passou = Double.compare(resultado, esperado) == 0;
        } catch (RuntimeException e) {
            // Uma exceção inesperada conta como falha, sem interromper os demais testes.
            obtido = e.getClass().getSimpleName() + ": " + e.getMessage();
            passou = false;
        }
        registrar(nome, a, b, op, String.valueOf(esperado), obtido, passou);
    }

    /**
     * Verifica se uma operação lança a exceção esperada.
     *
     * @param nome         nome do teste exibido no relatório
     * @param a            primeiro operando
     * @param b            segundo operando
     * @param op           símbolo da operação
     * @param erroEsperado tipo de exceção esperado
     */
    private static void verificarErro(String nome, int a, int b, String op,
                                      Class<? extends RuntimeException> erroEsperado) {
        String obtido;
        boolean passou;
        try {
            double resultado = calculadora.calc(a, b, op);
            // Se chegou aqui, nenhuma exceção foi lançada: o teste falhou.
            obtido = String.valueOf(resultado);
            passou = false;
        } catch (RuntimeException e) {
            obtido = e.getClass().getSimpleName() + ": " + e.getMessage();
            passou = erroEsperado.isInstance(e);
        }
        registrar(nome, a, b, op, erroEsperado.getSimpleName(), obtido, passou);
    }

    /**
     * Contabiliza e imprime o resultado de um teste.
     *
     * @param nome     nome do teste
     * @param a        primeiro operando
     * @param b        segundo operando
     * @param op       símbolo da operação
     * @param esperado descrição do resultado esperado
     * @param obtido   descrição do resultado obtido
     * @param passou   {@code true} se o teste foi aprovado
     */
    private static void registrar(String nome, int a, int b, String op,
                                  String esperado, String obtido, boolean passou) {
        if (passou) {
            aprovados++;
        } else {
            reprovados++;
        }
        System.out.println("[" + (passou ? "PASSOU" : "FALHOU") + "] " + nome);
        System.out.println("  Entradas: a = " + a + ", b = " + b + ", op = \"" + op + "\"");
        System.out.println("  Esperado: " + esperado);
        System.out.println("  Obtido:   " + obtido);
        System.out.println();
    }
}
