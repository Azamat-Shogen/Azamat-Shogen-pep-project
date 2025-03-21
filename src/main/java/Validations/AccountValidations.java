package Validations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Account;


/**
 * Handles validation logic for account operations.
 */
public class AccountValidations {

     /**
     * Validates registration criteria.
     * @param account The account to validate.
     * @param connection The database connection.
     * @return {@code true} if valid, otherwise {@code false}.
     */
    public boolean registerValidations(Account account, Connection connection){
        // Validate the account is not null
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

     /**
     * Validates login criteria.
     * @param account The account to validate.
     * @return {@code true} if valid, otherwise {@code false}.
     */
    public boolean loginValidations(Account account){
        // Validate the account is not null
        if (account == null) return false;

        // Validate username length and null
        if (account.getUsername() == null || account.getUsername().isBlank()) return false;

        // Validate password length and null
        if (account.getPassword() == null || account.getPassword().length() < 4) return false;

        return true;
    }
}
