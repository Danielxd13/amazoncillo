package es.storeapp.business.entities;

import es.storeapp.common.Constants;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = Constants.COMMENT_ENTITY)
@Table(name = Constants.COMMENTS_TABLE)
public class Comment implements Serializable {

    private static final long serialVersionUID = -8821440815912953976L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "text", nullable = false)
    @NotNull
    private String text;

    @Column(name = "rating", nullable = false)
    @Min(1)
    @Max(5)
    private Integer rating;

    @Column(name = "timestamp", nullable = false)
    @Column(type = Timestamp)
    @NotNull
    private Long timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) throws InvalidCommentTextException {
        if (text == null || text.isEmpty()) {
            throw new InvalidCommentTextException("El texto del comentario es obligatorio.");
        }
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) throws InvalidRatingException {
        if (rating < 1 || rating > 5) {
            throw new InvalidRatingException("La puntuación debe estar entre 1 y 5.");
        }
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("Comment{commentId=%s, text=%s, rating=%s, timestamp=%s, user=%s, product=%s}",
                commentId, text, rating, timestamp, user, product);
    }

    class InvalidCommentTextException extends Exception {

        public InvalidCommentTextException(String message) {
            super(message);
        }
    }

    class InvalidRatingException extends Exception {

        public InvalidRatingException(String message) {
            super(message);
        }
    }
}
