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
public class UserReq4LogoutVo {
    @ApiModelProperty(value = "user's token", required = true, example = "token")
    private String token;
}
