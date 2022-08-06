package flow.daehyun.domain.extension;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ExtensionRepository {

    private static final Map<Long, Extension> store = new HashMap<>();
    private static long sequence = 0L;

    public Extension save(Extension extension){
        extension.setId(++sequence);
        log.info("extension.getId={}", extension.getId());
        store.put(extension.getId(), extension);
        return extension;
    }

    public Extension findById(Long id){
        return store.get(id);
    }
    public void delete(Long id){
        store.remove(id);
    }

    public List<Extension> findAll(){
        return new ArrayList<>(store.values());
    }
}
