package whildcard_type;

public class HighStudent extends Student{
    private String name;

    public HighStudent(String name) {
        super(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}