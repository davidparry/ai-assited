package ai.qodo.joke;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.function.Predicate;

public class EvilJoke implements Joke {
  private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
  private final HttpClient httpClient;

  public EvilJoke() {
    this.httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
  }

  public EvilJoke(HttpClient client) {
    httpClient = client;
  }

  @Override
  public String joke() {
    return generateJoke("Buster Keaton");
  }

  private String generateJoke(String name) {
    var prompt = """
        Generate a light-hearted evil joke about a person named %s. 
        Keep it playful and not offensive.
        """.formatted(name);

    try {
      var request = HttpRequest.newBuilder().uri(URI.create(API_ENDPOINT)).header("Content-Type", "application/json")
          .header("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"))
          .POST(HttpRequest.BodyPublishers.ofString(createRequestBody(prompt))).build();

      return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
          .thenApply(this::extractJokeFromResponse).join();

    } catch (Exception e) {
      return "Even evil jokes need a break sometimes! Try again later.";
    }
  }

  private String createRequestBody(String prompt) {
    return """
        {
            "model": "gpt-3.5-turbo",
            "messages": [
                {
                    "role": "user",
                    "content": "%s"
                }
            ]
        }
        """.formatted(prompt);
  }

  private String extractJokeFromResponse(String response) {
    // In a real implementation, you would parse the JSON response
    // and extract the actual joke from the AI service response
    // This is a simplified version
    return Optional.ofNullable(response).filter(Predicate.not(String::isBlank)).map(r -> {
      // Simple extraction - in production code, use a proper JSON parser
      if (r.contains("\"content\":")) {
        int start = r.indexOf("\"content\":") + 11;
        int end = r.indexOf("\"", start);
        if (start > 0 && end > start) {
          return r.substring(start, end);
        }
      }
      return "Couldn't parse the joke response";
    }).orElse("Oops, the joke machine is having a bad day!");
  }
}
