package com.lin.demoes.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
public class CommonFriendRequest {

    @Min(2)
    @Max(2)
    private List<String> friends;
}
