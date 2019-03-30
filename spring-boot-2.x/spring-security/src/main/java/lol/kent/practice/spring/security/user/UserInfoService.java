package lol.kent.practice.spring.security.user;

import lol.kent.practice.spring.security.user.domain.CustomUser;
import lol.kent.practice.spring.security.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月26日 19:32
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version x.x.x
 */
@Service()
public class UserInfoService implements UserDetailsService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    private final List<GrantedAuthority> roles = new ArrayList<>();

    /**
     * @param userAccount :用户帐号，手机，邮箱或其他一切能唯一认定用户的主键
     */
    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {

        CustomUser user = userMapper.selectByUserName(userAccount);

        // GET USER FROM DB, Then wired userInfo to org.springframework.security.core.userdetails UserClass;
        return new User(user.getName(), user.getPassword(), roles);
    }
}
