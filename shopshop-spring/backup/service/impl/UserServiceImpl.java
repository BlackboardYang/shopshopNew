package com.panacea.shopshop.service.impl;

import com.panacea.shopshop.entity.User;
import com.panacea.shopshop.mapper.UserMapper;
import com.panacea.shopshop.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alex
 * @since 2023-06-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
