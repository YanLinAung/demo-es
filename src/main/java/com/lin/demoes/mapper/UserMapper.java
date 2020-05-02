package com.lin.demoes.mapper;

import com.lin.demoes.model.EsUser;
import com.lin.demoes.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class UserMapper {

    public abstract EsUser convertToIndex(User user);
}
