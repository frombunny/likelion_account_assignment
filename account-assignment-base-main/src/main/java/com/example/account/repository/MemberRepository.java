package com.example.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class MemberRepository extends JpaRepository<Member, BigInteger> {

}
