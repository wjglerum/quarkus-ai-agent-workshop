# Building secure AI agents with Quarkus LangChain4j

In this workshop we will build a simple **secure AI agent** with Quarkus and LangChain4j.
Next, we will explore how to securely integrate it into an application and enable monitoring and logging for production.
The workshop is divided into multiple parts, you can start with the first part and build your way through the workshop:

- **Chatbot & Tools** (WebSocket UI, REST clients, function-calling)
- **MCP (Model Context Protocol)** integration
- **RAG (Retrieval-Augmented Generation)** over your own docs
- **Guardrails** (input/output validation)
- **Testing** (fast, deterministic guardrail tests; optional scoring)
- **Observability** for production

Each step is self-contained. If you get stuck, you can always check out the solution in the next part.

## Prerequisites

Make sure you have the following installed locally:

- [JDK 21+](https://adoptium.net/) (you can leverage [SDKMAN!](https://sdkman.io))
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [VS Code](https://code.visualstudio.com/) with the Java &
  Quarkus extension enabled
- [Podman Desktop](https://podman-desktop.io) or [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Ollama](https://ollama.com)
- [Quarkus CLI](https://quarkus.io/guides/cli-tooling)

### Setup

> [!NOTE]
> This workshop needs to download a lot of dependencies, so it might take a while on the first run. If you have an
> unlimited data plan, you can speed up the process by using your mobile connection instead of the shared wireless
> connection.

### Initial clone

First clone the repository and run the build:

```shell
./mvnw install -DskipTests
```

## Enable LLM

To run this workshop you need access to an LLM.
You can either use a local model or one of the other providers that you have access to.

- [Ollama](#ollama)
- [OpenAI](#openai)
- [Gemini](#gemini)

### Ollama

Ollama is a free tool that can be used to run models locally.
You can explore some popular models from Ollama, there are a lot of great free models available:

- [llama3.2](https://ollama.com/library/llama3.2) (small model)
- [qwen2.5](https://ollama.com/library/qwen2.5) (small model)
- [qwen2.5-coder](https://ollama.com/library/qwen2.5-coder) (great for coding)
- [gemma3](https://ollama.com/library/gemma3) (CPU only model)
- [deepseek-r1](https://ollama.com/library/deepseek-r1) (popular model)
- [gpt-oss](https://ollama.com/library/gpt-oss) (open-weight language model from OpenAI)

We will use `llama3.2` for now.
Feel free to experiment with other models too, do watch the download size though!
Larger models will take longer to download, so you might want to use a smaller model for the workshop.
Also to run large models you will need to have plenty of free disk space and memory available.

You can start a model directly from the app or from the command line:

```shell
ollama run llama3.2
```

> [!NOTE]
> Running models locally is great for development, but it is restricted by the specifications of your machine.
> You can also use a CPU only model, however the performance will be much lower and expect slow responses.

### OpenAI

> [!NOTE]
> If you have the option, you can also leverage OpenAI so you don't need to run a model locally.
> Unfortunately, the free tier has been discontinued, so you will need to use a paid plan.
> Some costs apply, but this workshop should only cost you a few cents/dollars.
> This is not required for the workshop, but feel free to explore.

You can generate an API key on your [profile](https://platform.openai.com/api-keys).
This is only possible if you have a payment method set up.
Next you can run the following command to export the API key:

```shell
export OPENAI_API_KEY=<YOUR_API_KEY_HERE>
``` 

> [!WARNING]
> Make sure to keep the API key secret.
> You are responsible for the costs yourself.
> You can disable auto recharge to avoid surcharges.

### Gemini

If you have a Google account you can also leverage the free tier from Gemini.
You can generate an API key on your [profile](https://aistudio.google.com/app/apikey).
No payment method is required.
Check out the [documentation](https://ai.google.dev/gemini-api/docs/api-key) for more information.
Next you can run the following command to export the API key:

```shell
export GEMINI_API_KEY=<YOUR_API_KEY_HERE>
```

## Getting started

Before diving in, open the **[Step 0 – Setup](./step-00-your-solution/README.md)** folder.  
This is your **personal workspace** — all your code for the workshop will live there.

- [Step 1 - Introduction](./step-01-introduction/README.md)
- [Step 2 - Chatbot](./step-02-chatbot/README.md)
- [Step 3 - Authentication](./step-03-authentication/README.md)
- [Step 4 - Tools](./step-04-tools/README.md)
- [Step 5 - MCP Server](./step-05-mcp-server/README.md)
- [Step 6 - RAG](./step-06-rag/README.md)
- [Step 7 - Guardrails](./step-07-guardrails/README.md)
- [Step 8 - Testing](./step-08-testing/README.md)
- [Bonus 1 - Observability](step-bonus-01-observability/README.md)
- [Bonus 2 - Hexagonal Architecture](step-bonus-02-hexagonal-architecture/README.md)
- [Bonus 3 - Your own use case!](step-bonus-03-use-case/README.md)

### Further reading

Quarkus is a great framework for writing AI agents and tools.
If you want to learn more, you can check out the following resources:

- [Quarkus Documentation](https://quarkus.io/guides)
- [Quarkus LangChain4j Workshop](https://quarkus.io/quarkus-workshop-langchain4j/)

- Blog post series: Agentic AI with Quarkus
    - [Agentic AI with Quarkus - part 1](https://quarkus.io/blog/agentic-ai-with-quarkus/)
    - [Agentic AI with Quarkus - part 2](https://quarkus.io/blog/agentic-ai-with-quarkus-p2/)
    - [Agentic AI with Quarkus - part 3](https://quarkus.io/blog/agentic-ai-with-quarkus-p3/)

- Blog post series: Securing MCP with Quarkus
    - [Getting ready for secure MCP with Quarkus MCP Server](https://quarkus.io/blog/secure-mcp-sse-server/)
    - [Use Quarkus MCP client to access secure MCP HTTP servers](https://quarkus.io/blog/secure-mcp-client/)
    - [Use Quarkus MCP client to access secure MCP HTTP server from command line](https://quarkus.io/blog/secure-mcp-oidc-client/)

### Acknowledgements

This workshop was inspired by the
existing [Quarkus LangChain4j Workshop](https://quarkus.io/quarkus-workshop-langchain4j/) and uses examples from the
Quarkus website, documentation and blog posts. If you find any issues or have suggestions, please open an issue or a PR.
