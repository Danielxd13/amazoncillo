package es.storeapp.business.repositories;

import es.storeapp.business.entities.Category;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends AbstractRepository<Category> {

    private static final String FIND_HIGHLIGHTED_QUERY = "SELECT c FROM Category c WHERE c.highlighted = ?";

    public List<Category> findHighlighted(boolean highlighted) {
        Query query = entityManager.createQuery(FIND_HIGHLIGHTED_QUERY);
        query.setParameter(1, highlighted);
        return (List<Category>) query.getResultList();
    }

}
