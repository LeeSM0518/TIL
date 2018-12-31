package check_problem;

class Parent{
    public String name;

    Parent(String name) {
        this.name = name;
    }
}

class Child extends Parent {
    private  int studentNo;

    Child(String name, int studentNo) {
        super(name);
        this.name = name;
        this.studentNo = studentNo;
    }
}