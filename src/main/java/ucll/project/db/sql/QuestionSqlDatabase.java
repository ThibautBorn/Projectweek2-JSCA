package ucll.project.db.sql;

import ucll.project.domain.Grade;
import ucll.project.domain.Question;
import ucll.project.domain.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class QuestionSqlDatabase extends SQLDatabase<Question> {

    public QuestionSqlDatabase(Properties properties) throws SQLException {
        super(properties, Question.class, "question");
    }

    @Override
    public Question getByUniqueField(String field, Integer uniqueField) {
        return null;
    }

    public Question get(int primaryKey) {
        try {
            String sql = "SELECT * FROM " + getCorrectTableName() + " WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, primaryKey);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int id = result.getInt(1);
                String value = result.getString(2);
                Grade grade = Grade.valueOf(result.getString(3));
                int tentId = result.getInt(4);

                return new Question(id, value, grade, tentId);
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Question> getAll() {
        System.out.println("x");
        List<Question> tents = new ArrayList<>();
        try {
            String sql = "SELECT * FROM " + getCorrectTableName();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                int id = result.getInt(1);
                String value = result.getString(2);
                Grade grade = Grade.valueOf(result.getString(3));
                int tentId = result.getInt(4);

                tents.add(new Question(id, value, grade, tentId));
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tents;
    }

    @Override
    public int add(Question record) {
        try {
            String sql = "INSERT INTO " + getCorrectTableName() + " VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            int id = this.highestId()+1;
            statement.setObject(1, id);
            statement.setObject(2, record.getValue());
            statement.setString(3, record.getGrade().toString());
            statement.setObject(4, record.getTentId());
            statement.executeUpdate();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void update(Question record) {

    }
}
