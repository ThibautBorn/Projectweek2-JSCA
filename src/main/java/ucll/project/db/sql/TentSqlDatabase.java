package ucll.project.db.sql;

import ucll.project.domain.Tent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TentSqlDatabase extends SQLDatabase<Tent> {

    public TentSqlDatabase(Properties properties) throws SQLException {
        super(properties, Tent.class, "tent");
    }

    @Override
    public boolean contains(Tent record) {
        return false;
    }

    @Override
    public Tent getByUniqueField(String field, Integer uniqueField) {
        return null;
    }

    public Tent get(int primaryKey) {
        try {
            String sql = "SELECT * FROM " + getCorrectTableName() + " WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, primaryKey);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String description = result.getString(3);

                return new Tent(id, name, description);
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tent> getAll() {
        List<Tent> tents = new ArrayList<>();
        try {
            String sql = "SELECT * FROM " + getCorrectTableName();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String description = result.getString(3);

                tents.add(new Tent(id, name, description));
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tents;
    }

    @Override
    public int add(Tent record) {
        try {
            String sql = "INSERT INTO " + getCorrectTableName() + " VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            int id = this.highestId()+1;
            statement.setObject(1, id);
            statement.setObject(2, record.getName());
            statement.setObject(3, record.getDescription());
            statement.executeUpdate();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void update(Tent record) {

    }
}
