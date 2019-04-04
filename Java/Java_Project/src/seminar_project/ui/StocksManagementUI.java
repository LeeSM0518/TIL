package seminar_project.ui;

import seminar_project.parts.Part;

import java.util.Scanner;

public class StocksManagementUI {

    private Scanner scanner = new Scanner(System.in);

    // 하나의 부품의 정보들을 조회하는 메소드
    public <T> void inquiryPart(final T t) {

        Part part = (Part) t;
        System.out.println("제품명 : " + part.getProductName());
        System.out.println("성능 : " + part.getPerformance());
        System.out.println("가격 : " + part.getPrice());
        System.out.println();

    }

    // 문자열 정보를 입력할 때 사용하는 메소드
    public String informationInput() {

        String information = scanner.nextLine();

        if (information.equals("")) information = scanner.nextLine();

        while (true) {
            if (information.equals("")) {
                System.out.println("다시 입력해주세요.");
                System.out.print("입력 : ");
                information = scanner.nextLine();
            } else {
                return information;
            }
        }

    }

}
