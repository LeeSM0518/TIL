package objects_class.equals_and_deep_equals_method;

import java.util.Arrays;
import java.util.Objects;

public class EqualsAndDeepEqualsExample {
    public static void main(String[] args) {
        Integer o1 = 1000;
        Integer o2 = 1000;

        // 둘 다 값이 1000 이므로  true
        System.out.println(Objects.equals(o1, o2));

        // 한 쪽이 null 이므로 false
        System.out.println(Objects.equals(o1, null));
        System.out.println(Objects.equals(null, o2));

        // 둘 다 같은 null 이므로 true
        System.out.println(Objects.equals(null, null));

        // 둘 다 값이 1000 이므로 true
        System.out.println(Objects.deepEquals(o1, o2) + "\n");

        Integer[] arr1 = {1, 2};
        Integer[] arr2 = {1, 2};
        System.out.println("arr1 address : " + arr1.hashCode());
        System.out.println("arr2 address : " + arr2.hashCode());

        // 두 배열 객체의 주소가 다르기 때문에 false
        System.out.println(Objects.equals(arr1, arr2));

        // 두 배열 객체의 값이 같기 때문에 true
        System.out.println(Objects.deepEquals(arr1, arr2));

        // 두 배열 객체의 값이 같기 때문에 true
        System.out.println(Arrays.deepEquals(arr1, arr2));

        // 한 쪽이 null 이므로 false
        System.out.println(Objects.deepEquals(null, arr2));
        System.out.println(Objects.deepEquals(arr1, null));

        // 둘 다 null 이므로 true
        System.out.println(Objects.deepEquals(null, null));
    }
}
