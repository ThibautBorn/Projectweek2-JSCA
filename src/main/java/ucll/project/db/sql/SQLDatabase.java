package ucll.project.db.sql;

import ucll.project.db.DatabaseEntity;
import ucll.project.db.IDatabase;
import ucll.project.domain.Question;
import ucll.project.domain.Tent;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public abstract class SQLDatabase<T extends DatabaseEntity> implements IDatabase<T,Integer> {

    private Class<T> tableClass;
    private Properties properties;
    private String tableName;
    String url;

    Connection connection;

    public SQLDatabase(Properties properties, Class<T> tableClass, String tableName) throws SQLException {
        this.tableClass = tableClass;
        this.tableName = tableName;

        this.properties = properties;
        this.url = properties.getProperty("url");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connection = DriverManager.getConnection(url, properties);
    }

    protected String getCorrectTableName() {
        return "\"competenties-VerplichtAanwezig\"." + tableName;
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(int primaryKey) {
        try {
            String sql = "SELECT COUNT(1) FROM " + getCorrectTableName() + " WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, primaryKey);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt(1)==1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean contains(T record) {
        if (record.getId() == null) {
            return false;
        }
        return this.contains(record.getId());
    }

    @Override
    public abstract T getByUniqueField(String field, Integer uniqueField);

    @Override
    public abstract List<T> getAll();
    public abstract int add(T record);
    public abstract void update(T record);

    public void delete(int primaryKey) {
        try {
            String sql = "DELETE FROM " + getCorrectTableName() + " WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, primaryKey);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        try {
            String sql = "SELECT COUNT(*) FROM " + getCorrectTableName();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int highestId() {
        try {
            String sql = "SELECT MAX(id) FROM " + getCorrectTableName();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            result.next();
            Integer id = result.getInt(1);

            if (result.wasNull()) {
                return -1;
            } else {
                return id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void drop() {

        try {
            String sql = "DELETE FROM " + getCorrectTableName();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
