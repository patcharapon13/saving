package com.self.saving.client.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Objects;

@AllArgsConstructor
public class UpdateSheetInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {

        template.header(HttpHeaders.CONTENT_LENGTH, "100")
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);


    }
}
