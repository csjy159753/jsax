package com.jinhe.common.config;

/**
 * @author Administrator
 */

public class SystemType {
    public static String TYPE = "LOGIN_TYPE";
    public static String USER_ID = "USER_ID";
    public static String USER_NAME = "USER_NAME";
    public static String prod = "prod";
    public static String identityId = "identityId";

    public enum LoginType {
        USER("USER"),
        KEY_AUTHORIZATION("KEY_AUTHORIZATION"),
        SSO("SSO");

        public final String value;

        private LoginType(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
