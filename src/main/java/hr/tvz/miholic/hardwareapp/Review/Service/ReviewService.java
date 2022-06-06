package hr.tvz.miholic.hardwareapp.Review.Service;

import hr.tvz.miholic.hardwareapp.Review.Classes.Review;
import hr.tvz.miholic.hardwareapp.Review.Classes.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> findAll();

    List<ReviewDTO> findAllByHardwareCode(String code);
    List<ReviewDTO> findByTitle(String title);
    List<Review> findFullByTitle(String title);
}