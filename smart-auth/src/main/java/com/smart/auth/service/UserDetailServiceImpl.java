package com.smart.auth.service;

import com.smart.auth.feign.MemberService;
import com.smart.commons.dto.MemberDto;
import com.smart.commons.result.ResponseResult;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 会员服务--->认证服务-->会员服务
 * <p>
 * <p>
 * 管理人员  sys_admin
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    MemberService memberService;

    /**
     * 客服端标识
     * 前端----->1
     * 后台----->2
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseResult<MemberDto> result = memberService.getUserByUsername(username);
        if (result.getStatus() == 200) {
            MemberDto dto = result.getData();
            ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("admin");
            list.add(simpleGrantedAuthority);
            return new User(dto.getUsername(), dto.getPassword(), list);
        }
        return null;
    }
}
