package chapter04_expansion_of_linked_list.polylist;

import chapter03_linked_list.Node;

public class PolyNode extends Node {

    private double coef;    // 계수
    private int degree;     // 차수

    public PolyNode(){}

    public PolyNode(final double coef, final int degree) {
        this.coef = coef;
        this.degree = degree;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}