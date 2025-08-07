package org.board.member.services;

import lombok.RequiredArgsConstructor;
import org.board.member.MemberInfo;
import org.board.member.entities.Member;
import org.board.member.jwt.JwtProperties;
import org.board.member.repository.MemberRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Lazy
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return MemberInfo.builder()
                .member(member)
                .build();
    }
}
