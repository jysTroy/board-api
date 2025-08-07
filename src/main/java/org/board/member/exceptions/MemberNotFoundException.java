package org.board.member.exceptions;

import org.board.global.exceptions.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException() {
        super("NotFound.member");
        setErrorCode(true);
    }
}
