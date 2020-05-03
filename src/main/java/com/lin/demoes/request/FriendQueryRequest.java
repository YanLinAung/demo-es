package com.lin.demoes.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FriendQueryRequest {

    private FilterRequest filter;

    private List<SortRequest> sort = new ArrayList<>();
}
