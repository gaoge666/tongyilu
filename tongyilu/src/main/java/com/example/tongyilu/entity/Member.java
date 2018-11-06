package com.example.tongyilu.entity;

import com.example.tongyilu.common.base.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Moya
 * @description ：用户实体
 * @Created by  : Moya
 * @Creation Date ： 2018/6/4 18:03
 */
@ToString
@Data
@Entity
@Table(name = "tyl_member")
public class Member extends BaseEntity {
    /**
     * openid
     */
    private String openid;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信昵称
     */
    private String nickName;
    /**
     * 所属省
     */
    private String province;
    /**
     * 所属市
     */
    private String city;
    /**
     * 性别
     */
    private Integer gender;

    /**
     * 状态 默认1  1:启用 0:禁用 2:删除
     */
    private Byte state;
    /**
     * 被收藏数量
     */
    private Long collectionNum;

}
