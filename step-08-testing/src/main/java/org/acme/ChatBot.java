package org.acme;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import dev.langchain4j.service.guardrail.OutputGuardrails;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox;
import jakarta.enterprise.context.SessionScoped;
import org.acme.guardrails.AllowedLocationsGuardrail;
import org.acme.guardrails.MaxLength;

@SessionScoped
@RegisterAiService
public interface ChatBot {

    @SystemMessage("""
                You are a helpful bot that helps users with recommendations about their location.
                You can get their location and extract the latitude and longitude.
                You use provided information to you about Antwerp and Rotterdam.
            """)
    @InputGuardrails({MaxLength.class})
    @OutputGuardrails({ AllowedLocationsGuardrail.class })
    @ToolBox(IPLookupClient.class)
    @McpToolBox("weather")
    String chat(String userMessage);
}
