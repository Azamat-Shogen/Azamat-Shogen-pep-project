package Controller;

import Handlers.AccountHandler;
import Handlers.MessageHandler;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

    private final AccountService accountService;
    private final MessageService messageService;
    private final AccountHandler accountHandler;
    private final MessageHandler messageHandler;

    public SocialMediaController(){
         this.accountService = new AccountService();
         this.messageService = new MessageService();
         this.accountHandler = new AccountHandler(accountService);
         this.messageHandler = new MessageHandler(messageService);
    }


    public Javalin startAPI() {

        Javalin app = Javalin.create();

        // Register endpoints with handlers
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", accountHandler::registerHandler);
        app.post("/login", accountHandler::loginHandler);
        app.post("/messages", messageHandler::postNewMessageHandler);
        app.get("/messages", messageHandler::retrieveAllMessagesHandler);
        app.get("/messages/{message_id}", messageHandler::retrieveMessageByMessageIdHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }


}