package hr.tvz.miholic.hardwareapp.Review.Controller;

import hr.tvz.miholic.hardwareapp.Review.Classes.Review;
import hr.tvz.miholic.hardwareapp.Review.Classes.ReviewDTO;
import hr.tvz.miholic.hardwareapp.Review.Service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")

public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public List<ReviewDTO> getAllCourses(){
        return reviewService.findAll();
    }
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(params = "code")
    public List<ReviewDTO> getAllReviewsByHardwareCode(@RequestParam String code){
        return reviewService.findAllByHardwareCode(code);
    }
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(params = "title")
    public List<ReviewDTO> findByTitle(@RequestParam String title){
        return reviewService.findByTitle(title);
    }


}