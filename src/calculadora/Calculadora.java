package calculadora;

public class Calculadora {

    public static final String SOMA = "+";
    public static final String SUBTRACAO = "-";
    public static final String MULTIPLICACAO = "*";
    public static final String DIVISAO = "/";

    static final String MENSAGEM_DIVISAO_POR_ZERO = "Nao e possivel dividir por zero";
    static final String MENSAGEM_OPERACAO_INVALIDA = "Operacao invalida: ";

    public double calc(int a, int b, String op) {
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

    public double somar(int primeiroNumero, int segundoNumero) {
        return (double) primeiroNumero + segundoNumero;
    }

    public double subtrair(int minuendo, int subtraendo) {
        return (double) minuendo - subtraendo;
    }

    public double multiplicar(int primeiroFator, int segundoFator) {
        return (double) primeiroFator * segundoFator;
    }

    public double dividir(int dividendo, int divisor) {
        validarDivisor(divisor);
        return (double) dividendo / divisor;
    }

    private void validarOperacao(String operacao) {
        if (operacao == null) {
            throw operacaoInvalida(null);
        }
    }

    private void validarDivisor(int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException(MENSAGEM_DIVISAO_POR_ZERO);
        }
    }

    private IllegalArgumentException operacaoInvalida(String operacao) {
        return new IllegalArgumentException(MENSAGEM_OPERACAO_INVALIDA + operacao);
    }
}
