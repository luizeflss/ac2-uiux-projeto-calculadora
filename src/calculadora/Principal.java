package calculadora;

/**
 * Ponto de entrada que demonstra o uso da {@link Calculadora}.
 *
 * <p>Executa uma sequência de operações, incluindo casos de erro,
 * e mostra que o programa continua rodando mesmo quando uma
 * operação falha.</p>
 *
 * @author Luiz Eduardo
 * @version 2.0
 */
public class Principal {

    /** Classe utilitária: não deve ser instanciada. */
    private Principal() {
    }

    /**
     * Executa a demonstração da calculadora.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();

        executarOperacao(calculadora, 10, 5, Calculadora.SOMA);
        executarOperacao(calculadora, 10, 5, Calculadora.SUBTRACAO);
        executarOperacao(calculadora, 10, 5, Calculadora.MULTIPLICACAO);
        executarOperacao(calculadora, 10, 4, Calculadora.DIVISAO);
        // Casos de erro: a execução deve prosseguir normalmente após eles.
        executarOperacao(calculadora, 10, 0, Calculadora.DIVISAO);
        executarOperacao(calculadora, 10, 5, "%");
    }

    /**
     * Executa uma operação e imprime o resultado ou a mensagem de erro.
     *
     * @param calculadora instância usada para o cálculo
     * @param a           primeiro operando
     * @param b           segundo operando
     * @param operacao    símbolo da operação
     */
    static void executarOperacao(Calculadora calculadora, int a, int b, String operacao) {
        String expressao = a + " " + operacao + " " + b;
        try {
            double resultado = calculadora.calc(a, b, operacao);
            System.out.println(expressao + " = " + resultado);
        } catch (ArithmeticException | IllegalArgumentException erro) {
            // O erro é exibido ao usuário em vez de encerrar o programa.
            System.out.println(expressao + " -> Erro: " + erro.getMessage());
        }
    }
}
