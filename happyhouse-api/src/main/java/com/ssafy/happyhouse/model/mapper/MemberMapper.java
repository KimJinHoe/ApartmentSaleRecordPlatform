package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public void userRegister(MemberDto memberDto) throws SQLException;
	public void userDelete(String userid) throws SQLException;
	public void userUpdate(MemberDto memberDto) throws SQLException;
	
}