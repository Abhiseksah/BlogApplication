package io.mountblue.repository;

import io.mountblue.models.Post;
import io.mountblue.models.Post_tag;
import io.mountblue.models.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomPostRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Page<Post> findFilteredPosts(Long authorId, List<Long> tagIds, Boolean isPublished,
                                        LocalDateTime startDate, LocalDateTime endDate, Pageable pageable,
                                        String sortBy, String sortDirection) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> query = cb.createQuery(Post.class);
        Root<Post> root = query.from(Post.class);

        List<Predicate> predicates = new ArrayList<>();

        // 🔹 Filter by Author
        if (authorId != null) {
            predicates.add(cb.equal(root.get("author").get("id"), authorId));
        }

        // 🔹 Filter by Tags (Fix Join Issue)
        if (tagIds != null && !tagIds.isEmpty()) {
            Join<Post, Post_tag> postTagJoin = root.join("postTags", JoinType.INNER);
            Join<Post_tag, Tag> tagJoin = postTagJoin.join("tag", JoinType.INNER); // ✅ Fix here
            predicates.add(tagJoin.get("id").in(tagIds));
        }

        // 🔹 Filter by Publication Status
        if (isPublished != null) {
            predicates.add(cb.equal(root.get("is_published"), isPublished));
        }

        // 🔹 Filter by Date Range
        if (startDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("published_at"), startDate));
        }
        if (endDate != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("published_at"), endDate));
        }

        // 🔹 Apply Filters
        query.where(cb.and(predicates.toArray(new Predicate[0])));

        // 🔹 Sorting
        if (sortBy != null) {
            Path<?> sortField = root.get(sortBy);
            if ("desc".equalsIgnoreCase(sortDirection)) {
                query.orderBy(cb.desc(sortField));
            } else {
                query.orderBy(cb.asc(sortField));
            }
        }

        // 🔹 Pagination
        TypedQuery<Post> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        // 🔹 Count Query for Pagination (Fix Join Issue)
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Post> countRoot = countQuery.from(Post.class);
        countQuery.select(cb.count(countRoot));

        List<Predicate> countPredicates = new ArrayList<>(predicates);

        if (tagIds != null && !tagIds.isEmpty()) {
            Join<Post, Post_tag> countPostTagJoin = countRoot.join("postTags", JoinType.INNER);
            Join<Post_tag, Tag> countTagJoin = countPostTagJoin.join("tag", JoinType.INNER);
            countPredicates.add(countTagJoin.get("id").in(tagIds));
        }

        countQuery.where(cb.and(countPredicates.toArray(new Predicate[0])));
        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(typedQuery.getResultList(), pageable, totalCount);
    }
}
