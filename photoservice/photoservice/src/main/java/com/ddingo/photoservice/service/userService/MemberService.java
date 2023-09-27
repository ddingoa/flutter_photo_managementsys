package com.ddingo.photoservice.service.userService;



import com.ddingo.photoservice.domain.user.Member;
import com.ddingo.photoservice.dto.member.MemberResponseDto;
import com.ddingo.photoservice.repository.userRepository.MemberRepository;
import com.ddingo.photoservice.util.jwtUtil.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }


    @Transactional(readOnly = true)
    public Member getMemberData(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Member> getMemberListData() {
        return memberRepository.findAll();
    }

}
