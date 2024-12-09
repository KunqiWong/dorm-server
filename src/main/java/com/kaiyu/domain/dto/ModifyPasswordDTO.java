package com.kaiyu.domain.dto;

import lombok.Data;


@Data
public class ModifyPasswordDTO {

    private String rawPassword;
    private String newPassword;


}
