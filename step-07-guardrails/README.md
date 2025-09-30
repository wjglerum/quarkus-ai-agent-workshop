# Step 7 - Guardrails

Guardrails are a set of rules that are applied to your large language model. It can define a set of rules for the input and
output of your model.
For the full docs check out the [documentation](https://docs.langchain4j.dev/tutorials/guardrails/).

![guardrails](https://docs.quarkiverse.io/quarkus-langchain4j/dev/_images/guardrails.png)
https://docs.quarkiverse.io/quarkus-langchain4j/dev/guardrails.html


> [!NOTE]
> Ideally, we want to follow the single responsibility principle, so hahve each guardrail class validate a single rule.
> Then, we chain them together to guard our model.
> The order matters here - the first guardrail to fail will stop the chain and return the result.
> So, prioritize the guardrails that are most likely to fail.
> Leave the least likely to fail guardrails to the end of the chain.

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

Let's create a new package `org.acme.guardrails` and add a new class `MaxLength` to it.


### 1) Guardrail Implementation
Let's configure ...

```java
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
            // Stop immediately: later guardrails won't run; LLM will not be called
            return fatal("Input too long (" + len + " > " + maxChars + " characters)");
        }
        return success();
    }
}
```

Optional config (in `application.properties`): `guardrails.max-input-chars=1000`

### 2) Register the input guardrail on our AI service

To register the input guardrail, we need to modify our AI service by adding `@InputGuardrails({ MaxLength.class })` to our `ChatBot` class's `chat` method.
Order matters: if you add more input rails later (e.g., domain whitelist, injection sanitizer), list them in the exact order you want them evaluated.

## Output Guardrails

We can filter the outputs of our model before returning them to the user.
You can also use output guardrails to process the output of your model.

Similarly to the input guardrail, let's create a new class `AllowedLocationsGuardrail` in the `org.acme.guardrails` package.
This guardrail will process output and ensure our chatbot refers to our City Guide from the previous step.

```java
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
```


Let's wire the `AllowedLocationsGuardrail` to our `ChatBot` class by adding `@OutputGuardrails({ AllowedLocationsGuardrail.class })` to the `chat` method.

## Next step

Now you are ready to move to the next [step](./../step-08-testing/README.md).
