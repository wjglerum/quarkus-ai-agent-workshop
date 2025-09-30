package org.acme.guardrails;

import org.junit.jupiter.api.Test;

import static dev.langchain4j.data.message.AiMessage.*;
import static dev.langchain4j.test.guardrail.GuardrailAssertions.assertThat;

class AllowedLocationsGuardrailTest {

    @Test
    void passesWhenContainsAllowed() {
        var rail = new AllowedLocationsGuardrail();
        rail.allowedCsv = "Markthal,Fenix Food Factory";
        var res = rail.validate(from("Grab lunch at Markthal."));
        assertThat(res).isSuccessful();
    }

    @Test
    void repromptsWhenNoAllowedMentioned() {
        var rail = new AllowedLocationsGuardrail();
        rail.allowedCsv = "Markthal,Fenix Food Factory";

        var res = rail.validate(from("Plenty of options downtown."));

        assertThat(res)
                .hasSingleFailureWithMessageAndReprompt(
                        "Answer must include an approved location",
                        "Please include at least one of: Markthal,Fenix Food Factory.");
    }
}
