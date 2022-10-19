package cn.tuyucheng.loginextrafieldscustom;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository("userRepository")
public class CustomUserRepository implements UserRepository {

    private final PasswordEncoder passwordEncoder;

    public CustomUserRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUser(String username, String domain) {
        if (StringUtils.isAnyBlank(username, domain)) {
            return null;
        } else {
            Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
            return new User(username, domain, passwordEncoder.encode("secret"),
                    true, true, true, true, authorities);
        }
    }
}