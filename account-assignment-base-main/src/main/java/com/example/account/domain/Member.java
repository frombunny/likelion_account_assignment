package com.example.account.domain;

import com.example.account.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity //entity에는 setter 사용 X
@Table
@Builder
@SequenceGenerator(
        name="MEMBER_SEQ_GENERATOR", // 제너레이터
        sequenceName="MEMBER_SEQ", // 시퀀스
        allocationSize= 1 // 할당할 범위 사이즈
)

public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")

    @Column(name="ID") //사용자 고유의 아이디(사용자가 지정하는 것 아님)
    private Long id;

    @Column(name="USER_ID") //사용자가 설정한 아이디
    private String userId;

    @Column(name="PASSWORD") //사용자가 설정한 비밀번호
    private String password;

    @Column(name = "EMAIL") //회원가입 시 입력한 이메일 주소
    private String email;

    @Column(name = "PHONE") //회원가입 시 입력한 전화번호
    private String phone;



}
