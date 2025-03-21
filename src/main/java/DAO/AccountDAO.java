package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.Account;
import Util.ConnectionUtil;
import Validations.AccountValidations;


/**
 * DAO class for managing account-related database operations.
 */
public class AccountDAO {
    
    private final AccountValidations validations = new AccountValidations();
    
   /**
     * Registers a new account in the database.
     * @param account The account to register.
     * @return The created account with ID or {@code null} if registration fails.
     */
    public Account registerUser(Account account){

        Connection connection = ConnectionUtil.getConnection();

        try {
            // Run validation checks
            if (!validations.registerValidations(account, connection)) return null;


            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            int affectedRows = preparedStatement.executeUpdate();
            
            if (affectedRows > 0){
                ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
                if(pkeyResultSet.next()){
                    int generated_account_id = (int) pkeyResultSet.getLong(1);
                    return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        }
   
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
        } 

    /**
     * Authenticates a user by verifying their credentials.
     * @param account The account to authenticate.
     * @return The authenticated account or {@code null} if login fails.
     */
    public Account loginUser(Account account){

        if (!validations.loginValidations(account)) return null;

        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM account WHERE account.username = ? AND account.password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Account loggedInUser = new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
                return loggedInUser;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}