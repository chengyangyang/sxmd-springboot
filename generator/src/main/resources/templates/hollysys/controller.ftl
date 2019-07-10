package com.hollysys.smartfactory.account.system.controller;

import com.github.pagehelper.PageInfo;
import com.hollysys.smart.factory.bean.AjaxResult;
import com.hollysys.smart.factory.bean.ReturnInfo;
import com.hollysys.smart.factory.date.DateUtils;
import com.hollysys.smart.factory.string.StrKit;
import com.hollysys.smartfactory.account.system.model.vm.${table.tableNameToJavaName}ListModel;
import com.hollysys.smartfactory.account.system.model.vm.${table.tableNameToJavaName}Model;
import com.hollysys.smartfactory.account.system.model.vr.${table.tableNameToJavaName}AddModel;
import com.hollysys.smartfactory.account.system.model.vr.${table.tableNameToJavaName}EditModel;
import com.hollysys.smartfactory.account.system.service.${table.tableNameToJavaName}Service;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

/**
* Description: ${table.tableComment!''} 控制器
*
* @author sxmd
* @date
* Version 1.0
*/
@RestController
@Api(value = "${table.tableComment!''} controller",tags = "${table.tableComment!''}相关接口")
@RequestMapping("${table.tableNameToJavaName}")
public class ${fileName} extends BaseController {

    @Resource
    ${table.tableNameToJavaName}Service service;


    /**
    * Description:   ${table.tableComment!''}新增
    * @author sxmd
    * @param model:
    * @date
    */
    @ApiOperation(value = "添加${table.tableComment!''}", notes = "添加${table.tableComment!''}")
    @RequestMapping(method = RequestMethod.POST)
    public AjaxResult add(@ApiParam(name = "model", value = "${table.tableComment!''}实体", required = true)
    @RequestBody @Valid ${table.tableNameToJavaName}AddModel model) {
        String id = service.add${table.tableNameToJavaName}(model);
        if (StrKit.isNotEmpty(id)) {
            return success(ReturnInfo.SAVE_SUCCESS_MSG, id);
        } else {
            return fail(ReturnInfo.SAVE_FAIL_MSG);
        }
    }


    /**
    * Description:   删除${table.tableComment!''}
    * @author sxmd
    * @param id:
    * @date
    */
    @ApiOperation(value = "删除${table.tableComment!''}", notes = "根据Id删除${table.tableComment!''}")
    @ApiImplicitParam(name = "id", value = "维保计划 id", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") String id) {
        String deleteId = service.delete${table.tableNameToJavaName}(id);
        if (StrKit.isNotEmpty(deleteId)) {
            return success(ReturnInfo.DEL_SUCCESS_MSG, deleteId);
        } else {
         return fail(ReturnInfo.DEL_FAIL_MSG);
        }
    }

    /**
    * Description:   ${table.tableComment!''}详情
    * @author sxmd
    * @param id:
    * @date
    */
    @ApiOperation(value = "获取${table.tableComment!''}", notes = "根据${table.tableComment!''}ID获取单条${table.tableComment!''}")
    @ApiImplicitParam(name = "id", value = "维保计划id", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") String id) {
        ${table.tableNameToJavaName} model = service.get${table.tableNameToJavaName}(id);
        if (Objects.nonNull(model)) {
        return success(ReturnInfo.QUERY_SUCCESS_MSG).setResults(model);
        } else {
        return fail(ReturnInfo.QUERY_FAIL_MSG);
        }
    }


    /**
    * Description:   修改${table.tableComment!''}
    * @author sxmd
    * @param model:
    * @date
    */
    @ApiOperation(value = "修改${table.tableComment!''}", notes = "根据id更新${table.tableComment!''}")
    @RequestMapping(method = RequestMethod.PUT)
    public AjaxResult update(@ApiParam(name = "model", value = "${table.tableComment!''}修改实体", required = true) @RequestBody @Valid ${table.tableNameToJavaName}EditModel model) {
        String id = service.update${table.tableNameToJavaName}(model);
        if (StrKit.isNotEmpty(id)) {
            return success(ReturnInfo.UPDATE_SUCCESS_MSG, id);
        } else {
            return fail(ReturnInfo.UPDATE_FAIL_MSG);
        }
    }




    /**
    * Description:   列表${table.tableComment!''}
    * @author sxmd
    * @param model:
    * @date
    */
    @ApiOperation(value = "条件查询${table.tableComment!''}", notes = "${table.tableComment!''}")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
    @ApiImplicitParam(name = "page", value = "当前页", paramType = "query", required = true, dataType = "int"),
    @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "query", required = true, dataType = "int"),
    })
    public AjaxResult search(@ApiIgnore @RequestParam Map<String,Object> map) {

        PageInfo<${table.tableNameToJavaName}Model> model = service.find${table.tableNameToJavaName}(Integer.valueOf(map.get("page")+""), Integer.valueOf(map.get("pageSize")+""),map);
        if (Objects.nonNull(model)) {
            return success(ReturnInfo.QUERY_SUCCESS_MSG, model);
        } else {
            return fail(ReturnInfo.QUERY_FAIL_MSG);
        }
    }

}
