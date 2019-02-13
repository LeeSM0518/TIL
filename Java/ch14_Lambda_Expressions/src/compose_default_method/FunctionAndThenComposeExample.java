package compose_default_method;

import andthen_default_method.Address;
import andthen_default_method.Member;

import java.util.function.Function;

public class FunctionAndThenComposeExample {
    public static void main(String[] args) {
        // Function 함수적 인터페이스 선언
        Function<Member, Address> functionA;
        Function<Address, String> functionB;
        Function<Member, String> functionAB;
        String city;

        // 주소를 가져오는 람다식 선언
        functionA = (m) -> m.getAddress();
        // 도시를 가져오는 람다식 선언
        functionB = (a) -> a.getCity();

        // A와 B를 순차적으로 연결(A 먼저 실행)
        functionAB = functionA.andThen(functionB);
        city = functionAB.apply(
                new Member("홍길동", "hong", new Address(
                        "한국", "서울"
                ))
        );
        System.out.println("거주 도시: " + city);

        // A와 B를 순차적으로 연결(B 먼저 실행)
        functionAB = functionB.compose(functionA);
        city = functionAB.apply(
                new Member("홍길동", "hong", new Address(
                        "한국", "서울"
                ))
        );
        System.out.println("거주 도시: " + city);
    }
}
