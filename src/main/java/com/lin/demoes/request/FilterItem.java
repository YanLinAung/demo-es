package com.lin.demoes.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterItem {

    private String operator;

    private String value;

    private String field;
}
