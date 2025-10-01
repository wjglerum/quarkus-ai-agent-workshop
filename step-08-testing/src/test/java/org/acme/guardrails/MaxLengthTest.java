package org.acme.guardrails;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.guardrail.GuardrailResult;
import dev.langchain4j.guardrail.InputGuardrailResult;
import org.junit.jupiter.api.Test;

import static dev.langchain4j.test.guardrail.GuardrailAssertions.assertThat;

class MaxLengthTest {

    @Test
    void underLimit_isSuccessful() {
        MaxLength rail = new MaxLength();
        rail.maxChars = 1000;

        InputGuardrailResult res = rail.validate(UserMessage.from("a".repeat(1000)));
        assertThat(res).isSuccessful();
    }

    @Test
    void overLimit_isFatal_withClearMessage() {
        MaxLength rail = new MaxLength();
        rail.maxChars = 1000;

        InputGuardrailResult res = rail.validate(UserMessage.from("a".repeat(1001)));
        assertThat(res)
                .hasResult(GuardrailResult.Result.FATAL)
                .assertSingleFailureSatisfies(f ->
                        org.assertj.core.api.Assertions.assertThat(f.message())
                                .contains("Input too long")
                                .contains("1001")
                                .contains("1000")
                );
    }

    @Test
    void emoji_areCountedByCodePoint() {
        MaxLength rail = new MaxLength();
        rail.maxChars = 3;

        // "A🙂B" == 3 code points; this is OK
        assertThat(rail.validate(UserMessage.from("A🙂B"))).isSuccessful();

        // "A🙂BC" == 4 code points; this is incorrect
        assertThat(rail.validate(UserMessage.from("A🙂BC")))
                .hasResult(GuardrailResult.Result.FATAL)
                .assertSingleFailureSatisfies(f ->
                        org.assertj.core.api.Assertions.assertThat(f.message())
                                .contains("Input too long")
                                .contains("4")
                                .contains("3")
                );

    }
}
