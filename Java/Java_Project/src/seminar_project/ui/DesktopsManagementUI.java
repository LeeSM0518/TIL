package seminar_project.ui;

import seminar_project.desktop.Desktop;

import java.util.List;
import java.util.Scanner;

public class DesktopsManagementUI extends StocksManagementUI {

    private Scanner scanner = new Scanner(System.in);

    // TODO selectPart()
    //  1. 부품을 입력한다.
    //  2. 부품 서비스에서 존재하는지 확인한다.
    public String inputPartNameUI() {

        System.out.println("부품의 제품명을 입력해주세요.");
        System.out.print("입력 : ");
        String partName = informationInput();
        scanner.nextLine();
        System.out.println();

        return partName;
    }

    // TODO inquiryDesktopUI()
    //  1. 데스크탑 서비스에서 Desktop 스트림을 받는다.
    //  2. Desktop 스트림을 forEach 로 출력시킨다.
    public void inquiryDesktopsUI(final List<Desktop> desktops) {

        System.out.println();
        System.out.println("Desktops 재고");
        desktops.forEach(desktop -> {
            System.out.println("데스크탑 제품명 : " + desktop.getDesktopName());
            desktopInformation(desktop);
            System.out.println("+++++++++++++++++");
        });

    }

    private void desktopInformation(final Desktop desktop) {

        System.out.println("CPU 정보");
        inquiryPart(desktop.getCpu());

        System.out.println("RAM 정보");
        inquiryPart(desktop.getRam());

        System.out.println("Graphic Card 정보");
        inquiryPart(desktop.getGraphicCard());

        System.out.println("데스크탑의 가격 : " + desktop.getPrice());

    }

    public String saleDesktopUI() {

        System.out.print("판매하실 데스크탑의 제품명을 입력해주세요 : ");

        return informationInput();

    }

}
