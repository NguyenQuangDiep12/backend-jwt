package com.np.shopee.service.user;

import com.np.shopee.model.User;
import com.np.shopee.request.CreateUserRequest;
import com.np.shopee.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);

    User createUser(CreateUserRequest request);

    User updateUser(UserUpdateRequest request, Long userId);

    void deleteUser(Long userId);
}
