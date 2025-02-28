package uz.chat.app.controller;

import uz.chat.app.dto.MessageDto;
import uz.chat.app.model.Message;
import uz.chat.app.model.User;
import uz.chat.app.service.MessageService;

import java.util.List;

public class MessageController {
    private final MessageService messageService = new MessageService();

    public List<Message> getAllInvoiceMessages(User user) {
        return messageService.getAllInvoiceMessages(user);
    }

    public boolean sendMessage(MessageDto messageDto) {
        return messageService.sendMessage(messageDto);
    }
}
