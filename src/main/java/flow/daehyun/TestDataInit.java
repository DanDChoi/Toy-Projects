package flow.daehyun;

import flow.daehyun.domain.extension.Extension;
import flow.daehyun.domain.extension.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ExtensionRepository extensionRepository;

    @PostConstruct
    public void init(){
        extensionRepository.save(new Extension(1L, "txt"));
        extensionRepository.save(new Extension(2L, "zip"));
    }

}
