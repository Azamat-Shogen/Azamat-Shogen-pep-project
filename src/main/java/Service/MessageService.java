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


    public Message postNewMessage(Message message){
        return messageDAO.postNewMessage(message);
    }

    public List<Message> retrieveAllMessages(){
        return messageDAO.retrieveAllMessages();
    }

    public Message retrieveMessageById(int message_id){
        return messageDAO.retrieveMessageById(message_id);
    }
    
}
