package cn.sh.ideal.demo.ai.configure;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 宋志宗 on 2024/5/23
 */
@Component
public class RagWorker implements ApplicationRunner {

    private final VectorStore vectorStore;
    private final Resource resource;

    public RagWorker(VectorStore vectorStore,
                     @Value("classpath:http.md") Resource resource) {
        this.vectorStore = vectorStore;
        this.resource = resource;
    }

    @Override
    public void run(ApplicationArguments args) {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(resource);
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        List<Document> documents = tikaDocumentReader.get();
        List<Document> apply = tokenTextSplitter.apply(documents);
//        vectorStore.add(apply);
    }
}
