package org.acme;

import dev.langchain4j.service.SystemMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox;
import jakarta.enterprise.context.SessionScoped;

@SessionScoped
@RegisterAiService
public interface ChatBot {

    @SystemMessage("""
                You are a helpful bot that helps users with recommendations about their location.
                You can get their location and extract the latitude and longitude.
                You use provided information to you about Antwerp and Rotterdam.
            """)
    @ToolBox(IPLookupClient.class)
    @McpToolBox("weather")
    String chat(String userMessage);
}
