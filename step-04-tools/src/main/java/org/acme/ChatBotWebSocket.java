package org.acme;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import jakarta.inject.Inject;

@Authenticated
@WebSocket(path = "/chat-bot")
public class ChatBotWebSocket {

    @Inject
    SecurityIdentity identity;

    private final ChatBot chatBot;

    public ChatBotWebSocket(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    @OnOpen
    public String onOpen() {
        return "Hi " + identity.getPrincipal().getName() + "! Welcome to your personal Quarkus chat bot. What can I do for you?";
    }

    @OnTextMessage
    public String onTextMessage(String message) {
        return chatBot.chat(message);
    }
}
