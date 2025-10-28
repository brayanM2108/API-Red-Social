package com.melo.vibyn.user.domain.exception;

public class NicknameAlreadyExistsException extends RuntimeException {

    public NicknameAlreadyExistsException(String nickname) {
        super("The nickname " + nickname + " is already registered");
    }
}
