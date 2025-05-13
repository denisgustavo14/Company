package com.examen.company.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    NOT_FOUND("COM-404", "The requested information was not found."),

    BAD_GATEWAY("COM-502",  "It is not possible to establish a connection with the server."),

    BAD_REQUEST("COM-400", "The request has an incorrect format."),

    INTERNAL_SERVER_ERROR("COM-500", "An internal error occurred, please try again later."),

    UNPROCESSABLE_ENTITY("COM-422", "The request could not be processed."),

    CONFLICT("COM-409"," conflict was found in the request.");

    private final String code;

    private final String message;
}
