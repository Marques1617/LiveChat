package tech.buildrun.livechatms.controller;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import tech.buildrun.livechatms.domain.ChatInput;
import tech.buildrun.livechatms.domain.ChatOutput;

@Controller
public class LiveChatController {

    @MessageMapping("/new-message")
    @SendTo("/topics/live-chat") //broadcast the message to all subscribers of the topic /topics/live-chat
    public ChatOutput newMessage(ChatInput input,Principal principal) {

        if (input == null || input.message() == null || principal == null) {
            return new ChatOutput("system", "Invalid message");
        }
        String safeMessage = HtmlUtils.htmlEscape(input.message());
        return new ChatOutput(principal.getName(), safeMessage);
    }
    
}