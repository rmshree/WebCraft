package app.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @Column(name = "win")
    @JsonProperty
    private Integer win;

    @Column(name = "loss")
    @JsonProperty
    private Integer loss;

    @Column(name = "name")
    @JsonProperty
    private String name;

    public Integer getId() {
        return this.id;
    }

    public void setScore(Integer winScore, Integer lossScore) {
        this.win += winScore;
        this.loss += lossScore;
    }

    public void getScore(Integer winScore, Integer lossScore) {
        this.win += winScore;
        this.loss += lossScore;
    }

    public boolean isDuplicate(Integer id, String username) {
        if (username.equals(this.name)) {
            return true;
        } else {
            return false;
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}