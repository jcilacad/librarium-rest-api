package com.projects.reviewservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_gen")
    @SequenceGenerator(name = "reviews_gen", sequenceName = "reviews_seq", allocationSize = 1)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "rating", nullable = false)
    private int rating;

    private String comment;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private Instant dateCreated;

    @UpdateTimestamp
    @Column(name = "date_updated", nullable = false)
    private Instant dateUpdated;
}
