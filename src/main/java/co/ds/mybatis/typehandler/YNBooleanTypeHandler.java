package co.ds.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class YNBooleanTypeHandler implements TypeHandler<Boolean> {

	private static final String TRUE_STRING = "Y";
	private static final String FALSE_STRING = "N";

	@Override
	public void setParameter(final PreparedStatement ps, final int i, final Boolean parameter, final JdbcType jdbcType) throws SQLException {
		if (null == parameter) {
			ps.setNull(i, jdbcType.TYPE_CODE);
		} else {
			ps.setString(i, parameter ? TRUE_STRING : FALSE_STRING);
		}
	}

	@Override
	public Boolean getResult(final ResultSet rs, final String columnName) throws SQLException {
		return getResult(rs.getString(columnName));
	}

	@Override
	public Boolean getResult(final ResultSet resultSet, final int columnIndex) throws SQLException {
		return getResult(resultSet.getString(columnIndex));
	}

	@Override
	public Boolean getResult(final CallableStatement cs, final int columnIndex) throws SQLException {
		return getResult(cs.getString(columnIndex));
	}

	private Boolean getResult(final String value) throws SQLException {
		final String result = trimAndUpcase(value);
		if (null == result) return null;
		if (TRUE_STRING.equals(result)) return true;
		if (FALSE_STRING.equals(result)) return false;
		throw new SQLException("Unable to map '" + result + "' to a boolean value.");
	}

	String trimAndUpcase(final String string) {
		return (string == null) ? null : string.trim().toUpperCase();
	}
}
