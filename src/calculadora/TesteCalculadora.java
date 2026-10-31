package calculadora;

public class TesteCalculadora {

    private static int aprovados = 0;
    private static int reprovados = 0;
    private static final Calculadora calculadora = new Calculadora();

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

        if (reprovados > 0) {
            System.exit(1);
        }
    }

    static void testeSoma() {
        verificarResultado("Soma", 10, 5, "+", 15.0);
    }

    static void testeSubtracao() {
        verificarResultado("Subtracao", 10, 5, "-", 5.0);
    }

    static void testeMultiplicacao() {
        verificarResultado("Multiplicacao", 10, 5, "*", 50.0);
    }

    static void testeDivisao() {
        verificarResultado("Divisao", 10, 5, "/", 2.0);
    }

    static void testeDivisaoComResultadoDecimal() {
        verificarResultado("Divisao com resultado decimal", 7, 2, "/", 3.5);
    }

    static void testeDivisaoPorZero() {
        verificarErro("Divisao por zero", 10, 0, "/", ArithmeticException.class);
    }

    static void testeOperacaoInvalida() {
        verificarErro("Operacao invalida", 10, 5, "%", IllegalArgumentException.class);
    }

    static void testeOperacaoNula() {
        verificarErro("Operacao nula", 10, 5, null, IllegalArgumentException.class);
    }

    private static void verificarResultado(String nome, int a, int b, String op, double esperado) {
        String obtido;
        boolean passou;
        try {
            double resultado = calculadora.calc(a, b, op);
            obtido = String.valueOf(resultado);
            passou = Double.compare(resultado, esperado) == 0;
        } catch (RuntimeException e) {
            obtido = e.getClass().getSimpleName() + ": " + e.getMessage();
            passou = false;
        }
        registrar(nome, a, b, op, String.valueOf(esperado), obtido, passou);
    }

    private static void verificarErro(String nome, int a, int b, String op,
                                      Class<? extends RuntimeException> erroEsperado) {
        String obtido;
        boolean passou;
        try {
            double resultado = calculadora.calc(a, b, op);
            obtido = String.valueOf(resultado);
            passou = false;
        } catch (RuntimeException e) {
            obtido = e.getClass().getSimpleName() + ": " + e.getMessage();
            passou = erroEsperado.isInstance(e);
        }
        registrar(nome, a, b, op, erroEsperado.getSimpleName(), obtido, passou);
    }

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
