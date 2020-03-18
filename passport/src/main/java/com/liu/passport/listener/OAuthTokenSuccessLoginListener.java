package com.liu.passport.listener;

import com.liu.passport.entity.User;
import com.liu.passport.entity.UserLoginHistory;
import com.liu.passport.enums.UserDef;
import com.liu.passport.service.UserLoginHistoryService;
import com.liu.passport.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Order
public class OAuthTokenSuccessLoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

	private static final Logger logger = LoggerFactory.getLogger(OAuthTokenSuccessLoginListener.class);

	@Autowired
	private UserService userService;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserLoginHistoryService userLoginHistoryService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		// not a oath/token generate request skip.
		if (Objects.isNull(attr) || !StringUtils.endsWith(attr.getRequest().getRequestURI(), "/oauth/token")) {
			return;
		}

		// process user login
		if (event.getAuthentication() instanceof UsernamePasswordAuthenticationToken) {

			UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) event.getAuthentication();

			if (auth.getDetails() instanceof LinkedHashMap) {
				// 一个账号只允许登录一次 重新登录后需要清空已有的登录信息
				clearUserExistedAccessToken(auth);

				// 记录用户登录历史
				saveLoginHistory(attr, auth);
			}
		}

	}

	/**
	 * 清除用户已存在的Token
	 *
	 * @param auth
	 */
	private void clearUserExistedAccessToken(UsernamePasswordAuthenticationToken auth) {

		Object object = auth.getDetails();

		@SuppressWarnings("unchecked")
		Map<String, String> details = (Map<String, String>) auth.getDetails();

		String clientId = details.get("client_id");
		String username = details.get("username");

		// 根据OAuth client_id和username(用户登录名)查询已在使用的Token信息
		Collection<OAuth2AccessToken> accessTokens = tokenStore.findTokensByClientIdAndUserName(clientId, username);

		// 删除已在使用的Token
		accessTokens.stream().forEach(accessToken -> {
			tokenStore.removeAccessToken(accessToken);
		});
	}

	/**
	 * 保存用户登录历史记录
	 *
	 * @param attr
	 * @param auth
	 */
	private void saveLoginHistory(ServletRequestAttributes attr, UsernamePasswordAuthenticationToken auth) {
		try {

//			User user = userService.findByAccountNameAndState(auth.getName(), UserDef.State.NORMAL.getCode())
//					.orElseThrow(() -> new RuntimeException("User[]" + auth.getName() + "not found."));
			User user = userService.findByAccountNameAndState(auth.getName(), UserDef.State.NORMAL.getCode());
			UserLoginHistory history = new UserLoginHistory();
			history.setUserId(user.getId());
			history.setUserAgent(attr.getRequest().getHeader("User-Agent"));

			// fixed apache proxy missing client real ip. Using apache
			// X-Forwarded-For preserve client ip.
			String[] addrs = StringUtils.split(attr.getRequest().getHeader("X-Forwarded-For"), ',');
			String addr = "";
			if (null != addrs && addrs.length > 0) {
				addr = StringUtils.trim(addrs[0]);
			}

			// Can not get client remote ip from above methods, get it from
			// request remote addr
			if (StringUtils.isBlank(addr)) {
				addr = attr.getRequest().getRemoteAddr();
			}

			history.setRemoteAddr(addr);

			// user cookies
			Cookie[] cookies = attr.getRequest().getCookies();
			if (null != cookies) {

				StringBuilder buff = new StringBuilder();

				int index = 0;
				for (Cookie cookie : cookies) {

					if (index > 0) {
						buff.append("; ");
					}

					buff.append(cookie.getName()).append("=").append(cookie.getValue());
				}

				history.setCookies(buff.toString());
			}

			history.setLoginTime(LocalDateTime.now());

			userLoginHistoryService.insertUserLoginHistory(history);
		} catch (Exception e) {
			logger.error("Save user login history failed, caused by:", e);
		}
	}

}
