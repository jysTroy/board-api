package org.board.member.services;

import lombok.RequiredArgsConstructor;
import org.board.member.constants.Authority;
import org.board.member.controllers.RequestJoin;
import org.board.member.entities.Member;
import org.board.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Lazy
@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository repository;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;

    public void process(RequestJoin form) {
        Member member = mapper.map(form, Member.class);

        String password = form.getPassword();
        if (StringUtils.hasText(password)) {
            member.setPassword(encoder.encode(password));
        }

        member.setCredentialChangedAt(LocalDateTime.now());
        member.setAuthority(Authority.MEMBER);

        repository.saveAndFlush(member);
    }
}
