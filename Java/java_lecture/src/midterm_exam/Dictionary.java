package midterm_exam;

import java.util.*;

interface Dic {
    public String get();
    public void set(String x, String y);
    public void print();
}

class KorKor implements Dic {
    String word;
    String meaning;

    KorKor(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    @Override
    public String get() {
        return this.meaning;
    }

    @Override
    public void set(String x, String y) {
        this.word = x;
        this.meaning = y;
    }

    @Override
    public void print() {
        System.out.println("국어사전");
        System.out.println(this.word + ": " + this.meaning);
    }
}

class KorEng implements Dic {
    String word;
    String meaning;

    KorEng(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    @Override
    public String get() {
        return this.meaning;
    }

    @Override
    public void set(String x, String y) {
        this.word = x;
        this.meaning = y;
    }

    @Override
    public void print() {
        System.out.println("영어사전");
        System.out.println(this.word + ": " + this.meaning);
    }
}

public class Dictionary {
    public static void main(String[] args) {
        KorKor k = new KorKor("오른쪽", "북쪽을 바라보고 동쪽과 같은 쪽");

        k.print();
        k.set("왼쪽", "북쪽을 바라보고 서쪽과 같은 쪽");
        System.out.println("왼쪽: " + k.get());
        KorEng e = new KorEng("하나", "one");
        e.print();
        e.set("둘", "two");
        System.out.println("둘: " + e.get());
    }
}
