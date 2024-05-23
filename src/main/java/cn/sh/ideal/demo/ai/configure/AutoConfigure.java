package cn.sh.ideal.demo.ai.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * @author 宋志宗 on 2024/5/23
 */
@Configuration
public class AutoConfigure {

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

}
