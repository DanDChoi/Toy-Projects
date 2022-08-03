package attoResearch.Dan.controller;

import attoResearch.Dan.domain.Host;
import attoResearch.Dan.service.HostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class HostController {

    @Autowired
    private HostService hostService;

    @GetMapping("/")
    public ModelAndView hostList() {
        List<Host> hostList = hostService.findAll();
        String status = "";
        try{
            for(Host hosts : hostList){
                InetAddress ia = InetAddress.getByName(hosts.getIp());
                if(ia.isReachable(1000)){
                    status = "alive";
                    hosts.setStatus(status);
                }else{
                    status = "dead";
                    hosts.setStatus(status);
                }
            }
        }catch (IOException io){
            io.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("hosts/list", "hostList", hostList);
        return mv;
    }

    @GetMapping("info")
    public ModelAndView info(int hnum){
        Host host = hostService.host_info(hnum);
        String ip = host.getIp();
        String status = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String aliveTime = "";
        try{
            InetAddress ia = InetAddress.getByName(ip);
            if(ia.isReachable(1000)){
                status = "alive";
                Date now = new Date();
                aliveTime = sdf.format(now);
            }else{
                status = "dead";
                aliveTime = "알수없음";
            }
        }catch(IOException io){
            log.warn("io={}", io);
        }
        ModelAndView mv = new ModelAndView("hosts/info", "host", host);
        mv.addObject("status", status);
        mv.addObject("aliveTime", aliveTime);
        return mv;
    }
    @GetMapping("limit")
    public String limig(){
        return "hosts/limit";
    }

    @GetMapping("create")
    public String create() {
        if(hostService.host_count()>=100){
            return "redirect:/limit";
        }else{
            return "hosts/create";
        }
    }

    @PostMapping("create")
    public String create(Host host){
        String domain = host.getDomain();
        try{
            InetAddress ia = InetAddress.getByName(domain);
            log.info("getHostAddress={}", ia.getHostAddress());
            host.setIp(ia.getHostAddress());
            hostService.host_insert(host);
        }catch (UnknownHostException uhe){
            uhe.printStackTrace();
        }

        return "redirect:/";
    }

    @DeleteMapping("update")
    public ModelAndView update(int hnum){
        Host host = hostService.host_info(hnum);
        ModelAndView mv = new ModelAndView("hosts/update", "host", host);
        return mv;
    }

    @PatchMapping("update")
    public String update(Host host){
        String domain = host.getDomain();
        try{
            InetAddress ia = InetAddress.getByName(domain);
            log.info("getHostAddress={}", ia.getHostAddress());
            host.setIp(ia.getHostAddress());
            hostService.host_update(host);
        }catch (UnknownHostException uhe){
            uhe.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping("delete")
    public String delete(int hnum){
        hostService.host_delete(hnum);
        return "redirect:/";
    }
}

