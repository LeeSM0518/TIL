package generic_type_and_implement;

// 제네릭 구현 클래스
public class StorageImp1<T> implements Storage<T> {
    private T[] array;

    public StorageImp1(int capacity) {
        // 타입 파라미터로 배열을 생성하려면
        // 아래와 같이 생성해야 한다.
        this.array = (T[]) (new Object[capacity]);
    }

    @Override
    public void add(T item, int index) {
        array[index] = item;
    }

    @Override
    public T get(int index) {
        return array[index];
    }
}