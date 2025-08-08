package org.board.global.configs;

import lombok.RequiredArgsConstructor;
import org.board.member.libs.MemberUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    private final MemberUtil memberUtil;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(memberUtil.isLogin() ? memberUtil.getMember().getEmail() : null);
    }
}
