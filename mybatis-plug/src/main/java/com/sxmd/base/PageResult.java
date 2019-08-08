package com.sxmd.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Description: 封装pageHelp
 *
 * @author cy
 * @date 2019年08月07日 13:58
 * Version 1.0
 */
public class PageResult<T> {

    private int pages;
    private List list;

    public PageResult(Integer page, Integer pageSise, List<T> list) {
        int offset = page != null ? page : 1;
        int limit = pageSise != null ? pageSise : 10;
        PageHelper.startPage(offset, limit);
        PageInfo<T> pageInfo = new PageInfo<>(list);
        this.pages = pageInfo.getPages();
        this.list = pageInfo.getList();
    }
}
