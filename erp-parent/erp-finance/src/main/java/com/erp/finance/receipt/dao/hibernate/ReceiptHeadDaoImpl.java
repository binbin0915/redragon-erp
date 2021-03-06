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
package com.erp.finance.receipt.dao.hibernate;

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
import com.erp.finance.pay.dao.model.PayHead;
import com.erp.finance.receipt.dao.ReceiptHeadDao;
import com.erp.finance.receipt.dao.model.ReceiptHead;
import com.erp.finance.receipt.dao.model.ReceiptHeadCO;

@Repository
public class ReceiptHeadDaoImpl implements ReceiptHeadDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(ReceiptHead obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(ReceiptHead obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ReceiptHead obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(ReceiptHead obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<ReceiptHead> getDataObjects() {
        return this.daoSupport.getDataAllObject(ReceiptHead.class);
    }

    @Override
    public ReceiptHead getDataObject(int id) {
        return (ReceiptHead)this.daoSupport.getDataObject(ReceiptHead.class, id);
    }
    
    @Override
    public ReceiptHead getDataObject(String code) {
        String sql = "select p.* from receipt_head p where p.receipt_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ReceiptHead.class);
        
        List<ReceiptHead> list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<ReceiptHead> getDataObjects(ReceiptHeadCO paramObj) {
        return null;
    }
    
    @Override
    public List<ReceiptHead> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<ReceiptHead> getDataObjects(Pages pages, ReceiptHeadCO paramObj) {
        String sql = "select p.* from receipt_head p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "receiptHeadCode", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "receiptSourceType", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "receiptSourceHeadCode", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "payer", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "preReceiptFlag", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and p.", args);
        sql = sql + " order by p.receipt_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ReceiptHead.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ReceiptHeadCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<ReceiptHead> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, ReceiptHeadCO paramObj) {
        String sql = "select p.* from receipt_head p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "receiptHeadCode", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "receiptSourceType", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "receiptSourceHeadCode", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "payer", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "preReceiptFlag", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and p.", args);
        sql = sql + DaoUtil.getDataAuthSQL(dataAuthSQL, "p.", "p.");
        sql = sql + " order by p.receipt_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ReceiptHead.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
    @Override
    public List<ReceiptHead> getReceiptHeadListForNotCreateVoucher(Pages pages, ReceiptHeadCO paramObj) {
        String sql = "select p.* from receipt_head p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "receiptHeadCode", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "receiptSourceType", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "receiptSourceHeadCode", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "payer", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "preReceiptFlag", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and p.", args);
        
        sql = sql + " and not exists (select 1 from fin_voucher_bill_r where bill_type = 'RECEIPT' and bill_head_code = p.receipt_head_code)";
        sql = sql + " order by p.receipt_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ReceiptHead.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        String sql = "update receipt_head set approve_status = :approveStatus where receipt_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        args.put("approveStatus", approveStatus);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public int getReceiptHeadNum(String startDate, String endDate) {
        String sql = "select count(*) from receipt_head where created_date between :startDate and :endDate";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("startDate", startDate);
        args.put("endDate", endDate);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return this.daoSupport.convertSQLCount(list.get(0));
        }
        
        return 0;
    }
    
}
