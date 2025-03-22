package Handlers;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Message;
import Service.MessageService;
import io.javalin.http.Context;

/**
 * Handles message requests.
 */
public class MessageHandler {
    private final MessageService messageService;

    public MessageHandler(MessageService messageService){
        this.messageService = messageService;
    }

    /**
     * Handles posting a new message.
     * @param ctx The request context.
     * @throws JsonProcessingException If JSON parsing fails.
     */
    public void postNewMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);

        Message postedMessage = messageService.postNewMessage(message);

        if (postedMessage != null){
            ctx.status(200).json(mapper.writeValueAsString(postedMessage));
        } else {
            ctx.status(400);
        }
    }


    /**
     * Retrieves all messages and responds with a JSON list.
     * @param ctx The request context.
     */
    public void retrieveAllMessagesHandler(Context ctx){
        List<Message> messages = messageService.retrieveAllMessages();
        ctx.status(200).json(messages);
    }

    /**
     * Retrieves a message by ID and responds with JSON.
     * @param ctx The request context.
     */
    public void retrieveMessageByMessageIdHandler(Context ctx){
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.retrieveMessageByMessageId(message_id);
        if (message != null){
            ctx.status(200).json(message);
        } else {
            ctx.status(200);
        }
        
    }

    /**
     * Handles requests to retrieve all messages by user ID.
     * @param ctx The request context.
     */
    public void retrieveAllMessagesByUserIdHandler(Context ctx){
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages = messageService.retrieveAllMessagesByUserId(account_id);
        ctx.json(messages);
    }
    
}
