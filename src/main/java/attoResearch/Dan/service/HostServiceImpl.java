package attoResearch.Dan.service;

import attoResearch.Dan.mapper.HostMapper;
import attoResearch.Dan.domain.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService{

    @Autowired
    private HostMapper hostMapper;

    @Override
    public List<Host> findAll() {
        return hostMapper.findAll();
    }

    @Override
    public Host host_info(int hnum){
        return hostMapper.host_info(hnum);
    }

    @Override
    public int host_count() {
        return hostMapper.host_count();
    }

    @Override
    public void host_insert(Host host) {
        hostMapper.host_insert(host);
    }

    @Override
    public void host_update(Host host) {
        hostMapper.host_update(host);
    }

    @Override
    public void host_delete(int hnum) {
        hostMapper.host_delete(hnum);
    }
}
