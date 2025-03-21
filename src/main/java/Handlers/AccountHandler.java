package Handlers;

import Service.AccountService;
import Model.Account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;

/**
 * Handles both registration and login requests.
 */
public class AccountHandler {
    private final AccountService accountService;

    public AccountHandler(AccountService accountService){
        this.accountService = accountService;
    }

     /**
     * Handles user registration.
     * @param ctx Javalin context
     * @throws JsonProcessingException
     */
    public void registerHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        
        Account registeredAccount = accountService.registerUser(account);

        if (registeredAccount != null) {
            ctx.status(200).json(mapper.writeValueAsString(registeredAccount));
        } else {
            ctx.status(400);
        }
    }

    /**
     * Handles user login.
     * @param ctx Javalin context
     * @throws JsonProcessingException
     */
    public void loginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);

        Account loggedInUser = accountService.loginUser(account);

        if (loggedInUser != null) {
            ctx.status(200).json(mapper.writeValueAsString(loggedInUser));
        } else {
            ctx.status(401);
        }
    }

}
