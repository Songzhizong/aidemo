package cn.sh.ideal.demo.ai;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AidemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(AidemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
//        Prompt prompt = new Prompt("为什么小孩子的肚子大", OllamaOptions.create().withModel("qwen:7b"));
//        chatClient.stream(prompt).doOnNext(message -> {
//            List<Generation> results = message.getResults();
//            for (Generation result : results) {
//                System.out.print(result.getOutput().getContent());
//            }
//        }).subscribe();
    }
}
