package flow.daehyun.web.extension;

import flow.daehyun.domain.extension.Extension;
import flow.daehyun.domain.extension.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@Controller
@RequestMapping("/fileExtension")
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionRepository extensionRepository;


    @ModelAttribute("fixedExtensions")
    public Map<String, Boolean> fixedExtensions(){
        Map<String, Boolean> fixedExtensions = new LinkedHashMap<>();
        fixedExtensions.put("bat", false);
        fixedExtensions.put("cmd", false);
        fixedExtensions.put("com", false);
        fixedExtensions.put("cpl", false);
        fixedExtensions.put("exe", false);
        fixedExtensions.put("scr", false);
        fixedExtensions.put("js", false);
        return fixedExtensions;
    }

    @GetMapping
//            (value = {"/", "/{name}&{result}")
    public String extension(Model model, String name, boolean result) {
        List<Extension> extensions = extensionRepository.findAll();
        model.addAttribute("extensions", extensions);
        return "extension/list";
    }
    @GetMapping("{name}&{result}")
    public String extension(@PathVariable String name, @PathVariable boolean result, Model model){
        List<Extension> extensions = extensionRepository.findAll();
        model.addAttribute("extensions", extensions);
        //fixedExtensions에 하나만 들어가서 오류남
        model.addAttribute("fixedExtensions", fixedExtensions().replace(name, result));
        return "extension/list";
    }

    @PostMapping
    public String addExtension(@ModelAttribute Extension extension, BindingResult bindingResult, Model model){
        log.info("extension.getName={}", extension.getName());
        //문자열 체크
        String ptn = "^[a-zA-z]*$";
        boolean result = Pattern.matches(ptn, extension.getName());

        Map<String, String> errors = new HashMap<>();

        if(!result){
           errors.put("name", "문자만 입력 해 주세요");
        }

        //에러가 있을경우
        if(!errors.isEmpty()){
            log.info("errors={}", errors);
            List<Extension> extensions = extensionRepository.findAll();
            model.addAttribute("errors", errors);
            model.addAttribute("extensions", extensions);
            return "extension/list";
        }
        //정상수행일 경우
        Extension savedExtension = extensionRepository.save(extension);
        return "redirect:/fileExtension";
    }

    @GetMapping("/fixed/{name}")
    public String addFixedExtension(@PathVariable() String name, @ModelAttribute Extension extension, Model model){
        extensionRepository.findAll();
        if(fixedExtensions().get(name) == false){
            model.addAttribute("extension", extension);
            boolean result = true;
            log.info("아래쪽result={}", result);
            return "redirect:/fileExtension/"+name+"&"+result;
        }else{
            fixedExtensions().put(name, false);
            model.addAttribute("extension", extension);
            boolean result = fixedExtensions().get(name);
            return "redirect:/fileExtension/"+name+"&"+result;
        }

    }
    @PostMapping("/fixed/{name}")
    public String addFixedExtensions(String name, @ModelAttribute Extension extension, Model model){

        return "redirect:/fileExtension";
    }

    @GetMapping("/delete/{id}")
    public String removeExtension(@PathVariable Long id){
        log.info("id={}", id);
        extensionRepository.delete(id);
        return "redirect:/fileExtension";
    }

}
