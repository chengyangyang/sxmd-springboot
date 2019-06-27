package com.sxmd.database.service;

import com.sxmd.config.FreemarkerConfig;
import com.sxmd.config.FtlConfig;
import com.sxmd.database.bean.FtlEntity;
import com.sxmd.database.dao.MysqlGeneratorDao;
import com.sxmd.database.dao.PostgreGeneratorDao;
import com.sxmd.database.bean.ColumnEntity;
import com.sxmd.database.bean.TableEntity;
import com.sxmd.help.SqlToJavaHelp;
import com.sxmd.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月26日 17:05
 * Version 1.0
 */
@Service
public class GeneratorServiceImpl implements GeneratorService{

    @Autowired
    private MysqlGeneratorDao mysqlDao;
    @Autowired
    private PostgreGeneratorDao postgreDao;

    /**
     * Description:   查询列表
     * @author cy
     * @param tableName:
     * @return java.util.List<com.sxmd.database.bean.TableEntity>
     * @date  2019/6/26 17:14
     */
    @Override
    public List<TableEntity> getTableAll(String tableName){
        return postgreDao.getAllTable(tableName);
    }


    /**
     * Description:   对实体进行 相应的转化
     * @author cy
     * @param tableName:
     * @return com.sxmd.database.bean.TableEntity
     * @date  2019/6/26 16:50
     */
    @Override
    public TableEntity getTableByTableNameAndInit(String tableName){
        TableEntity oneTable = getOneTable(tableName);
        oneTable.setTableNameToJavaName(SqlToJavaHelp.classNameByTable(oneTable.getTableName()));
        return oneTable;
    }

    /**
     * Description:   对列 进行相应的处理
     * @author cy
     * @param isFilterColumns:
     * @param tableName:
     * @return java.util.List<com.sxmd.database.bean.ColumnEntity>
     * @date  2019/6/26 16:59
     */
    @Override
    public List<ColumnEntity> getColumnsByTableInit(boolean isFilterColumns,String tableName){
        List<ColumnEntity> columnsByTable = postgreDao.getColumnsByTable(tableName);
        columnsByTable.forEach(x->{
            x.setColumnNameToJava(StringUtils.camelCaseName(x.getColumnName()));
            x.setColumnTypeToJava(SqlToJavaHelp.getJavaTypeBySqlType(x.getColumnType()));
        });
        if(isFilterColumns){
            // 对集合进行过滤
            return columnsByTable.stream().filter(x->!SqlToJavaHelp.filterSqlColumns(x.getColumnName())).collect(Collectors.toList());
        }else {
            return columnsByTable;
        }
    }

    /**
     * Description:   文件生成
     * @author cy
     * @param isFilterColumns: 是否进行数据过滤
     * @param tableName: 是否使用表名称
     * @param templateName: 模板名称
     * @param map:  数据
     * @return void
     * @date  2019/6/26 17:09
     */
    @Override
    public void generatorJavaFile(boolean isFilterColumns,String tableName,String templateName, Map<String,Object> map){
        FtlEntity ftlEntity = FtlConfig.getFtlEntity(templateName);
        String filePath = ftlEntity.getCreateFilePath();
        String fileName = ftlEntity.getCreateFileName();
        File file = new File(filePath);
        if(file.exists()){
            if(!file.isDirectory()){
                throw new RuntimeException("文件路径只能为文件夹");
            }
        }else {
            file.mkdirs();
        }
        // 如果输入的表名称不是空的，把表的字段进入
        if(StringUtils.isNotBlank(tableName) && fileName.contains(".java")){
            TableEntity table = this.getTableByTableNameAndInit(tableName);
            List<ColumnEntity> columns = this.getColumnsByTableInit(isFilterColumns,tableName);
            map.put("table",table);
            map.put("columns",columns);
            // 对文件进行处理（替换标识符的内容）
            fileName = MessageFormat.format(ftlEntity.getCreateFileName(), new Object[]{table.getTableNameToJavaName()});
            map.put("fileName",fileName.substring(0,fileName.lastIndexOf(".")));
        }

        FreemarkerConfig.generatorFile(templateName,filePath + fileName,map);
    }

    /**
     * Description:   获得单个表
     * @author cy
     * @param tableName:
     * @return com.sxmd.database.bean.TableEntity
     * @date  2019/6/26 16:51
     */
    private TableEntity getOneTable(String tableName){
        return postgreDao.getOneTable(tableName);
    }

    private List<ColumnEntity> getColumnsByTable(String tableName){
        return postgreDao.getColumnsByTable(tableName);
    }
}
