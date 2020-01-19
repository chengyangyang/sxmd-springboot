package com.sxmd.content.${folderName!''}.controller;

import com.sxmd.base.AjaxResult;
import com.sxmd.base.BaseController;
import com.sxmd.content.${folderName!''}.model.am.${table.tableNameToJavaName}AddModel;
import com.sxmd.content.${folderName!''}.model.em.${table.tableNameToJavaName}EditModel;
import com.sxmd.content.${folderName!''}.service.${table.tableNameToJavaName}Service;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Map;

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

    @Autowired
    ${table.tableNameToJavaName}Service service;


    /**
     * Description:   ${table.tableComment!''}-列表
     * @author sxmd
     * @param map: 参数
     * @date
     */
    @ApiOperation(value = "条件查询${table.tableComment!''}", notes = "${table.tableComment!''}")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "query", required = true, dataType = "int"),
    })
    public AjaxResult search(@ApiIgnore @RequestParam Map<String,Object> map) {
        return success(service.find${table.tableNameToJavaName}List(Integer.valueOf(map.get("pageNum")+""), Integer.valueOf(map.get("pageSize")+""),map));
    }

    /**
     * Description:   ${table.tableComment!''}-详情
     * @author sxmd
     * @param id:
     * @date
     */
    @ApiOperation(value = "获取${table.tableComment!''}", notes = "根据${table.tableComment!''}ID获取单条${table.tableComment!''}")
    @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") Long id) {
        return success(service.get${table.tableNameToJavaName}ById(id));
    }

    /**
     * Description:   ${table.tableComment!''}-新增
     * @author sxmd
     * @param model:
     * @date
     */
    @ApiOperation(value = "新增${table.tableComment!''}", notes = "新增${table.tableComment!''}")
    @RequestMapping(method = RequestMethod.POST)
    public AjaxResult insert(@RequestBody @Valid ${table.tableNameToJavaName}AddModel model) {
        boolean result = service.insert${table.tableNameToJavaName}(model);
        if (result) {
            return success("保存成功");
        } else {
            return fail("保存失败");
        }
    }


    /**
     * Description:   ${table.tableComment!''}-删除
     * @author sxmd
     * @param id:
     * @date
     */
    @ApiOperation(value = "删除${table.tableComment!''}", notes = "根据Id删除${table.tableComment!''}")
    @ApiImplicitParam(name = "id", value = "主键 id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id) {
        boolean result = service.delete${table.tableNameToJavaName}(id);
        if (result) {
            return success("保存成功");
        } else {
            return fail("保存失败");
        }
    }


    /**
     * Description:   ${table.tableComment!''}-修改
     * @author sxmd
     * @param model:
     * @date
     */
    @ApiOperation(value = "修改${table.tableComment!''}", notes = "根据id更新${table.tableComment!''}")
    @RequestMapping(method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody @Valid ${table.tableNameToJavaName}EditModel model) {
        boolean result = service.update${table.tableNameToJavaName}(model);
        if (result) {
            return success("修改成功");
        } else {
            return fail("修改失败");
        }
    }

}
