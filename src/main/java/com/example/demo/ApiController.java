package com.example.demo;

import com.example.demo.after.EnumContract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ApiController {

    @GetMapping("/enum")
    public Map<String, Object> getEnum(){
        Map<String, Object> enums = new LinkedHashMap<>();

        Class commissionType = EnumContract.CommissionType.class;
        Class commissionCutting = EnumContract.CommissionCutting.class;

        enums.put("commissionType", commissionType.getEnumConstants());
        enums.put("commissionCutting", commissionCutting.getEnumConstants());
        return enums;
    }

}
