package Validations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Message;

public class MessageValidations {
    
    public boolean postNewMessageValidations(Message message, Connection connection){
        if (message == null) return false;

        if (message.getMessage_text().isBlank()) return false;

        if (message.getMessage_text().length() > 255) return false;

        // Validate existing account_id
        try {
            String sql = "SELECT * FROM account WHERE account.account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message.getPosted_by());

            ResultSet rs = preparedStatement.executeQuery();

            if(!rs.next()) return false;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean updateMessageValidations(String messageText, int message_id, Connection connection){
        if (messageText == null || messageText.isBlank()) return false;
        if (messageText.length() > 255) return false;

        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message_id);
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) return false;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
