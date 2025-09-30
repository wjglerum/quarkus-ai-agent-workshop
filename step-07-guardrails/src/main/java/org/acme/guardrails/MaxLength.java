package org.acme.guardrails;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.guardrail.InputGuardrail;
import dev.langchain4j.guardrail.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Fails if the user input exceeds the configured character budget.
 * Uses Unicode code point count (so emoji / surrogate pairs are counted correctly).
 */
@ApplicationScoped
public class MaxLength implements InputGuardrail {

    @ConfigProperty(name = "guardrails.max-input-chars", defaultValue = "1000")
    int maxChars;

    @Override
    public InputGuardrailResult validate(UserMessage um) {
        String text = um.singleText();
        if (text == null) {
            return success(); // Nothing to validate
        }
        int len = text.codePointCount(0, text.length());
        if (len > maxChars) {
            // a fatal failure, the next InputGuardrail won't be evaluated
            return fatal("Input too long (" + len + " > " + maxChars + " characters)");
        }
        return success();
    }
}
