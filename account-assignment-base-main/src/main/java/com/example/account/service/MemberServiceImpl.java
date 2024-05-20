package com.example.account.service;

import com.example.account.domain.Member;
import com.example.account.dto.MemberLoginDto;
import com.example.account.dto.MemberSignUpDto;
import com.example.account.repository.MemberRepository;
import com.example.account.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    //회원 가입
    @Override
    public ResponseEntity<CustomApiResponse<?>> signUp(MemberSignUpDto.Req req) {
        Member member = req.toEntity();

        Member savedMember = memberRepository.save(member);


        CustomApiResponse<MemberSignUpDto.Req> res=CustomApiResponse.createSuccess(
                HttpStatus.OK.value(), null,"회원가입이 완료되었습니다.");
        return ResponseEntity.ok(res);
    }

    //로그인
    @Override
    public ResponseEntity<CustomApiResponse<?>> login(MemberLoginDto.Req req) {
        Optional<Member> optionalMember=memberRepository.findByUserId(req.getUserId());
        if(optionalMember.isEmpty()){ //해당하는 아이디가 없을 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 회원입니다."));
        }

        if(!optionalMember.get().getPassword().equals(req.getPassword())){ //비밀번호가 일치하지 않는 경우
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.UNAUTHORIZED.value(),
                            "비밀번호가 일치하지 않습니다."));

        }

        Member member=optionalMember.get();
        MemberLoginDto.Req memberReq=new MemberLoginDto.Req(
                member.getUserId(),
                member.getPassword());

        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(),null,"로그인에 성공하였습니다."));
    }

    //회원 탈퇴(로그인 되어 있는 상태에서 진행)
    @Override
    public ResponseEntity<CustomApiResponse<?>> withDraw(String userId) {
        Optional<Member> optionalMember=memberRepository.findByUserId(userId);

        if(optionalMember.isEmpty()){ //해당하는 아이디가 없을 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(),
                            "id가 "+userId+"인 회원은 존재하지 않습니다."));
        }
        Member member=optionalMember.get();
        memberRepository.delete(member);
        CustomApiResponse<Void> res=CustomApiResponse.createSuccess(
                HttpStatus.OK.value(),null, "탈퇴되었습니다.");

        return ResponseEntity.ok(res);

    }


}
