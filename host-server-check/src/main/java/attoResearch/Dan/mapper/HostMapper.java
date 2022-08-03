package attoResearch.Dan.mapper;

import attoResearch.Dan.domain.Host;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HostMapper {

    List<Host> findAll();
    Host host_info(int hnum);
    int host_count();
    void host_insert(Host host);
    void host_update(Host host);
    void host_delete(int hnum);
}
