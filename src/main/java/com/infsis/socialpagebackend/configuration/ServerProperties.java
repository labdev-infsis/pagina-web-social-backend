package com.infsis.socialpagebackend.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:server.properties")
@Data
public class ServerProperties {

    @Value("${server-ws.schema}")
    private String schema;

    @Value("${server-ws.host}")
    private String host;

    @Value("${server-ws.port}")
    private String port;

}
