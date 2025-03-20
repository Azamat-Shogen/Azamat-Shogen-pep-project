package Validations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Account;

public class AccountValidations {

    public boolean registerValidations(Account account, Connection connection){
        
        if (account == null) return false;

        // Validate username length and null
        if (account.getUsername() == null || account.getUsername().isBlank()) return false;

        // Validate password length and null
        if (account.getPassword() == null || account.getPassword().length() < 4) return false;
        
        // Validate existing username
        try {
            String sql = "SELECT * FROM account WHERE account.username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) return false;

        } catch (Exception e) {
            // Handle exception
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
