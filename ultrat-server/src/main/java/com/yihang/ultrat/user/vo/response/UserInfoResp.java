package com.yihang.ultrat.user.vo.response;

import lombok.Data;

@Data
public class UserInfoResp {
    private Long id;

    private String name;

    private String avatar;

    private Integer sex;

    private Integer modifyNameChance;
}
