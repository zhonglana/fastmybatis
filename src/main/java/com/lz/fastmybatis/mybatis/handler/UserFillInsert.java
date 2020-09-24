package com.lz.fastmybatis.mybatis.handler;

import com.alibaba.fastjson.JSON;
import com.gitee.fastmybatis.core.handler.BaseFill;
import com.gitee.fastmybatis.core.handler.FillType;
import com.lz.fastmybatis.mybatis.entity.TestEntry;
import com.lz.fastmybatis.mybatis.entity.UserInfo;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * insert时的字段填充<br>
 * 在做insert操作时,如果表里面有user_info字段,则自动填充时间
 * @author tanghc
 *
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({UserInfo.class})
public class UserFillInsert extends BaseFill<UserInfo> {

	private String columnName = "user_info";

	@Override
	public FillType getFillType() {
		return FillType.INSERT;
	}

	@Override
	public Class<?>[] getTargetEntityClasses() {
		return new Class<?>[] {TestEntry.class };
	}

	@Override
	public UserInfo getFillValue(UserInfo defaultValue) {
		if(defaultValue == null) {
			return null;
		}
		return defaultValue;
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	protected UserInfo convertValue(Object columnValue) {
		UserInfo userInfo = JSON.parseObject(columnValue.toString(), UserInfo.class);
		return userInfo;
	}

	@Override
	protected void setParameterValue(PreparedStatement ps, int i, UserInfo parameter, JdbcType jdbcType) throws SQLException {
		Object val = this.buildFillValue(parameter);
		if (val == null) {
			ps.setNull(i, jdbcType.TYPE_CODE);
		} else {
			if (val instanceof UserInfo) {
				String jsonString = JSON.toJSONString(parameter);
				ps.setString(i, jsonString);
			} else {
				ps.setObject(i, val);
			}
		}
	}
}
