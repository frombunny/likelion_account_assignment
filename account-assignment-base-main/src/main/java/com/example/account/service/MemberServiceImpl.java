package com.example.account.service;

import com.example.account.domain.Member;
import com.example.account.dto.MemberLoginDto;
import com.example.account.dto.MemberSignUpDto;
import com.example.account.repository.MemberRepository;
import com.example.account.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public ResponseEntity<CustomApiResponse<?>> login(MemberLoginDto.Req req) {
        Optional<Member> optionalMember=memberRepository.findByUserId(req.getUser_id());
        if(optionalMember.isEmpty()){ //해당하는 아이디가 없을 경우
            CustomApiResponse<Void> res=CustomApiResponse
                    .createFailWithoutData(HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 회원입니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        if(!optionalMember.get().getPassword().equals(req.getPassword())){ //비밀번호가 일치하지 않는 경우
            CustomApiResponse<Void> res=CustomApiResponse
                    .createFailWithoutData(HttpStatus.UNAUTHORIZED.value(),
                            "비밀번호가 일치하지 않습니다." );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);

        }

        Member member=optionalMember.get();
        MemberLoginDto.Req memberReq=new MemberLoginDto.Req(
                member.getUserId(),
                member.getPassword());

        CustomApiResponse<MemberLoginDto.Req> res=CustomApiResponse.createSuccess(
                HttpStatus.OK.value(), memberReq,"로그인이 완료되었습니다.");
        return ResponseEntity.ok(res);
    }


}
