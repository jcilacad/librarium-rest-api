package com.projects.reviewservice.service;

import com.projects.reviewservice.dto.request.ReviewRequest;
import com.projects.reviewservice.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {

    ReviewResponse addReview(ReviewRequest reviewRequest);

    ReviewResponse getReviewById(Long id);

    List<ReviewResponse> getAllReviews();

    List<ReviewResponse> getReviewsByBookId(Long bookId);

    ReviewResponse updateReview(Long id, ReviewRequest reviewRequest);

    void deleteReview(Long id);
}
