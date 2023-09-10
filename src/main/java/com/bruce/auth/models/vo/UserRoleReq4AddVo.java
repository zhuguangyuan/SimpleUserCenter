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
public class UserRoleReq4AddVo {
    @ApiModelProperty(value = "userName", required = true, example = "bruce")
    private String userName;
    @ApiModelProperty(value = "roleName", required = true, example = "role1")
    private String roleName;
}
