package lol.kent.practice.spring.mybatis.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class User {

    private Integer id;

    private Integer userId;

    private Integer storeId;

    private Integer levelId;

    @JsonProperty("user_name")
    private String username;

    private String password;

    private String realname;

    private String idcard;

    private Integer gender;

    private String phone;

    private String email;

    private String qq;

    private String mac;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private String address;

    private Integer wxfansId;

    private Byte hasDeleted;

    private Date addTime;

    private BigDecimal balance;

    private Integer point;

    private Date lastSignTime;

    private String nickname;

    private Date birthday;

    private String openid;

    private String wechatMiniOpenid;

    private String unionId;

    private String zipcode;

    private Integer tall;

    private Integer weight;

    private Integer exerciseRate;

    private Integer exerciseAim;

    private String remark;

    private String imgUrl;

    private Long imgFileStoreId;

}