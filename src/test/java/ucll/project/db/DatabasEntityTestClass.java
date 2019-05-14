package ucll.project.db;

public class DatabasEntityTestClass extends DatabaseEntity<String> {

    private String name;
    private String attribute;

    public DatabasEntityTestClass(String name) {
        setName(name);
    }

    public String getName() {
        return this.name;
    }
    private void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return this.attribute;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
