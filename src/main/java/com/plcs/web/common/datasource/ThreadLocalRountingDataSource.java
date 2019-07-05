package com.plcs.web.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author User
 *
 */
public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		
		return DataSourceTypeManager.get();
	}

}
