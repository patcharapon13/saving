package com.self.saving.client;

import com.self.saving.client.interceptor.UpdateSheetInterceptor;
import com.self.saving.core.SheetResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "SheetUpdate", url = "${application.sheets-post-endpoint}")
public interface UpdateSheet {

//    @PostMapping("/HELLO")
//    String doUpdateSheet(@RequestParam BigDecimal cost, @RequestParam String comment);

    @PostMapping()
    String doUpdateSheet(@RequestParam BigDecimal cost, @RequestParam String comment);
}
