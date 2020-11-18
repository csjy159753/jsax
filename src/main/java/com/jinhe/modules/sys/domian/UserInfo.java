package com.jinhe.modules.sys.domian;

import com.jinhe.config.ResultEnum;

/**
 * @author Administrator
 */
public class UserInfo {
    public static final String PATTEN = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{10,}$";
    private int NormalizedUsernameLength = 3;

    public boolean CheckPassword(String passwordHash) {
        boolean flag = passwordHash != null
                || "".equals(passwordHash.trim())
                || passwordHash.contains("012")
                || passwordHash.contains("123")
                || passwordHash.contains("234")
                || passwordHash.contains("345")
                || passwordHash.contains("456")
                || passwordHash.contains("567")
                || passwordHash.contains("678")
                || passwordHash.contains("789")
                || passwordHash.contains("987")
                || passwordHash.contains("876")
                || passwordHash.contains("765")
                || passwordHash.contains("654")
                || passwordHash.contains("543")
                || passwordHash.contains("432")
                || passwordHash.contains("321")
                || passwordHash.contains("210");
        if (flag || !passwordHash.matches(PATTEN)) {
            return false;
        }
        return true;
    }

    public ResultEnum NormalizedUsername(String NormalizedUsername) {
        if ((NormalizedUsername == null || "".equals(NormalizedUsername.trim()))) {
            return ResultEnum.USER_ACCOUNT_OR_PASSWORD_ISNULL;
        }
        if (NormalizedUsername.length() >= NormalizedUsernameLength) {
            return ResultEnum.USER_NAME_LENGTH_ERROR;
        }
        return ResultEnum.USER_NAME_CORRECT;
    }
}
