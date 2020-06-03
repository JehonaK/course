package com.course.config;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbxConfiguration {

    private static final String ACCESS_TOKEN = "xenlsSWs0YAAAAAAAAAAIwtJNWafBijGmvdKAV4QH144IJEuljBCLhMXJFfv1CHI";

    @Bean
    public DbxRequestConfig requestConfig() {
        return DbxRequestConfig.newBuilder("edu-platform").build();
    }

    @Bean
    public DbxClientV2 dbxClient() {
        return new DbxClientV2(requestConfig(), ACCESS_TOKEN);
    }

}
