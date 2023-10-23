package jenakxm.RestaurantReview.controller;

import jenakxm.RestaurantReview.domain.Review;
import jenakxm.RestaurantReview.dto.AddReviewRequest;
import jenakxm.RestaurantReview.dto.ReviewResponse;
import jenakxm.RestaurantReview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {
    private final ReviewService reviewService;

    @PostMapping("/api/reviews")
    public ResponseEntity<Review> addReview(@RequestBody AddReviewRequest request) {
        Review savedReview = reviewService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    @GetMapping("/api/reviews")
    public ResponseEntity<List<ReviewResponse>> findAllReviews() {
        List<ReviewResponse> reviews = reviewService.findAll()
                .stream()
                .map(ReviewResponse::new)
                .toList();

        return ResponseEntity.ok().body(reviews);

    }
}