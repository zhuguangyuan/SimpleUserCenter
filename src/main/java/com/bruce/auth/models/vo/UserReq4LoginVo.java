package com.bruce.auth.models.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class UserReq4LoginVo {
    @ApiModelProperty(value = "userName", required = true, example = "bruce")
    private String name;
    @ApiModelProperty(value = "userPassword", required = true, example = "b28#192A@")
    private String password;
}
