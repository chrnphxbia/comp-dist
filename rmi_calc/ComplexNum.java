/*
 * NOME: Pedro Henrique Araujo Farias
 * RA: 10265432
*/

import java.lang.Math;

public class ComplexNum implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private double real, imag;

    public ComplexNum(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public String toString() {
        if(imag == 0) return real + "";
        if(real == 0) return imag + "i";
        if(imag <  0) return real + " - " + (-imag) + "i";
        return real + " + " + imag + "i";
    }

    public ComplexNum addComplexNum(ComplexNum other) {
        double ansReal = this.real + other.real;
        double ansImag = this.imag + other.imag;
        return new ComplexNum(ansReal, ansImag);
    }

    public ComplexNum subComplexNum(ComplexNum other) {
        double ansReal = this.real - other.real;
        double ansImag = this.imag - other.imag;
        return new ComplexNum(ansReal, ansImag);
    }

    public ComplexNum multComplexNum(ComplexNum other) {
        double ansReal = this.real * other.real - this.imag * other.imag;
        double ansImag = this.real * other.imag + this.imag * other.real;
        return new ComplexNum(ansReal, ansImag);
    }

    public double absComplexNum() {
        return Math.sqrt(this.real * this.real + this.imag * this.imag);
    }

    public ComplexNum maxComplexNum(ComplexNum other) {
        return this.absComplexNum() >= other.absComplexNum() ? this : other; 
    }

    public ComplexNum minComplexNum(ComplexNum other) {
        return this.absComplexNum() < other.absComplexNum() ? this : other; 
    }

    public ComplexNum reciprocal() {
        double scale = this.real * this.real + this.imag * this.imag;
        return new ComplexNum(this.real / scale, -this.imag / scale);
    }

    public ComplexNum divComplexNum(ComplexNum other) {
        ComplexNum answer = this;
        return answer.multComplexNum(other.reciprocal());
    }
}
