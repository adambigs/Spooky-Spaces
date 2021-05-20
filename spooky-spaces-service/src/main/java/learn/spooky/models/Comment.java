package learn.spooky.models;

import javax.validation.constraints.*;

public class Comment {

    @Min(value = 1, message = "Comment Id Must be greater than zero")
    private int commentId;

    @NotBlank
    private String username;

    @Min(value = 1, message = "Rating must be a number 1 - 5")
    @Max(value = 5, message = "Rating must be a number 1 - 5")
    private int rating;

    @NotBlank(message = "Description can not be blank")
    private String description;

    @Min(value = 1)
    private int encounterId;

    public Comment(){};

    public Comment(@Min(value = 0, message = "Comment Id Must be greater than zero") int commentId, @NotBlank String username, @Min(value = 1, message = "Rating must be a number 1 - 5") @Max(value = 5, message = "Rating must be a number 1 - 5") int rating, @NotBlank(message = "Description can not be blank") String description, @Min(value = 1) int encounterId) {
        this.commentId = commentId;
        this.username = username;
        this.rating = rating;
        this.description = description;
        this.encounterId = encounterId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(int encounterId) {
        this.encounterId = encounterId;
    }
}
