package com.example.account.service;

import com.example.account.dto.MemberLoginDto;
import com.example.account.dto.MemberSignUpDto;
import com.example.account.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository //자동으로 빈 등록
public interface MemberService {
    ResponseEntity<CustomApiResponse<?>> signUp(MemberSignUpDto.Req req);

    ResponseEntity<CustomApiResponse<?>> login(MemberLoginDto.Req req);

    ResponseEntity<CustomApiResponse<?>> withDraw(String userId);
}
