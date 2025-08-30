# Quarkus AI Agent Workshop

In this workshop we will build a simple AI agent with Quarkus and Langchain4j.
Next, we will explore how to securely integrate it into an application and enable monitoring and logging for production.
The workshop is divided into multiple parts, you can start with the first part and build your way through the workshop.
If you get stuck, you can always check out the solution in the next part.

## Prerequisites

Make sure you have the following installed locally:

- [JDK 21+](https://adoptium.net/) (you can leverage [SDKMAN!](https://sdkman.io))
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (or use [VS Code](https://code.visualstudio.com/) with the Java &
  Quarkus extension)
- [Podman Desktop](https://podman-desktop.io) (or use [Docker Desktop](https://www.docker.com/products/docker-desktop/))
- [Ollama](https://ollama.com) (and enable [OpenAPI compatibility](https://ollama.com/blog/openai-compatibility))

## Setup

> [!NOTE]
> This workshop needs to download a lot of dependencies, so it might take a while on the first run. If you have an
> unlimited data plan, you can speed up the process by using your mobile connection instead of the shared wireless
> connection.

First clone the repository and run the build:

```shell
./mvwn install -DskipTests
```

Next, you can explore some popular models on Ollama, there are a lot of great free models available:

- [llama3.2](https://ollama.com/library/llama3.2) (small model)
- [qwen2.5](https://ollama.com/library/qwen2.5) (small model)
- [qwen2.5-coder](https://ollama.com/library/qwen2.5-coder) (great for coding)
- [gemma3](https://ollama.com/library/gemma3) (CPU only model)
- [deepseek-r1](https://ollama.com/library/deepseek-r1) (popular model)
- [gpt-oss](https://ollama.com/library/gpt-oss) (open-weight language model from OpenAI)

We will use `llama3.2` for now, feel free to experiment with other models too.

```shell
ollama run llama3.2
```

> [!NOTE]
> Running models locally is great for development, but it restricted by the specifications of your machine.
> You can leverage a CPU only model, however the performance will be much lower.

> [!NOTE]
> If you have the option you can also leverage OpenAI so you don't need to run a model locally.
> Some costs apply, but this workshop should only cost you a few cents/dollars.
> You can generate an API key if you have a payment method setup on your profile.
>
> Next you can run the following command to export the API key:
> ```shell
> export OPENAI_API_KEY=YOUR_API_KEY
> ```
> Make sure to keep the API key secret, and you are responsible for the costs yourself. You can disable auto recharge to avoid surcharges.

Finally, you can open the project in IntelliJ IDEA and start coding!
Go to [Step 1](./step-01-introduction/README.md) to get started.

