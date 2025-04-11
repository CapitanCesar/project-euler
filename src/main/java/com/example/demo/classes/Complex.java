package com.example.demo.classes;

public class Complex {
    private final double re;
    private final double im;

    public Complex(double real, double imag) {
        this.re = real;
        this.im = imag;
    }

    public double re() {
        return re;
    }

    public double im() {
        return im;
    }

    public Complex plus(Complex b) {
        return new Complex(this.re + b.re, this.im + b.im);
    }

    public Complex minus(Complex b) {
        return new Complex(this.re - b.re, this.im - b.im);
    }

    public Complex times(Complex b) {
        return new Complex(
                this.re * b.re - this.im * b.im,
                this.re * b.im + this.im * b.re
        );
    }

    public Complex scale(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    public Complex conjugate() {
        return new Complex(re, -im);
    }

    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    public String toString() {
        return String.format("(%f,%f)", re, im);
    }
}
