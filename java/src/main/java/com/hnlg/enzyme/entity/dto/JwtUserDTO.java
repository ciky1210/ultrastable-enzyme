package com.hnlg.enzyme.entity.dto;


import lombok.Data;

/**
 * jwt 用户
 */
@Data
public class JwtUserDTO {

    /**
     * 用户ID
     */
    private long userId;
    /**
     * 用户名
     */
    private String nickName;

    /**
     * token
     */
    private String token;


    public JwtUserDTO() {
    }

    public JwtUserDTO(long userId, String nickName,String token) {
        this.userId = userId;
        this.nickName = nickName;
        this.token = token;
    }

}
