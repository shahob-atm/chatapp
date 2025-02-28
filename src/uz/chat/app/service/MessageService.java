package uz.chat.app.service;

import uz.chat.app.dto.MessageDto;
import uz.chat.app.model.Message;
import uz.chat.app.model.User;
import uz.chat.app.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageService {
    public List<Message> getAllInvoiceMessages(User user) {
        List<Message> messages = MessageRepository.loadMessages();
        ArrayList<Message> foundedMessages = new ArrayList<>();

        for (Message message : messages) {
            if (message.getToEmail().equals(user.getEmail())) {
                foundedMessages.add(message);
            }
        }
        return foundedMessages;
    }

    public boolean sendMessage(MessageDto messageDto) {
        Message message = new Message(
                UUID.randomUUID(),
                messageDto.text(),
                messageDto.localDateTime(),
                messageDto.fromEmail(),
                messageDto.toEmail()
        );

        return MessageRepository.saveMessage(message);
    }
}
