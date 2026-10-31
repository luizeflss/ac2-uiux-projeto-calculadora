package calculadora;

public class Calculadora {

    public double calc(int a, int b, String op) {
        if (op == null) {
            throw new IllegalArgumentException("Operacao invalida: null");
        }
        if (op.equals("+")) {
            return a + b;
        } else if (op.equals("-")) {
            return a - b;
        } else if (op.equals("*")) {
            return (double) a * b;
        } else if (op.equals("/")) {
            if (b == 0) {
                throw new ArithmeticException("Nao e possivel dividir por zero");
            } else {
                return (double) a / b;
            }
        } else {
            throw new IllegalArgumentException("Operacao invalida: " + op);
        }
    }

    public static void main(String[] args) {
        Calculadora c = new Calculadora();
        String[] ops = {"+", "-", "*", "/", "/", "%"};
        int[] x = {10, 10, 10, 10, 10, 10};
        int[] y = {5, 5, 5, 4, 0, 5};
        for (int i = 0; i < ops.length; i++) {
            try {
                double r = c.calc(x[i], y[i], ops[i]);
                System.out.println(x[i] + " " + ops[i] + " " + y[i] + " = " + r);
            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println(x[i] + " " + ops[i] + " " + y[i] + " -> Erro: " + e.getMessage());
            }
        }
    }
}
