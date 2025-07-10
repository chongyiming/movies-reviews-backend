package dev.ym.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        Review review = reviewRepository.insert(new Review(reviewBody));
        mongoTemplate.update(Movie.class).matching(Criteria.where("imdbId").is(imdbId)).apply(new Update().push("reviewIds").value(review)).first();
        return review;
    }

    public List<String> movieReviews(String imdbId) {
        Optional<Movie> movie = movieRepository.findMovieByImdbId(imdbId);
        List<String> reviewBodies = new ArrayList<>();

        if (movie.isPresent()) {
            List<Review> reviews = movie.get().getReviewIds(); // review objects

            for (Review review : reviews) {
                reviewBodies.add(review.getBody());
            }
        }

        return reviewBodies;
    }







}
