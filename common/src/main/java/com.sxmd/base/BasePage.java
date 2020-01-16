package com.sxmd.base;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 对pagehelp 进行数据返回的封装
 *
 * @author cy
 * @date 2019年09月20日 15:58
 * Version 1.0
 */
@Data
public class BasePage<T> implements Serializable {

    private List<T> content;

    private Pageable pageable = new Pageable();

    public BasePage(List<T> data) {
        PageInfo<T> pageInfo = new PageInfo<T>(data);
        this.content = pageInfo.getList();
        pageable.setPages(pageInfo.getPages());
        pageable.setTotal(pageInfo.getTotal());
        pageable.setPageNum(pageInfo.getPageNum());
        pageable.setPageSize(pageInfo.getPageSize());
    }
}
