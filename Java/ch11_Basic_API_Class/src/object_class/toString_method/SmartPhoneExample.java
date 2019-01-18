package object_class.toString_method;

public class SmartPhoneExample {
    public static void main(String[] args) {
        SmartPhone myPhone = new SmartPhone("구글", "안드로이드");

        String strObj = myPhone.toString();

        System.out.println(strObj);     // toString 으로 가져온 정보를 출력

        // myPhone 을 출력시켜 myPhone.toString()을 자동 호출 시킨다.
        System.out.println(myPhone);
    }
}