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
public class UserReq4DelVo {
    @ApiModelProperty(value = "userName", required = true, example = "bruce")
    private String name;
    // may be should provide password to ensure operator has permission
}
