package lol.kent.practice.spring.security.user.mapper;

import lol.kent.practice.spring.security.user.domain.CustomUser;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String insertSelective(CustomUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }

        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CustomUser record) {
        SQL sql = new SQL();
        sql.UPDATE("user");

        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }
}