package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

/**
 * Handles message-related business logic.
 */
public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    /**
 * Posts a new message to the database.
 *
 * @param message   The message object containing the message text and timestamp.
 * @param posted_by The ID of the user posting the message.
 * @return          The posted Message object with the generated ID, or null if posting fails.
 */
    public Message postNewMessage(Message message){
        return messageDAO.postNewMessage(message);
    }

    public List<Message> retrieveAllMessages(){
        return messageDAO.retrieveAllMessages();
    }
}
