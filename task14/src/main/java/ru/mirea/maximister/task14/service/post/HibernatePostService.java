package ru.mirea.maximister.task14.service.post;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.mirea.maximister.task14.model.domain.Post;
import ru.mirea.maximister.task14.model.dto.AddPostRequest;
import ru.mirea.maximister.task14.model.dto.PostResponse;
import ru.mirea.maximister.task14.model.dto.RemovePostRequest;
import ru.mirea.maximister.task14.service.mappers.PostToPostResponseMapper;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
public class HibernatePostService implements PostService {
    private final SessionFactory sessionFactory;
    private Session session;

    public HibernatePostService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    @Override
    public List<PostResponse> getPosts() {
        log.info("getting all posts");
        return session.createQuery("select p from Post p", Post.class)
                .getResultList().stream().map(PostToPostResponseMapper::postToPostResponse).toList();
    }

    @Override
    public PostResponse getPost(Long id) {
        Post post = session.get(Post.class, id);
        if (post == null) {
            log.warn("tried to find post with id {}", id);
            throw new RuntimeException("Patient with id " + id + " not found");
        }
        log.info("getting post with id {}", id);
        return PostToPostResponseMapper.postToPostResponse(post);
    }

    @Override
    public void addPost(AddPostRequest addPostRequest) {
        Post post = new Post(
               addPostRequest.text(),
                OffsetDateTime.now()
        );

        session.beginTransaction();
        session.persist(post);
        session.getTransaction().commit();

        log.info("added new post {}", post);
    }

    @Override
    public void deletePost(RemovePostRequest request) {
        Post post = session.get(Post.class, request.id());
        if (post == null) {
            log.warn("tried to delete post {}", request);
            throw new RuntimeException("User with id " + request.id() + " not found");
        }

        session.beginTransaction();
        session.remove(post);
        session.getTransaction().commit();

        log.info("deleted post {}", post);
    }

    @Override
    public List<PostResponse> getPostsByFilter(String filteredBy, String value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Post.class);
        var root = criteria.from(Post.class);

        criteria.select(root).where(
                builder.greaterThanOrEqualTo(root.get(filteredBy), value)
        );

        session.beginTransaction();
        var posts = session.createQuery(criteria)
                .getResultList()
                .stream()
                .map(PostToPostResponseMapper::postToPostResponse)
                .toList();
        session.getTransaction().commit();

        log.info("getting posts by filter {} and value {}", filteredBy, value);
        return posts;
    }
}
