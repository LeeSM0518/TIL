package seminar_project.management_service;

import seminar_project.parts.CPU;
import seminar_project.parts.GraphicCard;
import seminar_project.parts.RAM;
import seminar_project.parts.Stock;

import java.util.ArrayList;
import java.util.List;

public class PartsManagementService implements StockManagement{

    List<Stock> stocks = new ArrayList<Stock>();

    @Override
    public <T> void inquiry(List<T> list) {

    }

    @Override
    public <T> void purchase(List<T> list) {
        Stock stock;
        String select;

        scanner.nextLine();

        System.out.println("구매할 재고가 무엇입니까?");
        System.out.println("1. CPU");
        System.out.println("2. RAM");
        System.out.println("3. Graphic Card");
        System.out.print("입력 : ");
        select = scanner.nextLine();

        while (true) {
            switch (select) {
                case "1":
                    return new CPU();
                case "2":
                    stock = new RAM();
                    return stock;
                case "3":
                    stock = new GraphicCard();
                    return stock;
                default:
                    System.out.print("다시 입력해주세요 : ");
                    select = scanner.nextLine();
            }

        }
    }

    @Override
    public <T> void sale(List<T> list) {

    }

    public static void main(String[] args) {

    }
}
