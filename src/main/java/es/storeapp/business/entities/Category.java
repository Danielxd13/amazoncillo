package es.storeapp.business.entities;

import es.storeapp.common.Constants;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = Constants.CATEGORY_ENTITY)
@Table(name = Constants.CATEGORIES_TABLE)
public class Category implements Serializable {

    private static final long serialVersionUID = 340618567236100110L;

    @Id
    private Long categoryId;

    @Column(name = "name", nullable = false, unique = true)
    @NotNull
    private String name;

    @Column(name = "description", nullable = false)
    @Size(max = 255)
    private String description;

    @Column(name = "icon", nullable = false)
    @Pattern(regexp = "^https?://.*$")
    @NotNull
    private String icon;

    @Column(name = "highlighted")
    private boolean highlighted;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    @Override
    public String toString() {
        return String.format("Category{categoryId=%s, name=%s, description=%s, icon=%s, highlighted=%s}",
                categoryId, name, description, icon, highlighted);
    }

    class InvalidNameException extends Exception {

        public InvalidNameException(String message) {
            super(message);
        }
    }

    class InvalidDescriptionException extends Exception {

        public InvalidDescriptionException(String message) {
            super(message);
        }
    }

    class InvalidIconException extends Exception {

        public InvalidIconException(String message) {
            super(message);

        }
    }
}
