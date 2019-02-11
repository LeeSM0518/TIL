package generic_method;

public class Util2 {
    // 제네릭 메소드
    // p1 과 p2 객체의 Key 값과 Value 값을 비교
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        boolean keyCompare = p1.getKey().equals(p2.getKey());
        boolean valueCompare = p1.getValue().equals(p2.getValue());
        return keyCompare && valueCompare;
    }
}