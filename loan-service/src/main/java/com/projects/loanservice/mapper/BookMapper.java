package com.projects.loanservice.mapper;

import com.projects.loanservice.dto.request.BookRequest;
import com.projects.loanservice.dto.response.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookRequest bookResponseToBookRequest(BookResponse bookResponse);
}
