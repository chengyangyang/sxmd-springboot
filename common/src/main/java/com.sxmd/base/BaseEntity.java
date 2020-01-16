package com.sxmd.base;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description:  表的公共字段
 *
 * @author cy
 * @date 2019年08月05日 16:41
 * Version 1.0
 */
@Data
public class BaseEntity {

    private Long id;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
