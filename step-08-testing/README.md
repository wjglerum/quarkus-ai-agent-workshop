# Step 8 - Testing LLM Application Guardrails

In this step you’ll unit test your input/output guardrails using the `langchain4j-test` AssertJ helpers.
These tests run in milliseconds and don’t call an LLM, which is perfect for CI.

## 0) Add Dependencies

First, we need to add the following dependencies to our `pom.xml`:

```xml
<!-- Testing -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-test</artifactId>
    <scope>test</scope>
</dependency>
```

## 1) What we’re testing

- Input guardrail: `MaxLength` - rejects inputs longer than a configured character budget (code-point safe).
- Output guardrail: `AllowedLocationsGuardrail` - the bot’s answer must mention at least one approved location.

Both are tiny and deterministic, so we can test them like any other function.

## 2) Output guardrail test (example)

Files under test

- `AllowedLocationsGuardrail.java`
- Config key: `guardrails.locations.allowed` (comma-separated allowed names)

Test file — `src/test/java/org/acme/guardrails/AllowedLocationsGuardrailTest.java`

> [!NOTE]
> Tip: we set `rail.allowedCsv` directly to keep tests self-contained.
> In the app, you normally put this in `application.properties`.

## 3) Input guardrail test (example)

Files under test

- `MaxLength.java`
- Config key: `guardrails.max-input-chars` (default 1000)

Test file — `src/test/java/org/acme/guardrails/MaxLengthTest.java`

> [!NOTE]
> If your failure message differs, either update the guardrail message or loosen the assertion (e.g., .contains(...)).

## 4) GuardrailAssertions: handy methods

Static-import this in tests:

```java
import static dev.langchain4j.test.guardrail.GuardrailAssertions.assertThat;
```

Common checks you can use:

- `assertThat(result).isSuccessful();`
- `assertThat(result).isReprompt();`
- `assertThat(result).hasResult(Result.FATAL);`
- `assertThat(result).hasSingleFailureWithMessage("...");`
- `assertThat(result).hasSingleFailureWithMessageAndReprompt("msg", "instruction");`
- `assertThat(result).hasRepromptInstructionContaining("...");`
-
`assertThat(result).assertSingleFailureSatisfies(f -> { ... assertions on f.message(), f.repromptInstruction() ... });`

These work for both **input** and **output** guardrail results.

## 5) Patterns for your own tests

- Arrange: instantiate your guardrail and override config fields (e.g., `rail.allowedCsv = "..."`).
- Act: call `validate(...)` with a `UserMessage` (input rails) or `AiMessage` (output rails).
- Assert: use `GuardrailAssertions` to check outcome (SUCCESS/REPROMPT/FATAL), message, and reprompt instruction.

## Bonus

If you want to explore testing in more depth, check out Quarkus
LangChain4j [Testing Scorer Strategy Semantic Similarity](https://docs.quarkiverse.io/quarkus-langchain4j/dev/testing.html).
Semantic similarity is a way to compare the meaning of two pieces of text, rather than just their exact words.
Applications include:

- Evaluating the quality of generated text by comparing it to a reference text.
- Clustering similar documents or responses.
- Detecting paraphrases or similar questions.
- Improving search results by finding semantically similar documents.
- Many more...

## Next step

Now you are ready to move to the next [step](./../step-bonus-01-observability/README.md).