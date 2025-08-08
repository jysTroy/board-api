package org.board.member.libs;

import org.board.member.MemberInfo;
import org.board.member.constants.Authority;
import org.board.member.entities.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberUtil {
    public boolean isLogin() {
        return getMember() != null;
    }

    public boolean isAdmin() {
        return isLogin() && getMember().getAuthority() == Authority.ADMIN;
    }

    public Member getMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof MemberInfo memberInfo) {
            return memberInfo.getMember();
        }

        return null;
    }
}
