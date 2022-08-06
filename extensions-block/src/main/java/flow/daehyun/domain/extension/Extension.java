package flow.daehyun.domain.extension;

import lombok.Data;

import java.util.List;

@Data
public class Extension {

    private Long id;
    private String name;

    private List<String> fixedExtensions;

    public Extension() {
    }
    public Extension(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
