package com.yihang.ultrat.user.dao;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yihang.ultrat.constant.domain.enums.NormalStatusEnum;
import com.yihang.ultrat.user.domain.entity.User;
import com.yihang.ultrat.user.domain.enums.UserActiveStatusEnum;
import com.yihang.ultrat.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDao extends ServiceImpl<UserMapper, User> {
    public void modifyName(Long uid, String name) {
        User updateUser = new User();
        updateUser.setId(uid);
        updateUser.setName(name);
        updateById(updateUser);
    }

    public User getByName(String name) {
        return lambdaQuery().eq(User::getName, name).one();
    }

    public List<User> getMemberList() {
        return lambdaQuery()
                .eq(User::getStatus, NormalStatusEnum.NORMAL.getStatus())
                .orderByDesc(User::getLastOptTime)
                .last("limit 1000")
                .select(User::getId, User::getName, User::getAvatar)
                .list();
    }

    public List<User> getFriendList(List<Long> ids) {
        return lambdaQuery()
                .in(User::getId, ids)
                .select(User::getId, User::getActiveStatus, User::getName, User::getAvatar)
                .list();
    }

    public Integer getOnlineCount() {
        return getOnlineCount(null);
    }

    public Integer getOnlineCount(List<Long> idList) {
        return lambdaQuery()
                .eq(User::getActiveStatus, UserActiveStatusEnum.ONLINE.getStatus())
                .in(CollectionUtil.isNotEmpty(idList), User::getId, idList)
                .count();
    }
}
