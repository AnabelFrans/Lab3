abstract class Function {
    abstract double getValue(double x);
    abstract Function getDerivative();
}

class F1 extends Function {
    double a, b;
    F1(double a, double b) {
        this.a = a;
        this.b = b;
    }
    double getValue(double x) {
        return Math.pow(x, 2) * Math.pow(Math.abs(Math.atan(Math.pow(x, 2)) - 1), 1/3) - Math.log(x + b);
    }
    Function getDerivative() {
        return new CompositeF1(a, b);
    }
}

class F2 extends Function {
    double a, b;
    F2(double a, double b) {
        this.a = a;
        this.b = b;
    }
    double getValue(double x) {
        return x * Math.exp(Math.sqrt(a * x)) + b / Math.abs(Math.pow(Math.sin(a * x + b), 3));
    }
    Function getDerivative() {
        return new CompositeF2(a, b);
    }
}

class CompositeF1 extends Function {
    Function f;
    double a, b;
    CompositeF1(double a, double b) {
        this.a = a;
        this.b = b;
        this.f = new F1(a, b);
    }
    double getValue(double x) {
        return f.getValue(x);
    }
    Function getDerivative() {
        return new CompositeF1Derivative(a, b, f.getDerivative());
    }
}

class CompositeF1Derivative extends Function {
    Function f;
    double a, b;
    CompositeF1Derivative(double a, double b, Function f) {
        this.f = f;
        this.a = a;
        this.b = b;
    }
    double getValue(double x) {
        double factor = Math.sqrt(Math.abs(a * x - 1));
        double firstTerm = 2 * x * factor + Math.pow(x, 2) * (a * factor) / (2 * factor);
        double secondTerm = 3 * Math.pow(Math.sin(x + b), 2) * Math.cos(x + b);
        return firstTerm - secondTerm;
    }
    Function getDerivative() {
        return new CompositeF1Derivative(a, b, f.getDerivative());
    }
}

class CompositeF2 extends Function {
    Function f;
    double a, b;
    CompositeF2(double a, double b) {
        this.a = a;
        this.b = b;
        this.f = new F2(a, b);
    }
    double getValue(double x) {
        return f.getValue(x);
    }
    Function getDerivative() {
        return new CompositeF2Derivative(a, b, f.getDerivative());
    }
}

class CompositeF2Derivative extends Function {
    Function f;
    double a, b;
    CompositeF2Derivative(double a, double b, Function f) {
        this.f = f;
        this.a = a;
        this.b = b;
    }
    double getValue(double x) {
        double firstTerm = -a / Math.pow(x, 2);
        double secondTerm = 3 * Math.pow(Math.tan(a * x + b), 2) * a * Math.pow(1 / Math.cos(a * x + b), 2);
        return firstTerm - secondTerm;
    }
    Function getDerivative() {
        return new CompositeF2Derivative(a, b, f.getDerivative());
    }
}

public class Main {
    public static void main(String[] args) {
        double a = -7, b = 5, x = -0.1;
        
        // compute the derivatives of the functions at x=-0.1
        Function f1 = new F1(a, b);
        Function f1Derivative = f1.getDerivative();
        double f1DerivativeValue = f1Derivative.getValue(x);

        Function f2 = new F2(a, b);
        Function f2Derivative = f2.getDerivative();
        double f2DerivativeValue = f2Derivative.getValue(x);

        // print the results
        System.out.println("f1(x) = " + f1.getValue(x));
        System.out.println("f1'(x) = " + f1DerivativeValue);

        System.out.println("f2(x) = " + f2.getValue(x));
        System.out.println("f2'(x) = " + f2DerivativeValue);
    }
}
