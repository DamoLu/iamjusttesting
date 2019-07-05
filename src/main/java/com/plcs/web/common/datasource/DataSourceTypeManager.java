package com.plcs.web.common.datasource;

import com.plcs.web.common.enums.DataSourceEnum;

/**
 * @author User
 * 数据源类型管理
 *
 */
public class DataSourceTypeManager {

	 private static final ThreadLocal<DataSourceEnum> dataSourceTypes = new ThreadLocal<DataSourceEnum>();
	    
	    public static DataSourceEnum get(){
	        return dataSourceTypes.get();
	    }
	    
	    public static void set(DataSourceEnum dataSourceType){
	        dataSourceTypes.set(dataSourceType);
	    }
	    
	    public static void reset(){
	        dataSourceTypes.set(DataSourceEnum.MASTER);
	    }
}
