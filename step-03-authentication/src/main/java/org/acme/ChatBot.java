package org.acme;

import dev.langchain4j.service.SystemMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.SessionScoped;

@SessionScoped
@RegisterAiService
public interface ChatBot {

    @SystemMessage("You are an assistant helping with users.")
    String chat(String userMessage);
}
