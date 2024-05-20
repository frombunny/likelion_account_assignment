package com.example.account.service;

import com.example.account.dto.MemberLoginDto;
import com.example.account.dto.MemberSignUpDto;
import com.example.account.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<CustomApiResponse<?>> signUp(MemberSignUpDto.Req req);

    ResponseEntity<CustomApiResponse<?>> login(MemberLoginDto.Req req);

    ResponseEntity<CustomApiResponse<?>> withDraw(String userId);
}
