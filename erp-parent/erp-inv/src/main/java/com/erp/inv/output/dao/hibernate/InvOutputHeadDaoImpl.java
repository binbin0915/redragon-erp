/*
 * Copyright 2020-2021 redragon.dongbin
 *
 * This file is part of redragon-erp/赤龙ERP.

 * redragon-erp/赤龙ERP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.

 * redragon-erp/赤龙ERP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with redragon-erp/赤龙ERP.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.erp.inv.output.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.framework.annotation.Cache;
import com.framework.annotation.Permissions;
import com.framework.annotation.Permissions.PermissionType;
import com.framework.annotation.SqlParam;
import com.framework.dao.DaoSupport;
import com.framework.dao.model.Pages;
import com.framework.util.DaoUtil;
import com.erp.inv.input.dao.model.InvInputHead;
import com.erp.inv.output.dao.InvOutputHeadDao;
import com.erp.inv.output.dao.model.InvOutputHead;
import com.erp.inv.output.dao.model.InvOutputHeadCO;

@Repository
public class InvOutputHeadDaoImpl implements InvOutputHeadDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(InvOutputHead obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(InvOutputHead obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvOutputHead obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(InvOutputHead obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<InvOutputHead> getDataObjects() {
        return this.daoSupport.getDataAllObject(InvOutputHead.class);
    }

    @Override
    public InvOutputHead getDataObject(int id) {
        return (InvOutputHead)this.daoSupport.getDataObject(InvOutputHead.class, id);
    }
    
    @Override
    public InvOutputHead getDataObject(String code) {
        String sql = "select i.* from inv_output_head i where i.output_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("i", InvOutputHead.class);
        
        List<InvOutputHead> list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<InvOutputHead> getDataObjects(InvOutputHeadCO paramObj) {
        return null;
    }
    
    @Override
    public List<InvOutputHead> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<InvOutputHead> getDataObjects(Pages pages, InvOutputHeadCO paramObj) {
        String sql = "select i.* from inv_output_head i where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "outputHeadCode", "and i.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "outputSourceType", "and i.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "outputSourceHeadCode", "and i.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "outputType", "and i.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "outputDate", "and i.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and i.", args);
        sql = sql + " order by i.output_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("i", InvOutputHead.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvOutputHeadCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<InvOutputHead> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, InvOutputHeadCO paramObj) {
        String sql = "select i.* from inv_output_head i where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "outputHeadCode", "and i.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "outputSourceType", "and i.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "outputSourceHeadCode", "and i.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "outputType", "and i.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "outputDate", "and i.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and i.", args);
        sql = sql + DaoUtil.getDataAuthSQL(dataAuthSQL, "i.", "i.");
        sql = sql + " order by i.output_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("i", InvOutputHead.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        String sql = "update inv_output_head set approve_status = :approveStatus where output_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        args.put("approveStatus", approveStatus);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
}
