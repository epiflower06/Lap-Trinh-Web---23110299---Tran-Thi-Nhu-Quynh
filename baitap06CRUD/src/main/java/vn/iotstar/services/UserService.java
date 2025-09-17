package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.User;

public interface UserService {
	List<User> findAll();
    User findById(int id);
	void create(User u);
	User findByUsername(String username);
    
    void update(User user);
    void delete(int id);
}
