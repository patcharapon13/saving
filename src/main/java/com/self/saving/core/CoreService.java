package com.self.saving.core;

import com.self.saving.client.UpdateSheet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CoreService {

    private UpdateSheet updateSheet;


    public String doUpdateSheet(BigDecimal cost,String comment){
        var doUpdateSheet = updateSheet.doUpdateSheet(cost,comment);
        System.out.println(doUpdateSheet);
        return "";
    }
}
