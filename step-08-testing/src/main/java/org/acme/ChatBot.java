package org.acme;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import dev.langchain4j.service.guardrail.OutputGuardrails;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox;
import jakarta.enterprise.context.SessionScoped;
import org.acme.guardrails.AllowedTalksGuardrail;
import org.acme.guardrails.MaxLength;

@SessionScoped
@RegisterAiService
public interface ChatBot {

    @SystemMessage("""
                You are the JavaLand 2026 conference buddy - a helpful assistant for attendees at JavaLand,
                held at Phantasialand theme park in Brühl, Germany (March 9-12, 2026).
                You help with talk recommendations, schedule info, speaker details, and venue tips.
                You can get the user's location and extract the latitude and longitude.
                You use provided information about the JavaLand conference, its talks, speakers, and venue.
                When recommending talks, always mention the room and time slot.
                If the user is in CircusCelebration, remind them they picked the best room at JavaLand!
            """)
    @InputGuardrails({MaxLength.class})
    @OutputGuardrails({AllowedTalksGuardrail.class})
    @ToolBox(IPLookupClient.class)
    @McpToolBox("weather")
    @McpToolBox("javaland")
    String chat(String userMessage);
}
