package seminar_project.ui;

import java.util.*;

public class PartsManagementUI extends StocksManagementUI {

    private static Scanner scanner = new Scanner(System.in);

    public void purchasePartsUI() {

        System.out.println("(1) CPU");
        System.out.println("(2) RAM");
        System.out.println("(3) Graphic Card");
        System.out.print("구매할 부품을 번호로 입력해주세요 : ");

    }

    private Integer selectPartNumber() {

        int select;

        while (true) {
            try {
                select = scanner.nextInt();
            } catch (InputMismatchException ex) {
                scanner.nextLine();
                System.out.println("잘못된 입력입니다. 1~3 까지의 숫자로 입력해주세요.");
                System.out.print("입력 : ");
                select = scanner.nextInt();
            }

            if (select > 3 || select < 0) {
                System.out.println("잘못된 입력입니다. 1~3 까지의 숫자로 입력해주세요.");
                System.out.print("입력 : ");
            } else {
                break;
            }

        }

        return select;
    }


    public Map<String, String> partInformation() {

        Map<String, String> information = new HashMap<>();

        int part = selectPartNumber();
        int price;

        scanner.nextLine();

        information.put("select", Integer.toString(part));
        System.out.println("==============================");
        System.out.print("구매할 부품의 제품명을 입력해주세요 : ");
        information.put("name", informationInput());
        System.out.print("구매할 부품의 성능을 입력해주세요 : ");
        information.put("performance", informationInput());

        try {
            System.out.print("구매할 재고의 가격을 입력해주세요 : ");
            price = scanner.nextInt();
        } catch (InputMismatchException exception) {
            scanner.nextLine();
            System.out.println("숫자로 입력해주세요.");
            System.out.print("입력 : ");
            price = scanner.nextInt();
        }

        information.put("price", Integer.toString(price));

        return information;

    }


    public Map<String, String> salePartsUI() {

        Map<String, String> information = new HashMap<>();

        System.out.println("==============================");
        System.out.println("(1) CPU");
        System.out.println("(2) RAM");
        System.out.println("(3) Graphic Card");
        System.out.print("판매할 부품을 번호로 입력해주세요 : ");
        information.put("select", Integer.toString(selectPartNumber()));

        scanner.nextLine();

        System.out.print("판매할 부품의 이름을 입력해주세요 : ");
        information.put("name", informationInput());

        return information;

    }

}