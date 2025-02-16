//package io.mountblue.controller;
//
//import io.mountblue.models.Post;
//import io.mountblue.models.Post_tag;
//import io.mountblue.models.Tag;
//import jakarta.persistence.criteria.Join;
//import jakarta.persistence.criteria.JoinType;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class PostSpecification {
//
//    public static Specification<Post> containsKeyword(String keyword) {
//        return (root, query, criteriaBuilder) -> {
//            if (keyword == null || keyword.trim().isEmpty()) {
//                return null;
//            }
//            String pattern = "%" + keyword + "%";
//            return criteriaBuilder.or(
//                    criteriaBuilder.like(root.get("title"), pattern),
//                    criteriaBuilder.like(root.get("tags"), pattern),
//                    criteriaBuilder.like(root.get("content"), pattern),
//                    criteriaBuilder.like(root.get("excerpt"), pattern)
//            );
//        };
//    }
//
//    public static Specification<Post> hasTags(List<Long> tagIds) {
//        return (root, query, criteriaBuilder) -> {
//            if (tagIds == null || tagIds.isEmpty()) {
//                return null;
//            }
//
//            // Join postTags table (Post -> Post_tag)
//            Join<Post, Post_tag> postTagJoin = root.join("postTags", JoinType.INNER);
//
//            // Join tags table through postTags (Post_tag -> Tag)
//            Join<Post_tag, Tag> tagJoin = postTagJoin.join("tag", JoinType.INNER);
//
//            // Filter posts where the tag ID is in the given list
//            return tagJoin.get("id").in(tagIds);
//        };
//    }
//
//    public static Specification<Post> hasAuthors(List<String> authors) {
//        return (root, query, criteriaBuilder) -> {
//            if (authors == null || authors.isEmpty()) {
//                return null;
//            }
//            return root.get("author").in(authors);
//        };
//    }
//
//    public static Specification<Post> isPublished(Boolean isPublished) {
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.equal(root.get("is_published"), isPublished);
//    }
//
//    public static Specification<Post> isWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
//        return (root, query, criteriaBuilder) -> {
//            if (startDate == null && endDate == null) {
//                return null;
//            } else if (startDate != null && endDate != null) {
//                return criteriaBuilder.between(root.get("published_at"), startDate, endDate);
//            } else if (startDate != null) {
//                return criteriaBuilder.greaterThanOrEqualTo(root.get("published_at"), startDate);
//            } else {
//                return criteriaBuilder.lessThanOrEqualTo(root.get("published_at"), endDate);
//            }
//        };
//    }
//}
