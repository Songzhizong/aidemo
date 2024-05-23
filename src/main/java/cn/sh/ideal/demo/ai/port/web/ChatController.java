package cn.sh.ideal.demo.ai.port.web;

import cn.sh.ideal.demo.ai.application.ChatService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author 宋志宗 on 2024/5/23
 */
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping(path = "/chat", produces = "text/event-stream")
    public Flux<String> chat(String chatId, String content, HttpServletResponse response) {
        response.setContentType("text/event-stream;charset=utf-8");
        return chatService.chat(chatId, content)
                .flatMap(resp -> Flux.fromIterable(resp.getResults()).map(g -> g.getOutput().getContent()));

    }
}
