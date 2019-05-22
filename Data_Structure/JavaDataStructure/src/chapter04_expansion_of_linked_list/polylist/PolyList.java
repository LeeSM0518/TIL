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

    static public PolyList calculatePolynomials(final PolyList[] polyLists) {
        PolyList resultList = new PolyList();

        PolyNode nodeA = (PolyNode) polyLists[0].headerNode.link;
        PolyNode nodeB = (PolyNode) polyLists[1].headerNode.link;

        int j = 0;
        double coefSum;

        while (nodeA != null && nodeB != null) {
            int degreeA = nodeA.getDegree();
            int degreeB = nodeB.getDegree();

            if (degreeA > degreeB) {
                resultList.addNode(j++, nodeA.getCoef(), degreeA);
                nodeA = (PolyNode) nodeA.link;
            } else if (degreeA == degreeB) {
                coefSum = nodeA.getCoef() + nodeB.getCoef();
                resultList.addNode(j++, coefSum, degreeA);
                nodeA = (PolyNode) nodeA.link;
                nodeB = (PolyNode) nodeB.link;
            } else {
                resultList.addNode(j++, nodeB.getCoef(), degreeB);
                nodeB = (PolyNode) nodeB.link;
            }
        }

        while (nodeA != null) {
            resultList.addNode(j++, nodeA.getCoef(), nodeA.getDegree());
            nodeA = (PolyNode) nodeA.link;
        }

        while (nodeB != null) {
            resultList.addNode(j++, nodeB.getCoef(), nodeB.getDegree());
            nodeB = (PolyNode) nodeB.link;
        }

        return resultList;
    }

    static public PolyList[] inputPolynomial() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("다항식 연산 입력 >> ");
        String poly = scanner.nextLine();
        String[] polyArr = poly.split(" ");

        PolyList[] polyLists = PolyList.insertPoly(polyArr);

        return polyLists;
    }

    static private PolyList[] insertPoly(final String[] polyArr) {

        PolyList frontList = new PolyList();
        PolyList backList = new PolyList();
        PolyList list = frontList;

        for (int i = 0, j = 0; i < polyArr.length ; i += 4, j++) {

            // 다항식 시작이 "-" 일 경우
            if (i == 0 && polyArr[i].equals("-")) {
                polyArr[i+2] = "-".concat(polyArr[i+2]);
                i += 1;
            }

            // 항이 음수일 경우
            if (i > 0 && polyArr[i-1].equals("-")) {
                polyArr[i] = "-".concat(polyArr[i]);
            }


            list.addNode(j, Double.parseDouble(polyArr[i]), Integer.parseInt(polyArr[i+2]));
            try {
                // 같은 차수일 경
                if (polyArr[i+2].equals(polyArr[i+6])) {
                    int sum = Integer.parseInt(polyArr[i]) + Integer.parseInt(polyArr[i+4]);
                    polyArr[i+4] = String.valueOf(sum);
                    list.removeData(j);
                    j--;
                }

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
        PolyList[] polyList;

        while (true) {
            polyList = PolyList.inputPolynomial();

            PolyList result = PolyList.calculatePolynomials(polyList);

            result.iterateList();
        }

    }

}

// 1 x^ 3 + 1 x^ 2 + 1 x^ 0 + 2 x^ 4 + 3 x^ 3 + 2 x^ 0
// - 1 x^ 3 + 1 x^ 2 + 1 x^ 0 + 2 x^ 4 + 3 x^ 3 + 2 x^ 0
// - 1 x^ 3 - 1 x^ 2 - 1 x^ 0 - 2 x^ 4 - 3 x^ 3 - 2 x^ 0
// 4 x^ 4 + 3 x^ 4