package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.Account;
import Util.ConnectionUtil;
import Validations.AccountValidations;

public class AccountDAO {
    
    AccountValidations validations;
    /**
     * Registers a new account
     * @param account
     * @return new account
     */
    public Account registerUser(Account account){

        validations = new AccountValidations();
        Connection connection = ConnectionUtil.getConnection();

        try {
            // Run validation checks
            if (!validations.registerValidations(account, connection)){
                return null;
            }

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
        }
        return null;
        } 
}