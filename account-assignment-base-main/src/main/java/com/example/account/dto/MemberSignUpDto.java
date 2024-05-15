package com.example.account.dto;

import com.example.account.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

public class MemberSignUpDto {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req {
        @NotBlank(message = "아이디 입력란은 비어 있을 수 없습니다.")
        private String user_id;

        @NotBlank(message = "비밀번호 입력란은 비어 있을 수 없습니다.")
        private String password;

        @NotBlank(message = "비밀번호 확인란은 비어 있을 수 없습니다.")
        private String password_check;

        @NotBlank(message = "이메일 입력란은 비어 있을 수 없습니다.")
        private String email;

        @NotBlank(message = "전화번호 입력란은 비어 있을 수 없습니다.")
        private String phone;

        public Member toEntity(){
            return Member
                    .builder()
                    .userId(user_id)
                    .password(password)
                    .email(email)
                    .phone(phone)
                    .build();
        }
    }


    //회원가입 : id, createdAt
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUp{
        private Long id;
        private LocalDateTime createdAt;

        public SignUp(Long id, LocalDateTime createdAt) {
            this.id = id;
            this.createdAt = createdAt;
        }
    }
}
