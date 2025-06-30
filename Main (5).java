// Template from assignment
import java.math.*;
import java.util.Scanner;

public class Main { // changed for replit
  public static void main(String[] args) {
    // Prompt the user to enter two Rational numbers
    Scanner input = new Scanner(System.in);
    System.out.print("Enter rational r1 with numerator and denominator seperated by a space: ");
    String n1 = input.next();
    String d1 = input.next();

    System.out.print("Enter rational r2 with numerator and denominator seperated by a space: ");
    String n2 = input.next();
    String d2 = input.next();

    RationalUsingBigInteger r1 = new RationalUsingBigInteger(
      new BigInteger(n1), new BigInteger(d1));
    RationalUsingBigInteger r2 = new RationalUsingBigInteger(
      new BigInteger(n2), new BigInteger(d2));

    // Display results
    System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
    System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
    System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
    System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
    System.out.println(r2 + " is " + r2.doubleValue());
  }
}

class RationalUsingBigInteger extends Number
        implements Comparable<RationalUsingBigInteger> {

    // Data fields for numerator and denominator
    private BigInteger numerator = BigInteger.ZERO;
    private BigInteger denominator = BigInteger.ONE;

    // constructor
    public RationalUsingBigInteger(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        // keep denominator positive
        if (denominator.signum() < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }
        // redice fraction
        BigInteger G = numerator.gcd(denominator);
        this.numerator = numerator.divide(G);
        this.denominator = denominator.divide(G);
    }
    // constructor: 0/1
    public RationalUsingBigInteger() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }
    // add
    public RationalUsingBigInteger add(RationalUsingBigInteger other) {
        BigInteger newNum = numerator.multiply(other.denominator)
                .add(other.numerator.multiply(denominator));
        BigInteger newDenom = denominator.multiply(other.denominator);
        return new RationalUsingBigInteger(newNum, newDenom);
    }
    // substract
    public RationalUsingBigInteger subtract(RationalUsingBigInteger other) {
        BigInteger newNum = numerator.multiply(other.denominator)
                .subtract(other.numerator.multiply(denominator));
        BigInteger newDenom = denominator.multiply(other.denominator);
        return new RationalUsingBigInteger(newNum, newDenom);
    }
    // multiply
    public RationalUsingBigInteger multiply(RationalUsingBigInteger other) {
        BigInteger newNum = numerator.multiply(other.numerator);
        BigInteger newDenom = denominator.multiply(other.denominator);
        return new RationalUsingBigInteger(newNum, newDenom);
    }
    // divide
    public RationalUsingBigInteger divide(RationalUsingBigInteger other) {
        if (other.numerator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Division by zero");
        }
        BigInteger newNum = numerator.multiply(other.denominator);
        BigInteger newDenom = denominator.multiply(other.numerator);
        return new RationalUsingBigInteger(newNum, newDenom);
    }
    // toString
    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE)) {
            return numerator.toString();
        }
        return numerator + "/" + denominator;
    }
    // compareTo
    @Override
    public int compareTo(RationalUsingBigInteger other) {
        BigInteger lhs = numerator.multiply(other.denominator);
        BigInteger rhs = other.numerator.multiply(denominator);
        return lhs.compareTo(rhs);
    }
    // value types below            
    @Override
    public int intValue() {
        return numerator.divide(denominator).intValue();
    }

    @Override
    public long longValue() {
        return numerator.divide(denominator).longValue();
    }

    @Override
    public float floatValue() {
        return new java.math.BigDecimal(numerator).divide(new java.math.BigDecimal(denominator), java.math.MathContext.DECIMAL32).floatValue();
    }
    
    @Override
    public double doubleValue() {
        return new java.math.BigDecimal(numerator).divide(new java.math.BigDecimal(denominator), java.math.MathContext.DECIMAL64).doubleValue();
    }
}