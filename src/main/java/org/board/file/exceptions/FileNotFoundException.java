package org.board.file.exceptions;

import org.board.global.exceptions.NotFoundException;

public class FileNotFoundException extends NotFoundException {
    public FileNotFoundException() {
        super("NotFound.file");
        setErrorCode(true);
    }
}
