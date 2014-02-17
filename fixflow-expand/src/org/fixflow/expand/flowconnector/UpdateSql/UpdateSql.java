package org.fixflow.expand.flowconnector.UpdateSql;


import org.fixflow.core.action.ConnectorHandler;
import org.fixflow.core.runtime.ExecutionContext;

public class UpdateSql implements ConnectorHandler {

	private java.lang.String tableName;

	private java.util.HashMap objectParam;

	private java.lang.String whereSql;

	private java.util.List objectParamWhere;

	public void execute(ExecutionContext executionContext) throws Exception {
		if(objectParamWhere!=null){
			Object[] objectParamWhereNew = objectParamWhere.toArray();
			executionContext.getSqlCommand().update(tableName, objectParam, whereSql, objectParamWhereNew);
		}

	}

	public void  setTableName(java.lang.String tableName){
		this.tableName = tableName;
	}

	public void  setObjectParam(java.util.HashMap objectParam){
		this.objectParam = objectParam;
	}

	public void  setWhereSql(java.lang.String whereSql){
		this.whereSql = whereSql;
	}

	public void  setObjectParamWhere(java.util.List objectParamWhere){
		this.objectParamWhere = objectParamWhere;
	}

}