package lol.kent.practice.spring.mybatis.mapper;

import lol.kent.practice.spring.mybatis.user.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月05日 18:58
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public interface UserMapper {

    String CRM_MEMBER = "crm_member";

    String CRM_MEMBER_COLUMNS =
            "id, user_id, store_id, level_id, username, password, realname, idcard, gender,  " +
                    "           phone, email, qq, mac, province_id, city_id, area_id, address, wxfans_id, has_deleted,  "
                    +
                    "            add_time, balance, point, last_sign_time, nickname, birthday, openid, zipcode, "
                    +
                    "            tall, weight, exercise_rate, exercise_aim, remark,wechat_mini_openid,union_id,img_url,img_file_store_id";

    String RESULT_MAP = "User";


    @Select({
            "select",
            CRM_MEMBER_COLUMNS,
            "from crm_member",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results(id = RESULT_MAP, value = {

            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "store_id", property = "storeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "level_id", property = "levelId", jdbcType = JdbcType.INTEGER),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "realname", property = "realname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "idcard", property = "idcard", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.INTEGER),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "qq", property = "qq", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mac", property = "mac", jdbcType = JdbcType.VARCHAR),
            @Result(column = "province_id", property = "provinceId", jdbcType = JdbcType.INTEGER),
            @Result(column = "city_id", property = "cityId", jdbcType = JdbcType.INTEGER),
            @Result(column = "area_id", property = "areaId", jdbcType = JdbcType.INTEGER),
            @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR),
            @Result(column = "wxfans_id", property = "wxfansId", jdbcType = JdbcType.INTEGER),
            @Result(column = "has_deleted", property = "hasDeleted", jdbcType = JdbcType.TINYINT),
            @Result(column = "add_time", property = "addTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "balance", property = "balance", jdbcType = JdbcType.DECIMAL),
            @Result(column = "point", property = "point", jdbcType = JdbcType.INTEGER),
            @Result(column = "last_sign_time", property = "lastSignTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "birthday", property = "birthday", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "openid", property = "openid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "wechat_mini_openid", property = "wechatMiniOpenid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "union_id", property = "unionId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zipcode", property = "zipcode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tall", property = "tall", jdbcType = JdbcType.INTEGER),
            @Result(column = "weight", property = "weight", jdbcType = JdbcType.INTEGER),
            @Result(column = "exercise_rate", property = "exerciseRate", jdbcType = JdbcType.INTEGER),
            @Result(column = "exercise_aim", property = "exerciseAim", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.LONGVARCHAR),
            @Result(column = "img_url", property = "imgUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "img_file_store_id", property = "imgFileStoreId", jdbcType = JdbcType.VARCHAR),
    })
    User selectByPrimaryKey(Integer id);

}
