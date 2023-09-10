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
public class RoleReq4CreateVo {
    @ApiModelProperty(value = "roleName", required = true, example = "role1")
    private String name;
}
