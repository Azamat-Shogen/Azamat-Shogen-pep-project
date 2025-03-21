package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.Message;
import Util.ConnectionUtil;
import Validations.MessageValidations;

public class MessageDAO {

    private final MessageValidations messageValidations = new MessageValidations();

    public Message postNewMessage(Message message){

        Connection connection = ConnectionUtil.getConnection();

        try {
            // Run validation checks
            if (!messageValidations.postNewMessageValidations(message, connection)) return null;

            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES(?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0){
                ResultSet pkResultSet = preparedStatement.getGeneratedKeys();
                if (pkResultSet.next()){
                    int generated_message_id = (int) pkResultSet.getLong(1);
                    return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }
}
