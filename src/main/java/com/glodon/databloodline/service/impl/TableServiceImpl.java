package com.glodon.databloodline.service.impl;

import com.glodon.databloodline.dao.TableMapper;
import com.glodon.databloodline.pojo.Table;
import com.glodon.databloodline.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangbw-b
 * @create 2021-07-27 16:40
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableMapper tableMapper;

    @Override
    public int addTable(Table table) {
        return tableMapper.addTable(table);
    }

    @Override
    public int deleteTable(int tableId) {
        return tableMapper.deleteTable(tableId);
    }

    @Override
    public int updateTable(Table table) {
        return tableMapper.updateTable(table);
    }

    @Override
    public Table queryTable(int tableId) {
        return tableMapper.queryTable(tableId);
    }

    @Override
    public List<Table> queryAllTables() {
        return tableMapper.queryAllTables();
    }

    @Override
    public Table queryTableByName(String tableName) {
        return tableMapper.queryTableByName(tableName);
    }
}
