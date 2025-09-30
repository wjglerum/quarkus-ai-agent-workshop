package org.acme.guardrails;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.guardrail.OutputGuardrail;
import dev.langchain4j.guardrail.OutputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class AllowedLocationsGuardrail implements OutputGuardrail {

    @ConfigProperty(name = "guardrails.locations.allowed") // check application.properties for the list
    String allowedCsv;

    @Override
    public OutputGuardrailResult validate(AiMessage ai) {
        String text = ai.text();
        if (text == null || text.isBlank()) {
            return reprompt("Empty answer", null,
                    "Mention at least one approved location from the list.");
        }

        String lower = text.toLowerCase();
        for (String raw : allowedCsv.split(",")) {
            String name = raw.trim();
            if (!name.isEmpty() && lower.contains(name.toLowerCase())) {
                return success();
            }
        }

        return reprompt("Answer must include an approved location", null,
                "Please include at least one of: " + allowedCsv + ".");
    }
}
