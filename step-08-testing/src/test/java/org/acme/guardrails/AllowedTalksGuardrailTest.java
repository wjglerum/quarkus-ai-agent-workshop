package org.acme.guardrails;

import org.junit.jupiter.api.Test;

import static dev.langchain4j.data.message.AiMessage.*;
import static dev.langchain4j.test.guardrail.GuardrailAssertions.assertThat;

class AllowedTalksGuardrailTest {

    @Test
    void passesWhenContainsAllowedSpeaker() {
        var rail = new AllowedTalksGuardrail();
        rail.allowedCsv = "Venkat Subramaniam,Josh Long,Willem Jan Glerum";
        var res = rail.validate(from("Don't miss the keynote by Venkat Subramaniam at 10:00 in the Dome!"));
        assertThat(res).isSuccessful();
    }

    @Test
    void passesWhenContainsAllowedVenue() {
        var rail = new AllowedTalksGuardrail();
        rail.allowedCsv = "Taron,FLY,Phantasialand";
        var res = rail.validate(from("After the sessions, you should definitely ride Taron!"));
        assertThat(res).isSuccessful();
    }

    @Test
    void repromptsWhenNoApprovedTalkOrSpeakerMentioned() {
        var rail = new AllowedTalksGuardrail();
        rail.allowedCsv = "Venkat Subramaniam,Josh Long";

        var res = rail.validate(from("There are many great talks happening today at the conference."));

        assertThat(res)
                .hasSingleFailureWithMessageAndReprompt(
                        "Answer must reference an approved talk or speaker",
                        "Please include at least one of these approved talks or speakers: Venkat Subramaniam,Josh Long.");
    }
}
