package com.api.sys_dept;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.model.SysDept;
import com.base.framework.dao.impl.BaseDao;
import com.base.framework.dao.impl.SqlParameter;
import com.base.util.TextUtil;

@Repository("SysDeptDao")
public class SysDeptDao extends BaseDao{

	public List<SysDept> queryAll(SysDept sysDept) {
		String sql = 	"SELECT\n" +
						"	DEPT_ID,\n" +
						"	P_DEPT_ID,\n" +
						"	DEPT_CODE,\n" +
						"	DEPT_NAME,\n" +
						"	DEPT_HEAD \n" +
						"FROM\n" +
						"	SYS_DEPT \n" +
						"WHERE\n" +
						"	1 =1\n";
		SqlParameter parameter = new SqlParameter();
		if(sysDept != null && TextUtil.isNotEmpty(sysDept.getDeptName())){
			sql += " AND (DEPT_NAME LIKE :DEPT_NAME OR DEPT_CODE LIKE :DEPT_NAME)";
			parameter.add("DEPT_NAME", "%" + sysDept.getDeptName() + "%");
		}
		return query(sql, parameter, SysDept.class);
	}
	
}
