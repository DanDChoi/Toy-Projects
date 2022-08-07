package flow.daehyun.web.extension;

import flow.daehyun.domain.extension.Extension;
import flow.daehyun.domain.extension.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@Controller
@RequestMapping("/fileExtension")
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionRepository extensionRepository;

    String[] fixEx = {"bat", "cmd", "com", "cpl", "exe", "scr", "js"};
    List<String> fixRepo = new ArrayList<>();
    Map<String, Boolean> fixedExtensions = new LinkedHashMap<>();
    @GetMapping
    public String extension(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<Extension> extensions = extensionRepository.findAll();
        if(cookies == null){
            for(int i=0; i<fixEx.length; i++){
                fixedExtensions.put(fixEx[i], false);
            }
        }else{
            for(int i=0; i<fixEx.length; i++){
                fixedExtensions.put(fixEx[i], false);
            }
            for(int i=0; i<cookies.length; i++){
                fixedExtensions.replace(cookies[i].getName(), true);
            }
        }
        model.addAttribute("fixedExtensions", fixedExtensions);
        model.addAttribute("extensions", extensions);
        return "extension/list";
    }
    @GetMapping("/fixed/{name}")
    public String extension(@PathVariable String name, Model model, HttpServletRequest request, HttpServletResponse response){
        List<Extension> extensions = extensionRepository.findAll();
        Cookie[] cookie = request.getCookies();
        for(int i=0; i<cookie.length; i++){
            if(cookie[i].getName().equals(name)){
                cookie[i].setMaxAge(0);
                cookie[i].setPath("/");
                response.addCookie(cookie[i]);
                return "redirect:/fileExtension";
            }
        }
        Cookie fixCookie = new Cookie(name, name);
        fixCookie.setPath("/");
        response.addCookie(fixCookie);
        model.addAttribute("extensions", extensions);
        return "redirect:/fileExtension";
    }

    @PostMapping
    public String addExtension(@ModelAttribute Extension extension, BindingResult bindingResult, Model model){
        log.info("extension.getName={}", extension.getName());

        List<Extension> extensions = extensionRepository.findAll();
        model.addAttribute("extensions", extensions);

        //validation
        Map<String, String> errors = new HashMap<>();

        //문자가 아닐경우
        String ptn = "^[a-zA-z]*$";
        boolean result = Pattern.matches(ptn, extension.getName());
        if(!result){
           errors.put("name", "영문자만 입력 해 주세요");
        }
        //이미 추가된 확장자인 경우
        for (Extension ex : extensions) {
            if(ex.getName().equals(extension.getName())){
                errors.put("name", "이미 등록된 확장자입니다");
            }
        }
        //등록된 확장자가 200개 이상인 경우
        if(extensions.size()>=200){
            errors.put("name", "등록은 200개까지만 가능합니다");
        }

        //에러발생
        if(!errors.isEmpty()){
            log.info("errors={}", errors);
            model.addAttribute("fixedExtensions", fixedExtensions);
            model.addAttribute("errors", errors);
            return "extension/list";
        }

        //정상수행일 경우
        Extension savedExtension = extensionRepository.save(extension);
        return "redirect:/fileExtension";
    }

    @GetMapping("/delete/{id}")
    public String removeExtension(@PathVariable Long id){
        log.info("id={}", id);
        extensionRepository.delete(id);
        return "redirect:/fileExtension";
    }

}
