//package seminar_project.management;
//
//import seminar_project.desktop.Desktop;
//import seminar_project.parts.*;
//
//import java.util.ArrayList;
//import java.util.InputMismatchException;
//import java.util.List;
//import java.util.Scanner;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class StockManagement {
//    List<Parts> stocks = new ArrayList<>();
//    List<Desktop> desktops = new ArrayList<>();
//
//    static List<Parts> CPUList;
//    static List<Parts> RAMList;
//    static List<Parts> GraphicCardList;
//
//    Scanner scanner = new Scanner(System.in);
//
//    static int number = 1;
//
//    static CPU removeCPU;
//    static GraphicCard removeGraphic;
//    static RAM removeRAM;
//
//    public int mainMenu() {
//        System.out.println("1. 재고 조회");
//        System.out.println("2. 재고 구매");
//        System.out.println("3. 본체 제작");
//        System.out.println("4. 본체 조회");
//        System.out.println("5. 본체 판매");
//        System.out.println("6. 종료");
//        System.out.print("입력 : ");
//        return scanner.nextInt();
//    }
//
//    public void inquiryStock(List<Parts> stocks) {
//        Stream<Parts> stockStream = stocks.stream();
//
//        CPUList = stockStream
//                .filter(s -> s.getClass() == CPU.class)
//                .collect(Collectors.toList());
//
//        stockStream = stocks.stream();
//        RAMList = stockStream
//                .filter(s -> s.getClass() == RAM.class)
//                .collect(Collectors.toList());
//
//        stockStream = stocks.stream();
//        GraphicCardList = stockStream
//                .filter(s -> s.getClass() == GraphicCard.class)
//                .collect(Collectors.toList());
//
//        System.out.println();
//
//        number = 1;
//        System.out.println("CPU_NUM 재고");
//        CPUList.stream()
//                .forEach(s -> {
//                    System.out.println(number++ + ". " + "제품명 : " + s.getProductName() +
//                            ", 성능 : " + s.getPerformance() + ", 가격 : " + s.getPrice());
//                });
//        System.out.println();
//
//        number = 1;
//        System.out.println("RAM_NUM 재고");
//        RAMList.stream()
//                .forEach(s -> {
//                    System.out.println(number++ + ". " + "제품명 : " + s.getProductName() +
//                            ", 성능 : " + s.getPerformance() + ", 가격 : " + s.getPrice());
//                });
//        System.out.println();
//
//        number = 1;
//        System.out.println("Graphic 재고");
//        GraphicCardList.stream()
//                .forEach(s -> {
//                    System.out.println(number++ + ". " + "제품명 : " + s.getProductName() +
//                            ", 성능 : " + s.getPerformance() + ", 가격 : " + s.getPrice());
//                });
//        System.out.println();
//
//    }
//
//    public Parts purchaseStock() {
//        Parts stock;
//        String select;
//
//        scanner.nextLine();
//
//        System.out.println("구매할 재고가 무엇입니까?");
//        System.out.println("1. CPU_NUM");
//        System.out.println("2. RAM_NUM");
//        System.out.println("3. Graphic Card");
//        System.out.print("입력 : ");
//        select = scanner.nextLine();
//
//        while (true) {
//            switch (select) {
//                case "1":
//                    return new CPU();
//                case "2":
//                    stock = new RAM();
//                    return stock;
//                case "3":
//                    stock = new GraphicCard();
//                    return stock;
//                default:
//                    System.out.print("다시 입력해주세요 : ");
//                    select = scanner.nextLine();
//            }
//
//        }
//    }
//
//    public void addStock(Parts stock) {
//        String productName = "";
//        String performance = "";
//        int price;
//
//        scanner.nextLine();
//
//        System.out.print("구매할 재고의 제품명을 입력해주세요.\n" + ": ");
//        productName = scanner.nextLine();
//        System.out.print("구매할 재고의 성능을 입력해주세요.\n" + ": ");
//        performance = scanner.nextLine();
//
//        while (true) {
//            try {
//                System.out.print("구매할 재고의 가격을 입력해주세요.\n" + ": ");
//                price = scanner.nextInt();
//                break;
//            } catch (InputMismatchException exception) {
//                scanner.nextLine();
//                System.out.println("숫자로 입력해주세요.");
//                System.out.print("입력 : ");
//                price = scanner.nextInt();
//                break;
//            }
//        }
//
//        System.out.println();
//
//        stock.allSet(productName, performance, price);
//
//        this.stocks.add(stock);
//    }
//
//    public void createDesktop(List<Parts> stocks) {
//        int number;
//
//        CPU cpu;
//        RAM ram;
//        GraphicCard graphicCard;
//
//        while (true) {
//            try {
//                if (CPUList.size() > 0 && RAMList.size() > 0 && GraphicCardList.size() > 0) {
//                    System.out.print("본체에 넣을 CPU의 번호를 입력해주세요 : ");
//                    number = scanner.nextInt();
//                    cpu = (CPU) CPUList.get(number - 1);
//
//                    System.out.print("본체에 넣을 RAM의 번호를 입력해주세요 : ");
//                    number = scanner.nextInt();
//                    ram = (RAM) RAMList.get(number - 1);
//
//                    System.out.print("본체에 넣을 Graphic Card의 번호를 입력해주세요 : ");
//                    number = scanner.nextInt();
//                    graphicCard = (GraphicCard) GraphicCardList.get(number - 1);
//
//                    Desktop desktop = new Desktop(cpu, ram, graphicCard,
//                            cpu.getPrice() + ram.getPrice() + graphicCard.getPrice());
//
//                    System.out.println();
//
//                    stocks.stream()
//                            .forEach(s -> {
//                                if (s == desktop.getCpu()) {
//                                    removeCPU = (CPU) s;
//                                } else if (s == desktop.getGraphicCard()) {
//                                    removeGraphic = (GraphicCard) s;
//                                } else if (s == desktop.getRam()) {
//                                    removeRAM = (RAM) s;
//                                }
//                            });
//
//                    stocks.remove(removeCPU);
//                    stocks.remove(removeGraphic);
//                    stocks.remove(removeRAM);
//
//                    this.desktops.add(desktop);
//                    return;
//                } else {
//                    if (CPUList.size() < 1) System.out.println("CPU_NUM 재고가 부족합니다.\n");
//                    else if (RAMList.size() < 1) System.out.println("RAM_NUM 재고가 부족합니다.\n");
//                    else System.out.println("Graphic Card 재고가 부족합니다.\n");
//                    return;
//                }
//            } catch (IndexOutOfBoundsException exception) {
//                System.out.println("존재하지 않는 재고 입니다.");
//            }
//        }
//
//    }
//
//    public boolean inquiryDesktops(List<Desktop> desktops) {
//        System.out.println();
//        number = 1;
//        if (desktops.size() > 0) {
//            desktops.stream()
//                    .forEach(desktop -> {
//                        System.out.println("데스크톱" + number++ + ".\n" +
//                                "CPU_NUM\n" + "제품명 : " + desktop.getCpu().getProductName() + "\n" +
//                                "성능 : " + desktop.getCpu().getPerformance() + "\n\n" +
//                                "Graphic Card\n" + "제품명 : " + desktop.getGraphicCard().getProductName() + "\n" +
//                                "성능 : " + desktop.getGraphicCard().getPerformance() + "\n\n" +
//                                "RAM_NUM\n" + "제품명 : " + desktop.getRam().getProductName() + "\n" +
//                                "성능 : " + desktop.getRam().getPerformance() + "\n\n" +
//                                "Price : " + desktop.getPrice() + "\n\n");
//                    });
//            return true;
//        } else {
//            System.out.println("제작된 본체가 없습니다.\n");
//            return false;
//        }
//    }
//
//    public void saleDesktop(List<Desktop> desktops) {
//        System.out.println();
//        boolean CheckDesktopCount = inquiryDesktops(desktops);
//
//        if (CheckDesktopCount) {
//            while (true) {
//                try {
//                    System.out.print("판매하실 Desktop의 번호를 입력해주세요 : ");
//                    int index = scanner.nextInt();
//                    desktops.remove(index - 1);
//                    System.out.println("\n판매가 완료되었습니다.\n");
//                    return;
//                } catch (IndexOutOfBoundsException exception) {
//                    System.out.println("숫자를 입력해주세요.");
//                }
//            }
//        }
//    }
//
//
//    public static void main(String[] args) {
//        StockManagement stockManagement = new StockManagement();
//        Parts stock;
//
//        while (true) {
//            int selectMenu = stockManagement.mainMenu();
//            switch (selectMenu) {
//                case 1:
//                    stockManagement.inquiryStock(stockManagement.stocks);
//                    break;
//                case 2:
//                    stock = stockManagement.purchaseStock();
//                    stockManagement.addStock(stock);
//                    break;
//                case 3:
//                    stockManagement.createDesktop(stockManagement.stocks);
//                    break;
//                case 4:
//                    stockManagement.inquiryDesktops(stockManagement.desktops);
//                    break;
//                case 5:
//                    stockManagement.saleDesktop(stockManagement.desktops);
//                    break;
//                case 6:
//                    return;
//            }
//        }
//    }
//}