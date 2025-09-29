# Step 6 - Guardrails

Guardrails are a set of rules that are applied to your large language model. It can define a set of rules for the input and
output of your model.
For the full docs check out the [documentation](https://docs.quarkiverse.io/quarkus-langchain4j/dev/guardrails.html).

![guardrails](https://docs.quarkiverse.io/quarkus-langchain4j/dev/_images/guardrails.png)
https://docs.quarkiverse.io/quarkus-langchain4j/dev/guardrails.html

## Prompt Injection

It's important to think about potential cases for prompt injection.
Just like with REST & SQL it's important to sanitise user input before feeding it to your model.

You can find more information about prompt injection here:
https://docs.quarkiverse.io/quarkus-langchain4j/dev/security.html#_prompt_injection

## Input Guardrails

Think about the inputs of your model. What are the possible inputs?
What can go wrong? Follow the documentation to set the guardrails.

For example to limit the input to 1000 characters:

```java

@ApplicationScoped
public class MaxLength implements InputGuardrail {

    @Override
    public InputGuardrailResult validate(UserMessage um) {
        String text = um.singleText();
        if (text.length() > 1000) {
            // a fatal failure, the next InputGuardrail won't be evaluated
            return fatal("Input too long, size = " + text.length());
        }
        return success();
    }
}
```

## Output Guardrails

We can filter the outputs of our model before returning them to the user.
You can also use output guardrails to process the output of your model.

For example to process it as JSON and then perform validation on the fields:

```java
@ApplicationScoped
public class JsonGuardrail implements OutputGuardrail {

    @Inject
    ObjectMapper mapper;

    @Override
    public OutputGuardrailResult validate(AiMessage responseFromLLM) {
        try {
            mapper.readTree(responseFromLLM.text());
        } catch (Exception e) {
            return reprompt("Invalid JSON", e, "Make sure you return a valid JSON object");
        }
        return success();
    }

}
```

Try this out with the weather forecast client.

## Next step

Now you are ready to move to the next [step](./../step-bonus-01-monitoring/README.md).
