package com.smart.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.commons.result.ResponseResult;
import com.smart.db.entity.Member;
import com.smart.db.mapper.MemberMapper;
import com.smart.member.callback.DefaultSendCallback;
import com.smart.member.dto.MemberDto;
import com.smart.member.service.MemberService;
import com.smart.lock.service.RedisService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 采用异步方式
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    RocketMQTemplate mqTemplate;
    @Resource
    RedisService redisService;
    @Resource
    MemberMapper memberMapper;


    /**
     * 接受到手机号
     * 生成验证码  6位 保存  60秒自动删除  redis 过期时间
     * 调用第三方的短信服务
     *
     * @param phone
     * @return
     */
    @Override
    public String getVerifyCode(String phone) {
        mqTemplate.asyncSend("member-sms-topic", phone, new DefaultSendCallback());
        return "success";
    }

    /**
     * @param phone
     * @param code
     * @return
     */
    @Override
    public String login(String phone, String code) {
        String verifyCode = (String) redisService.get("sms:phone:" + phone);
        if (!StringUtils.isEmpty(verifyCode) && verifyCode.equals(code)) {

        } else {

        }
        return null;
    }

    @Override
    public ResponseResult<MemberDto> findMemeberByName(String username) {
        Member member = memberMapper.selectOne(new QueryWrapper<Member>().eq(Member.COL_USERNAME, username));
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member, memberDto);
        return ResponseResult.success(memberDto);
    }


}
