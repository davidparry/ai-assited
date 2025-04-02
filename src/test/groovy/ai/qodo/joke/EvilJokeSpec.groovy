package ai.qodo.joke

import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpResponse
import java.util.concurrent.CompletableFuture

class EvilJokeSpec extends Specification {
    // Joke generation with valid name returns a joke from the API
    def "should return joke from API when given valid name"() {
        given:
        def mockResponse = Mock(HttpResponse)
        def mockClient = Mock(HttpClient)
        def mockCompletableFuture = Mock(CompletableFuture)
        def responseBody = '{"choices":[{"message":{"content":"Why did evil John cross the road? To get to the dark side!"}}]}'
        def evilJoke = new EvilJoke(mockClient)

        when:
        def result = evilJoke.joke("John")

        then:
        1 * mockClient.sendAsync(_, _) >> mockCompletableFuture
        1 * mockCompletableFuture.thenApply(_) >> mockCompletableFuture
        1 * mockCompletableFuture.thenApply(_) >> mockCompletableFuture
        1 * mockCompletableFuture.join() >> "Why did evil John cross the road? To get to the dark side!"

        and:
        result == "Why did evil John cross the road? To get to the dark side!"
    }

}