package calculadora;

public class Principal {

    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();

        executarOperacao(calculadora, 10, 5, Calculadora.SOMA);
        executarOperacao(calculadora, 10, 5, Calculadora.SUBTRACAO);
        executarOperacao(calculadora, 10, 5, Calculadora.MULTIPLICACAO);
        executarOperacao(calculadora, 10, 4, Calculadora.DIVISAO);
        executarOperacao(calculadora, 10, 0, Calculadora.DIVISAO);
        executarOperacao(calculadora, 10, 5, "%");
    }

    static void executarOperacao(Calculadora calculadora, int a, int b, String operacao) {
        String expressao = a + " " + operacao + " " + b;
        try {
            double resultado = calculadora.calc(a, b, operacao);
            System.out.println(expressao + " = " + resultado);
        } catch (ArithmeticException | IllegalArgumentException erro) {
            System.out.println(expressao + " -> Erro: " + erro.getMessage());
        }
    }
}
