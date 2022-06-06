package hr.tvz.miholic.hardwareapp.Review.Repository;

import hr.tvz.miholic.hardwareapp.Review.Classes.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByHardwareCode(String code);



    @Query("select r from Review r where LOWER(r.title) LIKE LOWER(concat('%', concat(?1, '%')))")
    List<Review> findByTitle(String title);



}