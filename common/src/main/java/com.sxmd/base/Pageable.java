package com.sxmd.base;

import lombok.Data;

/**
 * Description: 分页的数据显示
 *
 * @author cy
 * @date 2019年09月23日 15:22
 * Version 1.0
 */
@Data
public class Pageable {

    private int pages;
    private long total;
    private int pageNum;
    private int pageSize;
}
