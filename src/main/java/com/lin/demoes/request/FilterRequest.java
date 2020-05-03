package com.lin.demoes.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FilterRequest {

    private String logic;

    private List<FilterItem> filters = new ArrayList<>();
}
