package lol.kent.practice.spring.config;

import lombok.Data;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年01月11日 20:10
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Data
public class AppInfo {

    private String key;

    private String secret;

    public AppInfo() {
    }

    public AppInfo(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }
}
