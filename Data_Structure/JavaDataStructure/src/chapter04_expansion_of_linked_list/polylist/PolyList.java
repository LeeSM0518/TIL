package chapter04_expansion_of_linked_list.polylist;

import chapter03_linked_list.LinkedList;
import chapter03_linked_list.Node;

import java.util.Scanner;

public class PolyList extends LinkedList {

    public void addNode(final int position, final double coef, final int degree) {
        Node newNode = new PolyNode(coef, degree);
        Node preNode = this.headerNode;

        addProcess(preNode, newNode, position);
    }

    @Override
    public void iterateList() {
        PolyNode node = (PolyNode) this.headerNode.link;

        while (node != null) {
            System.out.print(node.getCoef() + "x^" + node.getDegree());
            if (node.link != null) {
                System.out.print(" + ");
            }
            node = (PolyNode) node.link;
        }
        System.out.println();
    }

    static public void calculatePolynomials(PolyList[] polyLists) {
        PolyList result = new PolyList();

        PolyNode node = (PolyNode) polyLists[0].headerNode.link;

        while (node.link != null) {
//            result
        }
    }

    static public PolyList[] inputPolynomial() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("다항식 연산 입력 >> ");
        String poly = scanner.nextLine();
        String[] polyArr = poly.split(" ");

        PolyList[] polyLists = PolyList.insertPoly(polyArr);

        return polyLists;
    }

    static private PolyList[] insertPoly(String[] polyArr) {

        PolyList frontList = new PolyList();
        PolyList backList = new PolyList();
        PolyList list = frontList;

        for (int i = 0, j = 0; i < polyArr.length ; i += 4, j++) {
            list.addNode(j, Double.parseDouble(polyArr[i]), Integer.parseInt(polyArr[i+2]));
            list.iterateList();
            try {
                if (Integer.parseInt(polyArr[i+2]) < Integer.parseInt(polyArr[i+6])) {
                    list = backList;
                    j = -1;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

        PolyList[] lists = new PolyList[] {frontList, backList};

        return lists;
    }

    public static void main(String[] args) {
        PolyList[] polyList = new PolyList[];

        polyList = PolyList.inputPolynomial();
    }

}

// 1 x^ 3 + 1 x^ 2 + 1 x^ 0 + 2 x^ 4 + 3 x^ 3 + 2 x^ 0