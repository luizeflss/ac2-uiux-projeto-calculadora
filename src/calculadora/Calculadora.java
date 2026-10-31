package calculadora;

/**
 * Módulo de cálculos matemáticos básicos do aplicativo FinançApp.
 *
 * <p>Oferece as quatro operações fundamentais (soma, subtração,
 * multiplicação e divisão), usadas em funcionalidades como cálculo de
 * orçamento mensal, divisão de despesas e projeção de economia.</p>
 *
 * <p>Os resultados são retornados como {@code double} para que divisões
 * não exatas (por exemplo, {@code 7 / 2 = 3.5}) não percam a parte decimal.
 * Situações de erro são sinalizadas por exceções com mensagens claras,
 * permitindo que quem chama trate o problema sem interromper o programa.</p>
 *
 * @author Luiz Eduardo
 * @version 2.0
 */
public class Calculadora {

    /** Símbolo da operação de soma. */
    public static final String SOMA = "+";

    /** Símbolo da operação de subtração. */
    public static final String SUBTRACAO = "-";

    /** Símbolo da operação de multiplicação. */
    public static final String MULTIPLICACAO = "*";

    /** Símbolo da operação de divisão. */
    public static final String DIVISAO = "/";

    /** Mensagem usada quando se tenta dividir por zero. */
    static final String MENSAGEM_DIVISAO_POR_ZERO = "Nao e possivel dividir por zero";

    /** Prefixo da mensagem usada quando a operação informada não é suportada. */
    static final String MENSAGEM_OPERACAO_INVALIDA = "Operacao invalida: ";

    /**
     * Cria uma nova calculadora. A classe não guarda estado entre cálculos.
     */
    public Calculadora() {
    }

    /**
     * Executa a operação matemática indicada sobre dois números inteiros.
     *
     * @param a  primeiro operando
     * @param b  segundo operando
     * @param op símbolo da operação: {@code "+"}, {@code "-"}, {@code "*"} ou {@code "/"}
     * @return o resultado da operação
     * @throws ArithmeticException      se {@code op} for {@code "/"} e {@code b} for zero
     * @throws IllegalArgumentException se {@code op} for {@code null} ou não for uma operação suportada
     */
    public double calc(int a, int b, String op) {
        // O switch sobre String lança NullPointerException com null,
        // por isso a operação é validada antes.
        validarOperacao(op);

        switch (op) {
            case SOMA:
                return somar(a, b);
            case SUBTRACAO:
                return subtrair(a, b);
            case MULTIPLICACAO:
                return multiplicar(a, b);
            case DIVISAO:
                return dividir(a, b);
            default:
                throw operacaoInvalida(op);
        }
    }

    /**
     * Soma dois números inteiros.
     *
     * @param primeiroNumero primeira parcela
     * @param segundoNumero  segunda parcela
     * @return a soma das parcelas
     */
    public double somar(int primeiroNumero, int segundoNumero) {
        // A conversão para double antes da operação evita estouro de int.
        return (double) primeiroNumero + segundoNumero;
    }

    /**
     * Subtrai um número inteiro de outro.
     *
     * @param minuendo   valor do qual se subtrai
     * @param subtraendo valor a ser subtraído
     * @return a diferença {@code minuendo - subtraendo}
     */
    public double subtrair(int minuendo, int subtraendo) {
        return (double) minuendo - subtraendo;
    }

    /**
     * Multiplica dois números inteiros.
     *
     * @param primeiroFator primeiro fator
     * @param segundoFator  segundo fator
     * @return o produto dos fatores
     */
    public double multiplicar(int primeiroFator, int segundoFator) {
        return (double) primeiroFator * segundoFator;
    }

    /**
     * Divide um número inteiro por outro, mantendo a parte decimal.
     *
     * @param dividendo valor a ser dividido
     * @param divisor   valor pelo qual se divide; não pode ser zero
     * @return o quociente {@code dividendo / divisor}
     * @throws ArithmeticException se {@code divisor} for zero
     */
    public double dividir(int dividendo, int divisor) {
        validarDivisor(divisor);
        return (double) dividendo / divisor;
    }

    /**
     * Garante que uma operação foi informada.
     *
     * @param operacao símbolo da operação recebido
     * @throws IllegalArgumentException se {@code operacao} for {@code null}
     */
    private void validarOperacao(String operacao) {
        if (operacao == null) {
            throw operacaoInvalida(null);
        }
    }

    /**
     * Impede a divisão por zero.
     *
     * <p>Sem esta verificação, a divisão em {@code double} retornaria
     * {@code Infinity} ou {@code NaN} em vez de sinalizar o erro.</p>
     *
     * @param divisor valor a ser usado como divisor
     * @throws ArithmeticException se {@code divisor} for zero
     */
    private void validarDivisor(int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException(MENSAGEM_DIVISAO_POR_ZERO);
        }
    }

    /**
     * Cria a exceção padrão para operações não suportadas.
     *
     * @param operacao símbolo da operação recebido
     * @return exceção com a mensagem de operação inválida
     */
    private IllegalArgumentException operacaoInvalida(String operacao) {
        return new IllegalArgumentException(MENSAGEM_OPERACAO_INVALIDA + operacao);
    }
}
