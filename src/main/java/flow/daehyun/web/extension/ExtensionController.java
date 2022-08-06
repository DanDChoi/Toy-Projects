package flow.daehyun.web.extension;

import flow.daehyun.domain.extension.Extension;
import flow.daehyun.domain.extension.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@Controller
@RequestMapping("/fileExtension")
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionRepository extensionRepository;

    @ModelAttribute("fixedExtensions")
    public List<String> fixedExtensions(){
        List<String> fixedExtensions = new ArrayList<>();
        fixedExtensions.add("bat");
        fixedExtensions.add("cmd");
        fixedExtensions.add("com");
        fixedExtensions.add("cpl");
        fixedExtensions.add("exe");
        fixedExtensions.add("scr");
        fixedExtensions.add("js");
        return fixedExtensions;
    }

    @GetMapping
    public String extension(Model model) {
        List<Extension> extensions = extensionRepository.findAll();
        model.addAttribute("extensions", extensions);
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

    @PostMapping("/post/{name}")
    public String addFixedExtension(@PathVariable String name, @ModelAttribute Extension extension){
        List<Extension> extensions = extensionRepository.findAll();
        for (Extension ex : extensions) {
            if(ex.getName().equals(name)){
                return "/fileExtension/delete/"+ex.getId();
            }else{
                List<Extension> ext = extensionRepository.save(extension);
                return "redirect:/fileExtension";
            }
        }
        return "redirect:/fileExtension";
    }

    @GetMapping("/delete/{id}")
    public String removeExtension(@PathVariable Long id){
        log.info("id={}", id);
        extensionRepository.delete(id);
        return "redirect:/fileExtension";
    }

}
