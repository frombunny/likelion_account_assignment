package com.example.account.dto;

import lombok.*;

public class MemberLoginDto {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Req {
        private String userId;

        private String password;
    }
}
