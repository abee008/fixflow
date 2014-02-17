package org.fixflow.expand.flowconnector.UpdateProcessInstanceId;


import org.fixflow.core.action.ConnectorHandler;
import org.fixflow.core.exception.FixFlowException;
import org.fixflow.core.impl.Context;
import org.fixflow.core.impl.db.SqlCommand;
import org.fixflow.core.impl.util.StringUtil;
import org.fixflow.core.runtime.ExecutionContext;

public class UpdateProcessInstanceId implements ConnectorHandler {

	public void execute(ExecutionContext executionContext) throws Exception {
		SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
		String Fix_BizName=StringUtil.getString(Context.getAbstractScriptLanguageMgmt().execute("${Fix_BizName}", executionContext));
		String Fix_BizKeyFile=StringUtil.getString(Context.getAbstractScriptLanguageMgmt().execute("${Fix_BizKeyFile}", executionContext));
		if(Fix_BizName==null||Fix_BizName.equals("")){
			throw new FixFlowException("数据变量${Fix_BizName}为空");
		}
		if(Fix_BizKeyFile==null||Fix_BizKeyFile.equals("")){
			throw new FixFlowException("数据变量${Fix_BizKeyFile}为空");
		}
		String processInstId=executionContext.getProcessInstance().getId();
		String sqlText="UPDATE "+Fix_BizName+" SET  FIX_PROCESSINSTANCE_ID='"+processInstId+"' WHERE "+Fix_BizKeyFile+"='"+executionContext.getBizKey()+"'";
		sqlCommand.execute(sqlText);
	}

}