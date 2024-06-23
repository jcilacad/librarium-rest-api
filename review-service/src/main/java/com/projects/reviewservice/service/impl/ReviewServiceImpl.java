package com.projects.reviewservice.service.impl;

import com.projects.reviewservice.dto.request.ReviewRequest;
import com.projects.reviewservice.dto.response.ReviewResponse;
import com.projects.reviewservice.entity.Review;
import com.projects.reviewservice.exception.ReviewNotFoundException;
import com.projects.reviewservice.mapper.ReviewMapper;
import com.projects.reviewservice.repository.ReviewRepository;
import com.projects.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResponse addReview(ReviewRequest reviewRequest) {
        Review review = ReviewMapper.INSTANCE.reviewRequestToReview(reviewRequest);
        // TODO: Create a logic to check if the book and member exits in the member and book services.
        Review savedReview = reviewRepository.save(review);
        log.info("Review added successfully with id: {}", savedReview.getId());
        return ReviewMapper.INSTANCE.reviewToReviewResponse(savedReview);
    }

    @Override
    public ReviewResponse getReviewById(Long id) {
        log.info("Review found with id: {}", id);
        return reviewRepository.findById(id)
                .map(ReviewMapper.INSTANCE::reviewToReviewResponse)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        log.info("Found all reviews");
        return reviewRepository.findAll()
                .stream()
                .map(ReviewMapper.INSTANCE::reviewToReviewResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> getReviewsByBookId(Long bookId) {
        log.info("Found review/s with book id: {}", bookId);
        return reviewRepository.findByBookId(bookId)
                .stream()
                .map(ReviewMapper.INSTANCE::reviewToReviewResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponse updateReview(Long id, ReviewRequest reviewRequest) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        // TODO: Create a logic to check if the book and member exits in the member and book services.
        log.info("Review found with id: {}", id);
        review.setBookId(reviewRequest.getBookId());
        review.setMemberId(reviewRequest.getMemberId());
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        Review updatedReview = reviewRepository.save(review);
        log.info("Review updated successfully with id: {}", updatedReview.getId());
        return ReviewMapper.INSTANCE.reviewToReviewResponse(updatedReview);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        log.info("Review deleted successfully with id: {}", id);
        reviewRepository.delete(review);
    }
}
