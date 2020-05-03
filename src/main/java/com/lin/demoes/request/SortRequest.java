package com.lin.demoes.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortRequest {
    private String field;
    private String dir;
}
