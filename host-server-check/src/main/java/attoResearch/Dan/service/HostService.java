package attoResearch.Dan.service;

import attoResearch.Dan.domain.Host;

import java.util.List;

public interface HostService {

    List<Host> findAll();
    Host host_info(int hnum);
    int host_count();
    void host_insert(Host host);
    void host_update(Host host);
    void host_delete(int hnum);
}
