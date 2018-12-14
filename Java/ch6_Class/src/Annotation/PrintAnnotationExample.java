package Annotation;

import java.lang.reflect.Method;        // 리플렉션을 사용하기 위함

public class PrintAnnotationExample {
    public static void main(String[] args) {
        // Service 클래스에 선언된 메소드 얻기(리플렉션)
        // 메소드 배열을 생성 후 Service 클래스에 선언된 메소드를 가져와 저장한다.
        Method[] declaredMethods = Service.class.getDeclaredMethods();

        // Method 객체를 하나씩 처리
        for(Method method : declaredMethods){   // 메소드 객체를 하나씩 불러와 method에 저장하여 처리한다.

            // PrintAnnotation이 적용되었는지 확인
            // 지정한 클래스에 method 어노테이션이 적용되었는지 확인
            if(method.isAnnotationPresent(PrintAnnotation.class)){

                // PrintAnnotation 객체 얻기
                // PrintAnnotation 객체에 method 어노테이션을 리턴
                PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class);

                // 메소드 이름 출력
                System.out.println("[" + method.getName() + "] ");

                // 구분선 출력
                for(int i=0; i<printAnnotation.number(); i++){
                    System.out.print(printAnnotation.value());
                }
                System.out.println();
            }

            try{
                // 메소드 호출
                method.invoke(new Service());
            } catch (Exception e){ }
            System.out.println();
        }
    }
}

