package com.nexsys.marketplace.config;

import com.nexsys.marketplace.util.Constants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(Constants.SWAGGER_TITLE)
                        .version(Constants.SWAGGER_VERSION)
                        .description(Constants.SWAGGER_DESCRIPTION));
    }
}
