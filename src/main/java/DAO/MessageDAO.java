package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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


 /**
 * Retrieves all messages from the database.
 * @return A list of Message objects.
 */
    public List<Message> retrieveAllMessages(){
        List<Message> messages = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                messages.add(message);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

    /**
    * Retrieves a message by its ID from the database.
    * @param message_id The ID of the message to retrieve.
    * @return The Message object if found, otherwise null.
    */
    public Message retrieveMessageByMessageId(int message_id){
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM message WHERE message.message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                return message;
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * Retrieves messages by user ID.
     * @param posted_by The user's ID.
     * @return A list of messages or an empty list if none are found.
     */
    public List<Message> retrieveMessageByUserId(int posted_by){

        List<Message> messages = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM message WHERE message.posted_by = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, posted_by);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                messages.add(message);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }
}
