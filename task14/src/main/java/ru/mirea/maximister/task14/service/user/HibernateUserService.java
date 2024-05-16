package ru.mirea.maximister.task14.service.user;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.mirea.maximister.task14.model.domain.Post;
import ru.mirea.maximister.task14.model.domain.User;
import ru.mirea.maximister.task14.model.dto.AddUserRequest;
import ru.mirea.maximister.task14.model.dto.PostResponse;
import ru.mirea.maximister.task14.model.dto.RemoveUserRequest;
import ru.mirea.maximister.task14.model.dto.UserResponse;
import ru.mirea.maximister.task14.service.mappers.PostToPostResponseMapper;
import ru.mirea.maximister.task14.service.mappers.UserToUserResponseMapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
public class HibernateUserService implements UserService {
    private final SessionFactory sessionFactory;
    private Session session;
    public HibernateUserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    @Override
    public List<UserResponse> getUsers() {
        log.info("getting all users");
        return session.createQuery("select u from User u", User.class)
                .getResultList().stream().map(UserToUserResponseMapper::userToUserResponse).toList();
    }

    @Override
    public UserResponse getUser(Long id) {
        log.info("getting user with id {}", id);
        User user = session.get(User.class, id);
        if (user == null) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        return UserToUserResponseMapper.userToUserResponse(user);
    }

    @Override
    public void addUser(AddUserRequest addUserRequest) {
        User user = new User(
                addUserRequest.firstName(),
                addUserRequest.lastName(),
                addUserRequest.middleName(),
                addUserRequest.birthDate()
        );

        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        log.info("added new user {}", user);
    }

    @Override
    public void deleteUser(RemoveUserRequest request) {
        User user = session.get(User.class, request.id());
        if (user == null) {
            throw new RuntimeException("User with id " + request.id() + " not found");
        }

        session.beginTransaction();
        session.remove(user);
        session.getTransaction().commit();
        log.info("deleted user {}", request);
    }

    @Override
    public List<PostResponse> getPosts(Long userId) {
        log.info("getting posts from user {}", userId);
        User user = session.get(User.class, userId);
        if (user == null) {
            throw new RuntimeException("User with id " + userId + " not found");
        }
        return user.getPosts().stream().map(PostToPostResponseMapper::postToPostResponse).toList();
    }

    @Override
    public void addPostToUser(Long userId, Long postId) {
        log.info("adding post {} to user {}", postId, userId);
        User user = session.get(User.class, userId);
        if (user == null) {
            throw new RuntimeException("User with id " + userId + " not found");
        }
        Post post = session.get(Post.class, postId);
        if (post == null) {
            throw new RuntimeException("post with id " + postId + " not found");
        }
        session.beginTransaction();
        user.getPosts().add(post);
        post.setUser(user);
        session.merge(user);
        session.getTransaction().commit();
    }

    @Override
    public List<UserResponse> getUsersByFilter(String filteredBy, String value) {
        log.info("getting users by filter {} and value {}", filteredBy, value);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(User.class);
        var root = criteria.from(User.class);

        if (filteredBy.equals("birthDate")) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = null;
            try {
                date = format.parse(value);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            criteria.select(root).where(
                    builder.greaterThanOrEqualTo(root.get(filteredBy), date)
            );
        } else {
            criteria.select(root).where(
                    builder.greaterThanOrEqualTo(root.get(filteredBy), value)
            );
        }

        session.beginTransaction();
        var users = session.createQuery(criteria)
                .getResultList()
                .stream()
                .map(UserToUserResponseMapper::userToUserResponse)
                .toList();
        session.getTransaction().commit();
        return users;
    }
}
