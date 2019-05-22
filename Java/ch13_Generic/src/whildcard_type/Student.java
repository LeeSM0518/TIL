package whildcard_type;

public class Student extends Person {
    private String name;

    public Student(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
