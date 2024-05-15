package com.example.account.service;

import com.example.account.domain.Member;
import com.example.account.dto.MemberSignUpDto;
import com.example.account.repository.MemberRepository;
import com.example.account.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<CustomApiResponse<?>> signUp(MemberSignUpDto.Req req) {
        Member member = req.toEntity();

        Member savedMember = memberRepository.save(member);

        MemberSignUpDto.SignUp signUpResponse=new MemberSignUpDto.SignUp(
                savedMember.getId(),savedMember.getCreatedAt());

        CustomApiResponse<MemberSignUpDto.SignUp> res=CustomApiResponse.createSuccess(
                HttpStatus.OK.value(), signUpResponse,"회원가입이 완료되었습니다.");
        return ResponseEntity.ok(res);
    }
}
