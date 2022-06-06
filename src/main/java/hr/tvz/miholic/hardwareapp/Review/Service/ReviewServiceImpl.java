package hr.tvz.miholic.hardwareapp.Review.Service;

import hr.tvz.miholic.hardwareapp.Review.Classes.Review;
import hr.tvz.miholic.hardwareapp.Review.Classes.ReviewDTO;
import hr.tvz.miholic.hardwareapp.Review.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findAllByHardwareCode(String code) {

        return reviewRepository.findAllByHardwareCode(code).stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByTitle(String title) {
        return reviewRepository.findByTitle(title).stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<Review> findFullByTitle(String title) {
        return reviewRepository.findByTitle(title).stream().collect(Collectors.toList());
    }

    private ReviewDTO mapReviewToDTO(Review review){
        return new ReviewDTO(review.getTitle(), review.getText(), review.getScore());
    }
}
