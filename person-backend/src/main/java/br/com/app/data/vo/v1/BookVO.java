package br.com.app.data.vo.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({ "id", "author", "launch_date", "price", "title" }) // Ordem dos atributos no JSON de output
public class BookVO extends RepresentationModel<BookVO> implements Serializable { // RepresentationModel - suporte a hateoas

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Mapping("id")
    private Long key;

    private String author;

    @JsonProperty("launch_date") // Modifica o nome do atributo
    private Instant launchDate;

    private BigDecimal price;

    private String title;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Instant getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Instant launchDate) {
        this.launchDate = launchDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((launchDate == null) ? 0 : launchDate.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        BookVO other = (BookVO) obj;
        if (author == null) {
            if (other.author != null) return false;
        } else if (!author.equals(other.author)) return false;
        if (key == null) {
            if (other.key != null) return false;
        } else if (!key.equals(other.key)) return false;
        if (launchDate == null) {
            if (other.launchDate != null) return false;
        } else if (!launchDate.equals(other.launchDate)) return false;
        if (price == null) {
            if (other.price != null) return false;
        } else if (!price.equals(other.price)) return false;
        if (title == null) {
            if (other.title != null) return false;
        } else if (!title.equals(other.title)) return false;
        return true;
    }

}
