package com.ddingo.photoservice.controller.user;



import com.ddingo.photoservice.domain.user.Member;
import com.ddingo.photoservice.dto.member.MemberResponseDto;
import com.ddingo.photoservice.service.userService.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/get/memberlist")
    private Map UserGetMast() {
        HashMap resultMap = new HashMap<String, String>();
        try {
            List<Member> membersList = memberService.getMemberListData();
            resultMap.put("ResultValue", "조회에 성공하였습니다.");
            resultMap.put("data", membersList );
        } catch (Exception e) {
            resultMap.put("ResultValue", e.getMessage());
        }
        return resultMap;
    }


    @GetMapping("/{email}")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String email) {
        return ResponseEntity.ok(memberService.getMemberInfo(email));
    }
}