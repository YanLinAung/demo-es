package com.lin.demoes;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class ErrorResponse {

    private boolean success = false;

    @NonNull
    private String errorMessage;

}
