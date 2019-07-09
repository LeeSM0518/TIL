package chapter13_hashing;

    import java.util.Scanner;

public class Hashing {

  private String[] hashTable;
  private final int TABLE_SIZE;
  private final int TABLE_SIZE2;

  public Hashing(int size) {
    TABLE_SIZE = size;
    hashTable = new String[TABLE_SIZE];

    int i;
    for (i = size; i >= 0; i-- ) {
      int count = 0;
      for (int j = 1; j <= i; j++) {
        if (i % j == 1) count++;
      }
      if (count == 2) break;
    }

    TABLE_SIZE2 = i;
  }

  private int transform(String str) {
    int sum = 0;

    for (int i = 0; i < str.length(); i++) {
      sum += str.charAt(i);
    }

    return sum;
  }

  private int hashFunction(String str) {
    return transform(str) % TABLE_SIZE;
  }

  public void linearAdd(String item) {
    int hashValue = hashFunction(item);
    int i = hashValue;

    while (hashTable[i] != null) {
      if (item.equals(hashTable[i])) {
        System.out.println("탐색 키가 중복되었습니다.");
        return;
      }

      i = (i + 1) % TABLE_SIZE;

      if (i == hashValue) {
        System.out.println("테이블이 가득찼습니다.");
        return;
      }
    }

    hashTable[i] = item;
  }

  public void linearSearch(String item) {
    int hashValue = hashFunction(item);
    int i = hashValue;

    while (hashTable[i] != null) {
      if (hashTable[i].equals(item)) {
        System.out.println("탐색성공 위치 : " + i);
        return;
      }
      i = (i + 1) % TABLE_SIZE;
      if (i == hashValue) {
        System.out.println("찾는 값이 테이블에 없습니다.");
        return;
      }
    }
    System.out.println("찾는 값이 테이블에 없습니다.");
  }

  public void quadraticAdd(String item) {
    int hashValue = hashFunction(item);
    int i = hashValue;
    int inc = 0;

    while (hashTable[i] != null) {
      if (item.equals(hashTable[i])) {
        System.out.println("탐색 키가 중복되었습니다");
        return;
      }

      i = (hashValue + inc * inc) % TABLE_SIZE;
      inc += 1;

      if (i == hashValue) {
        System.out.println("테이블이 가득찼습니다.");
        return;
      }
    }

    hashTable[i] = item;
  }

  public void quadraticSearch(String item) {
    int hashValue = hashFunction(item);
    int i = hashValue;
    int inc = 0;

    while (hashTable[i] != null) {
      if (item.equals(hashTable[i])) {
        System.out.println("탐색성공 위치 : " + i);
        return;
      }

      i = (hashValue + inc * inc) % TABLE_SIZE;
      inc += 1;

      if (i == hashValue) {
        System.out.println("찾는 값이 테이블에 없습니다.");
        return;
      }
    }
    System.out.println("찾는 값이 테이블에 없습니다.");
  }

  public void doubleAdd(String item) {
    int hashValue = hashFunction(item);
    int i = hashValue;

    while (hashTable[i] != null) {
      if (hashTable[i].equals(item)) {
        System.out.println("탐색 키가 중복되었습니다.");
        return;
      }

      i = (i + TABLE_SIZE2) % TABLE_SIZE;
      if (i == hashValue) {
        System.out.println("테이블이 가득찼습니다.");
        return;
      }
    }

    hashTable[i] = item;
  }

  public void doubleSearch(String item) {
    int hashValue = hashFunction(item);
    int i = hashValue;

    while (hashTable[i] != null) {
      if (hashTable[i].equals(item)) {
        System.out.println("탐색성공 위치 : " + i);
        return;
      }

      i = (i + TABLE_SIZE2) % TABLE_SIZE;
      if (i == hashValue) {
        System.out.println("찾는 값이 테이블에 없습니다.");
        return;
      }
    }
    System.out.println("찾는 값이 테이블에 없습니다.");
  }

  public void print() {
    for (int i = 0; i < TABLE_SIZE; i++) {
      System.out.println("[" + i + "], " + hashTable[i]);
    }
  }

  public static void main(String[] args) {
    Hashing hashing = new Hashing(10);
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("원하는 연산을 입력하세요(0=입력, 1=탐색, 2=출력, 3=종료) = ");
      int choice = scanner.nextInt();

      scanner.nextLine();

      if (choice == 0) {
        System.out.print("추가할 키를 입력하세요 = ");
        String item = scanner.nextLine();
        hashing.doubleAdd(item);
      } else if (choice == 1) {
        System.out.print("검색 키를 입력하세요 = ");
        String item = scanner.nextLine();
        hashing.doubleSearch(item);
      } else if (choice == 2) {
        hashing.print();
      } else {
        break;
      }
      System.out.println();
    }

  }

}