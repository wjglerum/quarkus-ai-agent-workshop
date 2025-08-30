package org.acme;

import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;

@WebSocket(path = "/chat-bot")
public class ChatBotWebSocket {

    private final ChatBot chatBot;

    public ChatBotWebSocket(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    @OnOpen
    public String onOpen() {
        return "Welcome to your personal Quarkus chat bot. What can I do for you?";
    }

    @OnTextMessage
    public String onTextMessage(String message) {
        return chatBot.chat(message);
    }
}
