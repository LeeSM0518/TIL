package check_problem;

class Parent1 {
    public String nation;

    public Parent1() {
        this("대한민국");
        System.out.println("Parent() call");
    }

    public Parent1(String nation) {
        this.nation = nation;
        System.out.println("Parent(String nation) call");
    }
}

class Child1 extends Parent1 {
    private String name;

    public Child1() {
        this("홍길동");
        System.out.println("Child() call");
    }

    public Child1(String name) {
        this.name = name;
        System.out.println("Child(String name) call");
    }
}

class ChildExample {
    public static void main(String[] args) {
        Child1 child1 = new Child1();
    }
}