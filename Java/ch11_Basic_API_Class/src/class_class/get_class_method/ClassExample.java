package class_class.get_class_method;

import object_class.clone_method.deep_clone.Car;

public class ClassExample {
    public static void main(String[] args) {
        // Car 객체 생성
        Car car = new Car("1");

        // Car 객체의 정보를 clazz1에 저장
        Class clazz1 = car.getClass();

        // Car 클래스 전체 이름(패키지가 포함된 이름)을 저장
        String clazzName = clazz1.getName();

        // 클래스의 전체이름과 간단한 이름 그리고 패키지 이름 출력
        System.out.println(clazz1.getName());
        System.out.println(clazz1.getSimpleName());
        System.out.println(clazz1.getPackage().getName());
        System.out.println();

        try {
            // 클래스의 전체이름과 간단한 이름 그리고 패키지 이름 출력
            Class clazz2 = Class.forName(clazzName);
            System.out.println(clazz2.getName());
            System.out.println(clazz2.getSimpleName());
            System.out.println(clazz2.getPackage().getName());
        } catch (ClassNotFoundException e) {    // 예외 처리
            e.printStackTrace();
        }
    }
}
