package ucll.project.db;

import java.util.List;

public interface IDatabase<T extends DatabaseEntity, K> {

    boolean contains(int primaryKey);

    boolean contains(T record);

    T get(int primaryKey);

    T getByUniqueField(String field, K uniqueField);

    List<T> getAll();

    int add(T record);

    void update(T record);

    void delete(int primaryKey);

    int getCount();

    int highestId();

    void drop();

    void close();
}
