package seminar_project.ui;

import seminar_project.parts.Part;

import java.util.Scanner;

public class StocksManagementUI {

    private Scanner scanner = new Scanner(System.in);

    // TODO 부품서비스 클래스에서 조회할 때 import 해서 UI 부분 사용
    public <T> void inquiryPart(final T t) {

        Part part = (Part) t;
        System.out.println("제품명 : " + part.getProductName());
        System.out.println("성능 : " + part.getPerformance());
        System.out.println("가격 : " + part.getPrice());
        System.out.println();

    }

    // TODO 정보에 대한 입력을 확인해주는 메소드
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
