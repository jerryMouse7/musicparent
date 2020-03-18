package com.liu.passport.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 自动授权
 */
public class AutoUserApprovalHandler extends ApprovalStoreUserApprovalHandler {

	private boolean useApprovalStore = true;

	private ClientDetailsService clientDetailsService;

	/**
	 * Service to load client details (optional) for auto approval checks.
	 *
	 * @param clientDetailsService
	 *            a client details service
	 */
	public void setClientDetailsService(ClientDetailsService clientDetailsService) {
		this.clientDetailsService = clientDetailsService;
		super.setClientDetailsService(clientDetailsService);
	}

	/**
	 * @param useApprovalStore
	 *            the useTokenServices to set
	 */
	public void setUseApprovalStore(boolean useApprovalStore) {
		this.useApprovalStore = useApprovalStore;
	}

	/**
	 * 对白名单应用执行scope自动授权
	 *
	 * Allows automatic approval for a white list of clients in the implicit grant
	 * case.
	 *
	 * @param authorizationRequest
	 *            The authorization request.
	 * @param userAuthentication
	 *            the current user authentication
	 *
	 * @return An updated request if it has already been approved by the current
	 *         user.
	 */
	@Override
	public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest,
                                                    Authentication userAuthentication) {

		boolean approved = false;
		// If we are allowed to check existing approvals this will short circuit
		// the decision
		if (useApprovalStore) {
			authorizationRequest = super.checkForPreApproval(authorizationRequest, userAuthentication);
			approved = authorizationRequest.isApproved();
		} else {
			if (clientDetailsService != null) {
				Collection<String> requestedScopes = authorizationRequest.getScope();
				try {
					ClientDetails client = clientDetailsService
							.loadClientByClientId(authorizationRequest.getClientId());
					for (String scope : requestedScopes) {
						if (client.isAutoApprove(scope)) {
							// 对某些scope可以自动授权
							approved = true;
							break;
						}
					}
				} catch (ClientRegistrationException e) {
				}
			}
		}
		authorizationRequest.setApproved(approved);

		return authorizationRequest;

	}
}
