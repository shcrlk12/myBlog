package com.kjwon.myblog.member.repository;

import com.kjwon.myblog.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByEmailAuthKey(String emailAuthKey);
    Optional<Member> findByUserIdAndUserName(String userId, String userName);
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByResetPasswordKey(String resetPasswordKey);



}
