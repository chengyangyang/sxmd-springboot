package com.sxmd.content.mytest.controller;

import com.sxmd.base.AjaxResult;
import com.sxmd.base.BaseController;
import com.sxmd.base.PageResult;
import com.sxmd.base.ReturnInfo;
import com.sxmd.content.mytest.model.am.MyTestAddModel;
import com.sxmd.content.mytest.model.dm.MyTestModel;
import com.sxmd.content.mytest.model.em.MyTestEditModel;
import com.sxmd.content.mytest.model.lm.MyTestListModel;
import com.sxmd.content.mytest.service.MyTestService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

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

    @Resource
    MyTestService service;


    /**
    * Description:   测试表新增
    * @author sxmd
    * @param model:
    * @date
    */
    @ApiOperation(value = "添加测试表", notes = "添加测试表")
    @RequestMapping(method = RequestMethod.POST)
    public AjaxResult add(@ApiParam(name = "model", value = "测试表实体", required = true)
    @RequestBody @Valid MyTestAddModel model) {
        boolean result = service.addMyTest(model);
        if (result) {
            return success(ReturnInfo.SAVE_SUCCESS_MSG);
        } else {
            return fail(ReturnInfo.SAVE_FAIL_MSG);
        }
    }


    /**
    * Description:   删除测试表
    * @author sxmd
    * @param id:
    * @date
    */
    @ApiOperation(value = "删除测试表", notes = "根据Id删除测试表")
    @ApiImplicitParam(name = "id", value = "维保计划 id", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") String id) {
        boolean result = service.deleteMyTest(id);
        if (result) {
            return success(ReturnInfo.DEL_SUCCESS_MSG);
        } else {
         return fail(ReturnInfo.DEL_FAIL_MSG);
        }
    }

    /**
    * Description:   测试表详情
    * @author sxmd
    * @param id:
    * @date
    */
    @ApiOperation(value = "获取测试表", notes = "根据测试表ID获取单条测试表")
    @ApiImplicitParam(name = "id", value = "维保计划id", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") String id) {
        MyTestModel model = service.getMyTestById(id);
        if (Objects.nonNull(model)) {
        return success(ReturnInfo.QUERY_SUCCESS_MSG,model);
        } else {
        return fail(ReturnInfo.QUERY_FAIL_MSG);
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
    public AjaxResult update(@ApiParam(name = "model", value = "测试表修改实体", required = true) @RequestBody @Valid MyTestEditModel model) {
        boolean result = service.updateMyTest(model);
        if (result) {
            return success(ReturnInfo.UPDATE_SUCCESS_MSG);
        } else {
            return fail(ReturnInfo.UPDATE_FAIL_MSG);
        }
    }




    /**
    * Description:   列表测试表
    * @author sxmd
    * @param map:
    * @date
    */
    @ApiOperation(value = "条件查询测试表", notes = "测试表")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
    @ApiImplicitParam(name = "page", value = "当前页", paramType = "query", required = true, dataType = "int"),
    @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "query", required = true, dataType = "int"),
    })
    public AjaxResult search(@ApiIgnore @RequestParam Map<String,Object> map) {
        PageResult<MyTestListModel> model = service.findMyTestList(Integer.valueOf(map.get("page")+""), Integer.valueOf(map.get("pageSize")+""),map);
        if (Objects.nonNull(model)) {
            return success(ReturnInfo.QUERY_SUCCESS_MSG, model);
        } else {
            return fail(ReturnInfo.QUERY_FAIL_MSG);
        }
    }

}
