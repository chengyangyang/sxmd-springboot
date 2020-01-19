package com.sxmd.content.mytest.controller;

import com.sxmd.base.AjaxResult;
import com.sxmd.base.BaseController;
import com.sxmd.content.mytest.model.am.MyTestAddModel;
import com.sxmd.content.mytest.model.em.MyTestEditModel;
import com.sxmd.content.mytest.service.MyTestService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Map;


/**
* Description: 测试表 控制器
*
* @author sxmd
* @date
* Version 1.0
*/

@RestController
@Api(value = "测试表 controller",tags = "测试表相关接口")
@RequestMapping("MyTest")
public class MyTestController extends BaseController {

    @Autowired
    MyTestService service;

    /**
     * Description:   列表测试表
     * @author sxmd
     * @param map:
     * @date
     */
    @ApiOperation(value = "条件查询测试表", notes = "测试表")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "query", required = true, dataType = "int"),
    })
    public AjaxResult search(@ApiIgnore @RequestParam Map<String,Object> map) {
        return success(service.findMyTestList(Integer.valueOf(map.get("page")+""), Integer.valueOf(map.get("pageSize")+""),map));
    }

    /**
     * Description:   测试表详情
     * @author sxmd
     * @param id:
     * @date
     */
    @ApiOperation(value = "获取测试表", notes = "根据测试表ID获取单条测试表")
    @ApiImplicitParam(name = "id", value = "测试表id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") Long id) {
        return success(service.getMyTestById(id));
    }

    /**
    * Description:   测试表新增
    * @author sxmd
    * @param model:
    * @date
    */
    @ApiOperation(value = "添加测试表", notes = "添加测试表")
    @RequestMapping(method = RequestMethod.POST)
    public AjaxResult add(@RequestBody @Valid MyTestAddModel model) {
        boolean result = service.insertMyTest(model);
        if (result) {
            return success("保存成功");
        } else {
            return fail("保存失败");
        }
    }

    /**
     * Description:   修改测试表
     * @author sxmd
     * @param model:
     * @date
     */
    @ApiOperation(value = "修改测试表", notes = "根据id更新测试表")
    @RequestMapping(method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody @Valid MyTestEditModel model) {
        boolean result = service.updateMyTest(model);
        if (result) {
            return success("修改成功");
        } else {
            return fail("修改失败");
        }
    }

    /**
    * Description:   删除测试表
    * @author sxmd
    * @param id:
    * @date
    */
    @ApiOperation(value = "删除测试表", notes = "根据Id删除测试表")
    @ApiImplicitParam(name = "id", value = "主键 id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id) {
        boolean result = service.deleteMyTest(id);
        if (result) {
            return success("删除成功");
        } else {
            return fail("删除失败");
        }
    }

}

