package hash_map;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample2 {
    public static void main(String[] args) {
        Map<Student, Integer> map = new HashMap<Student, Integer>();

        // 학번과 이름이 동일한 Student 를 키로 저장
        map.put(new Student(1, "홍길동"), 95);
        map.put(new Student(1, "홍길동"), 95);

        // 저장된 총 Map.Entry 수 얻기
        System.out.println("총 Entry 수: " + map.size());
    }
}
