package seminar_project.parts;

import java.util.Map;



public interface Part {
    void setProductName(String productName);

    void setPerformance(String performance);

    void setPrice(int price);

    String getProductName();

    String getPerformance();

    int getPrice();

    // 이 interface 를 구현한 모든 클래스들이 필요한 메소드이기에 default 메소드로 선언
    default <T extends Part> void createPart(Map<String, String> information, T t) {

        t.setProductName(information.get("name"));
        t.setPerformance(information.get("performance"));
        t.setPrice(new Integer(information.get("price")));

    }

}