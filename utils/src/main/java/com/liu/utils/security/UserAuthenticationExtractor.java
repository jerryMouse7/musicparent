package com.liu.utils.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.liu.utils.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * 从
 * {@link org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices}
 * 创建的OAuth2Authentication中抽取用户相关信息
 *
 */
public class UserAuthenticationExtractor {

	private static final Logger logger = LoggerFactory
			.getLogger(UserAuthenticationExtractor.class);

	private static final String USER_EMAIL = "email";

	private static final String ID = "id";

	private static final String NAME = "name";

	private static final String TYPE = "type";

	/**
	 * 用户所拥有的班级资源
	 */
	private static final String CLAZZIDS = "clazzIds";

	/**
	 * 用户所拥有的学校资源
	 */
	private static final String COLLEGEIDS = "collegeIds";

	/**
	 * 用户所拥有的部门资源
	 */
	private static final String DEPARTMENTIDS = "departmentIds";

	/**
	 * 用户所拥有的院系资源
	 */
	private static final String INSTITUTEIDS = "instituteIds";

	public static PassportUser extract(Principal principal) {

		// OAuth2 登录
		if (principal instanceof OAuth2Authentication) {
			return UserAuthenticationExtractor
					.extract((OAuth2Authentication) principal);
		}

		// 用户名&密码 登录
		if (principal instanceof UsernamePasswordAuthenticationToken) {
			return UserAuthenticationExtractor
					.extract((UsernamePasswordAuthenticationToken) principal);
		}

		logger.debug("==> Principal: {}",
				principal.getClass().getCanonicalName());

		// Assert.isTrue(principal instanceof OAuth2Authentication);

		throw new IllegalArgumentException(
				"Principal must either UsernamePasswordAuthenticationToken or OAuth2Authentication");

	}

	public static PassportUser extract(
			UsernamePasswordAuthenticationToken principal) {

		if (principal.getPrincipal() instanceof UserExt) {
			UserExt userExt = (UserExt) principal.getPrincipal();
			return userExt.getPassportUser();
		}

		throw new IllegalArgumentException(
				"UsernamePasswordAuthenticationToken's Principal must be UserExt");
	}

	/**
	 *
	 * 从 {@link UserInfoTokenServices} 创建的OAuth2Authentication中抽取 用户信息
	 */
	public static PassportUser extract(OAuth2Authentication authentication) {

		PassportUser operator = new PassportUser();

		Authentication auth = authentication.getUserAuthentication();
		if (!auth.isAuthenticated()) {
			throw new RuntimeException("Not authenticated");
		}

		if (!(auth instanceof UsernamePasswordAuthenticationToken)) {
			throw new RuntimeException(
					"Authenticated not extracted by UserInfoTokenServices");
		}

		@SuppressWarnings("unchecked")
		Map<String, String> userInfo = (Map<String, String>) auth.getDetails();
		if (userInfo.containsKey(ID)) {
			operator.setId(Long.valueOf(userInfo.get(ID)));
		}

		if (userInfo.containsKey(NAME)) {
			operator.setName(userInfo.get(NAME));
		}

		if (userInfo.containsKey(USER_EMAIL)) {
			operator.setEmail(userInfo.get(USER_EMAIL));
		}

		if (userInfo.containsKey(TYPE)) {
			operator.setType(Integer.valueOf(userInfo.get(TYPE)));
		}
		// 把用户拥有的班级资源授予给用户
		if (userInfo.containsKey(CLAZZIDS)) {
			try {
				List<Long> clazzIds = JsonUtils.readJsonObject(
						userInfo.get(CLAZZIDS),
						new TypeReference<List<Long>>() {
						});
				operator.setClazzIds(clazzIds);
			} catch (Exception e) {
				logger.error("failed to get clazzIds");
			}

		}

		// 把用户拥有的学校资源授予给用户
		if (userInfo.containsKey(COLLEGEIDS)) {
			try {
				List<Long> collegeIds = JsonUtils.readJsonObject(
						userInfo.get(COLLEGEIDS),
						new TypeReference<List<Long>>() {
						});
				operator.setCollegeIds(collegeIds);
			} catch (Exception e) {
				logger.error("failed to get collegeIds");
			}
		}
		// 把用户拥有的部门资源授予给用户
		if (userInfo.containsKey(DEPARTMENTIDS)) {
			try {
				List<Long> departmentIds = JsonUtils.readJsonObject(
						userInfo.get(DEPARTMENTIDS),
						new TypeReference<List<Long>>() {
						});
				operator.setDepartmentIds(departmentIds);
			} catch (Exception e) {
				logger.error("failed to get departmentIds");
			}

		}

		//把用户拥有的院系资源授予给用户
		if (userInfo.containsKey(INSTITUTEIDS)) {
			try {
				List<Long> instituteIds = JsonUtils.readJsonObject(
						userInfo.get(INSTITUTEIDS),
						new TypeReference<List<Long>>() {
						});
				operator.setInstituteIds(instituteIds);
			} catch (Exception e) {
				logger.error("failed to get instituteIds");
			}
		}
		return operator;
	}
}
