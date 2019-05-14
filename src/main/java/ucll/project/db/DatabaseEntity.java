package ucll.project.db;

public class DatabaseEntity<K> {

    protected Integer id;

    public DatabaseEntity() { }

    public DatabaseEntity(int id) {
        setId(id);
    }

    public Integer getId() {return id;}
    public void setId(Integer id) { this.id = id;}

}
