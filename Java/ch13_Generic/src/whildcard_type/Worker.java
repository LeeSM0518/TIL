package whildcard_type;

public class Worker extends Person{
    private String name;

    public Worker(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
