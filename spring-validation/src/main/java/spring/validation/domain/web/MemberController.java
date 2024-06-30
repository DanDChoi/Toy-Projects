package spring.validation.domain.web;

import spring.validation.domain.member.MemberService;
import spring.validation.domain.member.dto.MemberRequestDto;
import spring.validation.domain.member.dto.MemberResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member")
    public Long saveMember(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return memberService.save(memberRequestDto);
    }

    @GetMapping("/members")
    public List<MemberResponseDto> findAll(){
        return memberService.findAll();
    }

    @PostMapping("/test")
    public ValidTestDto validTest(@Valid ValidTestDto validTestDto){
        return validTestDto;
    }

}
