package com.example.account.dto;

import com.example.account.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

public class MemberSignUpDto {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req {

        public static final String regexp1 ="^[a-zA-Z0-9]+$";
        @NotBlank(message = "아이디 입력란은 비어 있을 수 없습니다.")
        @Pattern(message = "아이디는 영문자와 숫자만 포함할 수 있습니다.", regexp = regexp1)
        private String userId;

        @NotBlank(message = "비밀번호 입력란은 비어 있을 수 없습니다.")
        private String password;

        @NotBlank(message = "이메일 입력란은 비어 있을 수 없습니다.")
        @Email(message = "이메일 형식을 맞춰주세요.")
        private String email;

        public static final String regexp2 ="^[\\d]{11}+$"; //전화번호 형식이 010XXXXXXXX
        @NotBlank(message = "전화번호 입력란은 비어 있을 수 없습니다.")
        @Pattern(message = "전화번호 형식을 맞춰주세요.",regexp = regexp2)
        private String phone;

        public Member toEntity(){
            return Member
                    .builder()
                    .userId(userId)
                    .password(password)
                    .email(email)
                    .phone(phone)
                    .build();
        }
    }
}
