package com.trainbooking.dao.impl;

import com.trainbooking.dao.UserDao;
import com.trainbooking.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDaoImpl extends DaoImpl<User> implements UserDao {
    private final transient Logger logger = LoggerFactory.getLogger(getClass());

    public UserDaoImpl() {
        super(User.class);
    }

    /**
     * Find user by login
     *
     * @param login login value
     * @return      entity {@link User}
     */
    @Override
    public User findByLogin(String login) {
        User user;
        try {
            user = entityManager.createQuery("select u from User u where u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
        return user;
    }
}
