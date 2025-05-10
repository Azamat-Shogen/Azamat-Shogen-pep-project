package Controller;

import Handlers.AccountHandler;
import Handlers.MessageHandler;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * Controller class for the Social Media API.
 * Defines the API endpoints and associates them with their respective handlers.
 * This controller handles user account creation, login, message posting, retrieval,
 * deletion, and updating.
 */
public class SocialMediaController {
    /**
     * Creates and configures a Javalin application instance, defining the API endpoints
     * and mapping them to their corresponding handler methods. This method is crucial
     * for the test suite to interact with the API.
     *
     * @return A configured Javalin application object.
     */

    private final AccountService accountService;
    private final MessageService messageService;
    private final AccountHandler accountHandler;
    private final MessageHandler messageHandler;

    /**
     * Constructs a new SocialMediaController, initializing the service and handler
     * dependencies.
     */
    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
        this.accountHandler = new AccountHandler(accountService);
        this.messageHandler = new MessageHandler(messageService);
    }

    /**
     * Configures the Javalin application by defining API endpoints and associating
     * them with the appropriate handler methods.
     *
     * @return A configured Javalin application object with defined routes.
     */
    public Javalin startAPI() {

        Javalin app = Javalin.create();

        // Register endpoints with handlers
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", accountHandler::registerHandler);
        app.post("/login", accountHandler::loginHandler);
        app.post("/messages", messageHandler::postNewMessageHandler);
        app.get("/messages", messageHandler::retrieveAllMessagesHandler);
        app.get("/messages/{message_id}", messageHandler::retrieveMessageByMessageIdHandler);
        app.get("/accounts/{account_id}/messages", messageHandler::retrieveAllMessagesByUserIdHandler);
        app.delete("/messages/{message_id}", messageHandler::deleteMessageByMessageIdHandler);
        app.patch("/messages/{message_id}", messageHandler::updateMessageByMessageIdHandler);

        return app;
    }

    /**
     * An example handler for the "/example-endpoint".
     * Responds with a simple JSON string.
     *
     * @param context The Javalin Context object, providing access to the HTTP request
     * and allowing control over the HTTP response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }
}