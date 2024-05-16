package com.example.account.controller;

import com.example.account.dto.MemberLoginDto;
import com.example.account.dto.MemberSignUpDto;
import com.example.account.service.MemberService;
import com.example.account.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<CustomApiResponse<?>> signUp(
            @Valid //해당 input에 대해 유효성 검증
            @RequestBody MemberSignUpDto.Req req) {
        ResponseEntity<CustomApiResponse<?>> result=memberService.signUp(req);
        return result;

    }

    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<?>> login(
            @RequestBody MemberLoginDto.Req req){
        ResponseEntity<CustomApiResponse<?>> result=memberService.login(req);
        return result;
    }




}
