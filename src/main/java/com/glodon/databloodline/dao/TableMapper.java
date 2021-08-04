package com.glodon.databloodline.dao;

import com.glodon.databloodline.pojo.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangbw-b
 * @create 2021-07-27 15:15
 */
@Mapper
public interface TableMapper {
    int addTable(Table table);
    int deleteTable(@Param("tableId") int tableId);
    int updateTable(Table table);
    Table queryTable(@Param("tableId") int tableId);
    List<Table> queryAllTables();
    Table queryTableByName(@Param("tableName") String tableName);
}
