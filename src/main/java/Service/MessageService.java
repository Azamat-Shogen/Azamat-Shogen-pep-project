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

    public Message retrieveMessageByMessageId(int message_id){
        return messageDAO.retrieveMessageByMessageId(message_id);
    }

    public List<Message> retrieveAllMessagesByUserId(int posted_by){
        return messageDAO.retrieveMessageByUserId(posted_by);
    }

    public Message deleteMessageByMessageId(int message_id){
        return messageDAO.deleteMessageByMessageId(message_id);
    }

    public Message updateMessageByMessageId(String messageText, int message_id){
        return messageDAO.updateMessageByMessageId(messageText, message_id);
    }
    
}
