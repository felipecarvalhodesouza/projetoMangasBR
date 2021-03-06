package com.felipecarvalho.projetoMangasBR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Review;
import com.felipecarvalho.projetoMangasBR.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repo;
	
	public List<Review> findByTitle(Integer titleId){
		return repo.findReviews(titleId);
	}
	
	public Review saveReview(Review review) {
		return repo.save(review);
	}

	public void removeReview(Review review) {
		repo.deleteById(review.getId());
	}

}
