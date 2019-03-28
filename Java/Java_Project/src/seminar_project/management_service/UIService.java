package seminar_project.management_service;

import java.util.Scanner;

public class UIService {

    Scanner scanner = new Scanner(System.in);


    public int mainMenu() {
        System.out.println("1. 재고 조회");
        System.out.println("2. 재고 구매");
        System.out.println("3. 본체 제작");
        System.out.println("4. 본체 조회");
        System.out.println("5. 본체 판매");
        System.out.println("6. 종료");
        System.out.print("입력 : ");
        return scanner.nextInt();
    }

}