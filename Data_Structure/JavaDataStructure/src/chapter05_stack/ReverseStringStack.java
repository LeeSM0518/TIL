package chapter05_stack;

public class ReverseStringStack extends ArrayStack {

    private String originString;
    private StringBuilder reversedString = new StringBuilder();

    public ReverseStringStack(String originString) {
        super(originString.length());
        this.originString = originString;
    }

    public void reverse() {
        for (int i = 0; i < originString.length(); i++) {
            push(String.valueOf(originString.charAt(i)));
        }

        for (int i = 0; i < originString.length(); i++) {
            reversedString.append((String) pop().getData());
        }
    }

    public StringBuilder getReversedString() {
        return reversedString;
    }

    public static void main(String[] args) {
        ReverseStringStack reverseStringStack = new ReverseStringStack("안녕하세요");
        reverseStringStack.reverse();
        System.out.println(reverseStringStack.getReversedString());
    }
}