package cn.tuyucheng.loginextrafieldscustom;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Collection;

public class User extends org.springframework.security.core.userdetails.User {

	@Serial
	private static final long serialVersionUID = 1L;

	private final String domain;

	public User(String username, String domain, String password, boolean enabled,
				boolean accountNonExpired, boolean credentialsNonExpired,
				boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.domain = domain;
	}

	public String getDomain() {
		return domain;
	}
}