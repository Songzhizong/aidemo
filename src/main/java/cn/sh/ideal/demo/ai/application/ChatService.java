package cn.sh.ideal.demo.ai.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.StreamingChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author 宋志宗 on 2024/5/23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final StreamingChatClient chatClient;

    @NonNull
    public Flux<ChatResponse> chat(@NonNull String chatId, @NonNull String content) {
        Prompt prompt = new Prompt(content);
        return chatClient.stream(prompt).doOnNext(message -> {
            List<Generation> results = message.getResults();
            for (Generation result : results) {
                System.out.print(result.getOutput().getContent());
            }
        });
    }
}
