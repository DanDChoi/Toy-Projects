package com.example.demo;

import com.example.demo.after.EnumContract;
import com.example.demo.after.EnumModel;
import com.example.demo.after.EnumValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping
    public Map<String, List<EnumValue>> getEnumValue(){
        Map<String, List<EnumValue>> enumValues = new LinkedHashMap<>();

        enumValues.put("commissionType", toEnumValues(EnumContract.CommissionType.class));
        enumValues.put("commissionCutting", toEnumValues(EnumContract.CommissionCutting.class));

        return enumValues;
    }

    private List<EnumValue> toEnumValues(Class<? extends EnumModel> e){
        return Arrays.stream(e.getEnumConstants())
                .map(EnumValue::new)
                .collect(Collectors.toList());
//        Java8이 아닌 경우
//        List<EnumValue> enumValues = new ArrayList<>();
//        for (EnumModel enumType : e.getEnumConstants()){
//            enumValues.add(new EnumValue(enumType));
//        }
//        return enumValues;
    }

}
