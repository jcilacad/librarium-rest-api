package com.projects.bookservice.mapper;

import com.projects.bookservice.dto.request.BookRequest;
import com.projects.bookservice.dto.response.BookResponse;
import com.projects.bookservice.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookRequestToBook(BookRequest bookRequest);

    BookResponse bookToBookResponse(Book book);
}
