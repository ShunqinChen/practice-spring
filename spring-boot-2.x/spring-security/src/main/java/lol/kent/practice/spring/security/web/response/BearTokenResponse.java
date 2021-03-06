package lol.kent.practice.spring.security.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月26日 15:48
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version x.x.x
 */
public class BearTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private long expiresIn;

    @JsonProperty("refresh_token")
    private long refreshToken;

    public BearTokenResponse() {

    }


    public BearTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(long refreshToken) {
        this.refreshToken = refreshToken;
    }
}
