package ucll.project.db.sql;

import ucll.project.domain.Grade;
import ucll.project.domain.Answer;
import ucll.project.domain.Answer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AnswerSqlDatabase extends SQLDatabase<Answer> {

    public AnswerSqlDatabase(Properties properties) throws SQLException {
        super(properties, Answer.class, "answer");
    }

    @Override
    public Answer getByUniqueField(String field, Integer uniqueField) {
        return null;
    }

    public Answer get(int primaryKey) {
        try {
            String sql = "SELECT * FROM " + getCorrectTableName() + " WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, primaryKey);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int id = result.getInt(1);
                String value = result.getString(2);
                int point = result.getInt(3);
                int questionId = result.getInt(4);

                return new Answer(id, value, point, questionId);
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Answer> getAll() {
        List<Answer> tents = new ArrayList<>();
        try {
            String sql = "SELECT * FROM " + getCorrectTableName();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                int id = result.getInt(1);
                String value = result.getString(2);
                int point = result.getInt(3);
                int questionId = result.getInt(4);

                tents.add(new Answer(id, value, point, questionId));
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tents;
    }

    @Override
    public int add(Answer record) {
        try {
            String sql = "INSERT INTO " + getCorrectTableName() + " VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            int id = this.highestId()+1;
            statement.setObject(1, id);
            statement.setObject(2, record.getValue());
            statement.setObject(3, record.getPoint());
            statement.setObject(4, record.getQuestionId());
            statement.executeUpdate();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void update(Answer record) {

    }
}
