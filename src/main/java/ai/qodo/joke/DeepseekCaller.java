package ai.qodo.joke;


import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.output.Response;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeepseekCaller implements LLMCaller {
  private static final Logger log = Logger.getLogger(DeepseekCaller.class.getName());

  @Override
  public String call(String joke) {
    ChatLanguageModel model =
        OllamaChatModel.builder().baseUrl("http://localhost:11434").modelName("deepseek-r1:32b").timeout(Duration.ofMinutes(5))
            .temperature(0.0).build();
    ChatMessage userChat = new UserMessage(joke);
    try {
      Response<AiMessage> message = model.generate(OpenAICaller.getSystemChat(), userChat);
      return message.content().text();
    } catch (Exception e) {
      log.log(Level.SEVERE, "Failed to generate response", e);
      return "Failed to generate funny story by calling deepseek-r1:32b " + e.getMessage();
    }

  }
}
