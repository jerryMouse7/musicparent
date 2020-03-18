package com.liu.utils.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 扩展@User，添加附加信息，比如用户所在公司，可访问的广告主，可访问的资源等等？
 */
public class UserExt extends User {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;;

	private PassportUser passportUser;

	public UserExt(String username, String password,
                   boolean enabled, boolean accountNonExpired,
                   boolean credentialsNonExpired, boolean accountNonLocked,
                   Collection<? extends GrantedAuthority> authorities, PassportUser info) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		this.passportUser = info;
	}

	public UserExt(String username, String password,
                   Collection<? extends GrantedAuthority> authorities, PassportUser info) {
		this(username, password, true, true, true, true, authorities, info);
	}

	public PassportUser getPassportUser() {
		return passportUser;
	}

	public void setPassportUser(PassportUser passportUser) {
		this.passportUser = passportUser;
	}



}
