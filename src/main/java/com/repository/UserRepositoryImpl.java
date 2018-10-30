package com.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.dto.User;
import com.exception.DatabaseException;
import com.util.EnumUtil;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	JdbcTemplate template;
	@Autowired
	NamedParameterJdbcTemplate namedTemplate;

	private static final class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int arg) throws SQLException {
			User user = new User();
			user.setMobile(rs.getString("MOBILE"));
			user.setGender(EnumUtil.stringToGender(rs.getString("GENDER")));
			return user;
		}
	}

	@Override
	public User getUserById(int id) {
		return template.queryForObject("select * from user where user_id = ?",
				new Object[] { id }, new UserMapper());
	}

	@Override
	public int addUser(User user) throws DatabaseException {
		Map<String, Object> values = new HashMap<>();
		values.put("GENDER", user.getGender());
		values.put("MOBILE", user.getMobile());
		SqlParameterSource sqlParamSource = new MapSqlParameterSource(values);
		int row = namedTemplate.update("insert into user values (:id, :gender, :mobile)", sqlParamSource);
		if (row == 0)
			throw new DatabaseException("Unable to insert user data.");
		return row;
	}

	@Override
	public int updateUser(User user) throws DatabaseException {
		SqlParameterSource sqlParamSource= new BeanPropertySqlParameterSource(user);
		int row = namedTemplate.update("update login set gender = :gender, mobile = :password where user_id = :userId"
				, sqlParamSource);
		if (row == 0)
			throw new DatabaseException("Unable to update user data.");
		return row;
	}

}
