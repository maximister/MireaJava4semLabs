package ru.mirea.maximister.task14.Configuration;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mirea.maximister.task14.domain.User;
import ru.mirea.maximister.task14.repository.post.InMemoryPostRepo;
import ru.mirea.maximister.task14.repository.post.JpaPostRepository;
import ru.mirea.maximister.task14.repository.post.PostRepository;
import ru.mirea.maximister.task14.repository.user.InMemoryUserRepo;
import ru.mirea.maximister.task14.repository.user.JpaUserRepository;
import ru.mirea.maximister.task14.repository.user.UserRepository;
import ru.mirea.maximister.task14.service.post.JpaPostService;
import ru.mirea.maximister.task14.service.user.HibernateUserService;
import ru.mirea.maximister.task14.service.post.HibernatePostService;
import ru.mirea.maximister.task14.service.post.InMemoryPostService;
import ru.mirea.maximister.task14.service.post.PostService;
import ru.mirea.maximister.task14.service.user.InMemoryUserService;
import ru.mirea.maximister.task14.service.user.JpaUserService;
import ru.mirea.maximister.task14.service.user.UserService;

@Configuration
public class ServiceConfiguration {
    @Bean
    @ConditionalOnProperty(name = "db-type", havingValue = "jpa")
    public PostService jpaPostService(
            JpaPostRepository addressRepository
    ) {
        return new JpaPostService(addressRepository);
    }

    @Bean
    @ConditionalOnProperty(name = "db-type", havingValue = "jpa")
    public UserService jpaUserService(JpaUserRepository userRepository, JpaPostRepository postRepository) {
        return new JpaUserService(userRepository, postRepository);
    }

    @Bean
    @ConditionalOnProperty(name = "db-type", havingValue = "hibernate")
    public PostService hibernatePostService(SessionFactory sessionFactory) {
        return new HibernatePostService(sessionFactory);
    }

    @Bean
    @ConditionalOnProperty(name = "db-type", havingValue = "hibernate")
    public UserService hibernateUserService(SessionFactory sessionFactory) {
        return new HibernateUserService(sessionFactory);
    }

    @Bean
    @ConditionalOnProperty(name = "db-type", havingValue = "in-memory")
    public PostService InMemoryPostService(PostRepository postRepository) {
        return new InMemoryPostService(postRepository);
    }

    @Bean
    @ConditionalOnProperty(name = "db-type", havingValue = "in-memory")
    public UserService InMemoryUserService(UserRepository userRepository) {
        return new InMemoryUserService(userRepository);
    }

    @Bean
    @ConditionalOnProperty(name = "db-type", havingValue = "in-memory")
    public PostRepository InMemoryPostRepository() {
        return new InMemoryPostRepo();
    }

    @Bean
    @ConditionalOnProperty(name = "db-type", havingValue = "in-memory")
    public UserRepository InMemoryUserRepository() {
        return new InMemoryUserRepo();
    }
}
