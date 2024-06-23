package com.projects.reviewservice.mapper;

import com.projects.reviewservice.dto.request.ReviewRequest;
import com.projects.reviewservice.dto.response.ReviewResponse;
import com.projects.reviewservice.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review reviewRequestToReview(ReviewRequest reviewRequest);

    ReviewResponse reviewToReviewResponse(Review review);
}
