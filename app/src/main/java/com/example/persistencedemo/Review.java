package com.example.persistencedemo;

public class Review {
    private String review;
    private int rating;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Review(String review, int rating) {
        this.review = review;
        this.rating = rating;
    }
}
