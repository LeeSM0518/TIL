package seminar_project.management;

import seminar_project.stock.*;
import sun.security.krb5.internal.crypto.Des;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StockManagement {
    List<Stock> stocks = new ArrayList<>();
    List<Desktop> desktops = new ArrayList<>();

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

    public void inquiryStock(List<Stock> stocks) {
        Stream<Stock> stockStream = stocks.stream();

        List<Stock> CPUList = stockStream
                .filter(s -> s.getClass() == CPU.class)
                .collect(Collectors.toList());

        stockStream = stocks.stream();
        List<Stock> RAMList = stockStream
                .filter(s -> s.getClass() == RAM.class)
                .collect(Collectors.toList());

        stockStream = stocks.stream();
        List<Stock> GraphicCardList = stockStream
                .filter(s -> s.getClass() == GraphicCard.class)
                .collect(Collectors.toList());

        System.out.println("CPU 재고");
        CPUList.stream()
                .forEach(s -> {
                    System.out.println("제품명 : " + s.getHadrware() +
                            ", 성능 : " + s.getPerformance() + ", 가격 : " + s.getPrice());
                });
        System.out.println();

        System.out.println("RAM 재고");
        RAMList.stream()
                .forEach(s -> {
                    System.out.println("제품명 : " + s.getHadrware() +
                            ", 성능 : " + s.getPerformance() + ", 가격 : " + s.getPrice());
                });
        System.out.println();

        System.out.println("Graphic 재고");
        GraphicCardList.stream()
                .forEach(s -> {
                    System.out.println("제품명 : " + s.getHadrware() +
                            ", 성능 : " + s.getPerformance() + ", 가격 : " + s.getPrice());
                });
        System.out.println();

    }

    public Stock purchaseStock() {
        Stock stock;
        System.out.println("구매할 재고가 무엇입니까?");
        System.out.println("1. CPU");
        System.out.println("2. RAM");
        System.out.println("3. Graphic Card");
        System.out.print("입력 : ");
        int select = scanner.nextInt();

        while (true) {
            switch (select) {
                case 1:
                    return new CPU();
                case 2:
                    stock = new RAM();
                    return stock;
                case 3:
                    stock = new GraphicCard();
                    return stock;
                default:
                    System.out.print("다시 입력해주세요 : ");
                    select = scanner.nextInt();
            }

        }
    }

    public void addStock(Stock stock) {
        String productName = "";
        String performance = "";
        int price;

        scanner.nextLine();

        System.out.print("구매할 재고의 제품명을 입력해주세요.\n" + ": ");
        productName = scanner.nextLine();
        System.out.print("구매할 재고의 성능을 입력해주세요.\n" + ": ");
        performance = scanner.nextLine();
        System.out.print("구매할 재고의 가격을 입력해주세요.\n" + ": ");
        price = scanner.nextInt();

        stock.allSet(productName, performance, price);

        this.stocks.add(stock);
    }

    public void createDesktop(List<Stock> stocks) {
        Stream<Stock> stockStream = stocks.stream();

        List<Stock> CPUList = stockStream
                .filter(s -> s.getClass() == CPU.class)
                .collect(Collectors.toList());

        stockStream = stocks.stream();
        List<Stock> RAMList = stockStream
                .filter(s -> s.getClass() == RAM.class)
                .collect(Collectors.toList());

        stockStream = stocks.stream();
        List<Stock> GraphicCardList = stockStream
                .filter(s -> s.getClass() == GraphicCard.class)
                .collect(Collectors.toList());

        int number;

        CPU cpu;
        RAM ram;
        GraphicCard graphicCard;

        inquiryStock(stocks);

        System.out.print("본체에 넣을 CPU를 입력해주세요 : ");
        number = scanner.nextInt();
        cpu = (CPU) CPUList.get(number);

        System.out.print("본체에 넣을 RAM를 입력해주세요 : ");
        number = scanner.nextInt();
        ram = (RAM) RAMList.get(number);

        System.out.print("본체에 넣을 Graphic Card를 입력해주세요 : ");
        number = scanner.nextInt();
        graphicCard = (GraphicCard) GraphicCardList.get(number);

        Desktop desktop = new Desktop(cpu, ram, graphicCard);

        this.desktops.add(desktop);
    }



    public static void main(String[] args) {
        StockManagement stockManagement = new StockManagement();
        Stock stock;

        while (true) {
            int selectMenu = stockManagement.mainMenu();
            switch (selectMenu) {
                case 1:
                    stockManagement.inquiryStock(stockManagement.stocks);
                    break;
                case 2:
                    stock = stockManagement.purchaseStock();
                    stockManagement.addStock(stock);
                    break;
                case 3:
                    stockManagement.createDesktop(stockManagement.stocks);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    return;
            }
        }
    }
}