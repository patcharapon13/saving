package com.self.saving.core;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@AllArgsConstructor
public class CoreController {

    private final CoreService coreService;



    @GetMapping
    public String getIndexPage(Model model){
        model.addAttribute("sheetRequest",new SheetRequest());
        model.addAttribute("showModal",false);
        return "index";
    }

    @PostMapping("/save")
    public String update(@ModelAttribute("sheetRequest") SheetRequest sheetRequest,Model model){
//        coreService.doUpdateSheet(sheetRequest.getCost(),sheetRequest.getComment());
        model.addAttribute("showModal", true);

        return "index";
    }

    @GetMapping("/close")
    public String close(Model model){
        model.addAttribute("showModal",false);
        System.out.println("HELLO");
        return "index";
    }

    @PostMapping("doUpdate")
    public ResponseEntity<String> doUpdateSheet(@RequestParam BigDecimal cost,@RequestParam String comment){
        return ResponseEntity.ok(coreService.doUpdateSheet(cost,comment));

    }

}
