package class_class.reflection_method;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        // Car 객체의 정보를 불러와서 Class 타입의 clazz 에 저장한다.
        Class clazz = Class.forName("object_class.clone_method.deep_clone.Car");

        // 클래스 이름을 호출한다.
        System.out.println("[클래스 이름]");
        System.out.println(clazz.getName());
        System.out.println();

        // 클래스의 생성자 정보를 호출한다.
        System.out.println("[생성자 정보]");
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for(Constructor constructor : constructors) {
            System.out.print(constructor.getName() + "(");
            Class[] parameters = constructor.getParameterTypes();
            printParameters(parameters);
            System.out.println(")");
        }
        System.out.println();

        // 클래스의 필드 정보를 호출한다.
        System.out.println("[필드 정보]");
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            System.out.println(field.getType().getSimpleName() + " " +
                    field.getName());
        }
        System.out.println();

        // 클래스의 메소드 정보를 호출한다.
        System.out.println("[메소드 정보]");
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods) {
            System.out.print(method.getName() + "(");
            Class[] parameters = method.getParameterTypes();
            printParameters(parameters);
            System.out.println(")");
        }

    }

    // 메소드의 파라미터들을 호출해주는 메소드
    private static void printParameters(Class[] parameters) {
        for(int i=0; i<parameters.length; i++) {
            System.out.print(parameters[i].getName());
            if(i < (parameters.length -1)) {
                System.out.println(",");
            }
        }
    }
}