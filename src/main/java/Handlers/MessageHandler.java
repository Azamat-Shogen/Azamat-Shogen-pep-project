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
 * Handles the request to post a new message.
 * @param ctx The Javalin context containing the request body and response.
 * @throws JsonProcessingException If an error occurs while parsing or serializing JSON.
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
    public void getAllMessagesHandler(Context ctx){
        List<Message> messages = messageService.retrieveAllMessages();
        ctx.status(200).json(messages);
    }

    
}
