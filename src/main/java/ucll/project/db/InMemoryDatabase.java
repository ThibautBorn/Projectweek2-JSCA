package ucll.project.db;

import java.lang.reflect.Field;
import java.util.*;

public class InMemoryDatabase<T extends DatabaseEntity, K> implements IDatabase<T,K> {



    private Map<Integer, T> records = new HashMap<>();

    @Override
    public boolean contains(int id) {
        return records.containsKey(id);
    }

    @Override
    public boolean contains(T record) {
        return false;
    }

    @Override
    public T get(int id) {
        return records.get(id);
    }

    public T getByUniqueField(String field, K value) {
        for (T record : records.values()) {
            try {
                Field fieldValue = record.getClass().getDeclaredField(field);
                if (fieldValue.equals(value)) {
                    return record;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<T>(records.values());
    }

    @Override
    public int add(T record) {
        if(record == null){
            throw new NullPointerException("The given record is null");
        }

        int id = highestId() + 1;
        record.setId(id);
        records.put(id, record);
        return id;

    }

    @Override
    public void update(T record) {
        if(record == null){
            throw new NullPointerException("The given record is null");
        }
        if(!records.containsKey(record.getId())){
            throw new DatabaseException("The given record does not exist");
        }
        records.put(record.getId(), record);
    }

    @Override
    public void delete(int primaryKey) {
        records.remove(primaryKey);
    }

    @Override
    public int getCount() {
        return records.size();
    }

    public int highestId() {
        try {
            return Collections.max(records.keySet());
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    @Override
    public void drop() {
        records.clear();
    }

    @Override
    public void close() {
        //
    }
}
