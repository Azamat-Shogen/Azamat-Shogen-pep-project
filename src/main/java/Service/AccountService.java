package Service;

import DAO.AccountDAO;
import Model.Account;

/**
 * Handles account-related business logic.
 */
public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    /**
     * Registers a new user.
     * @param account The account to register.
     * @return The registered account or {@code null} if failed.
     */
    public Account registerUser(Account account){
        return accountDAO.registerUser(account);
    }

    /**
     * Logs in a user.
     * @param account The account to log in.
     * @return The logged-in account or {@code null} if invalid.
     */
    public Account loginUser(Account account){
        return accountDAO.loginUser(account);
    }
}
