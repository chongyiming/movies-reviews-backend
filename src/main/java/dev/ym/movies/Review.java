package dev.ym.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    private ObjectId id;
    private String body;

    public Review(String body) {
        this.body = body;
    }

    public Review(ObjectId id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }
}
