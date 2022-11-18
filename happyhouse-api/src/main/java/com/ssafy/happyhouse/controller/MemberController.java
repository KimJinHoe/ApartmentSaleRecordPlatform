package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
		System.out.println(memberDto);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		System.out.println(resultMap);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") String userid, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				MemberDto memberDto = memberService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/findpwd")
	@ResponseBody
	public ResponseEntity<MemberDto> findpwd(@RequestBody MemberDto memberDto) throws Exception {
		MemberDto result = null;
		MemberDto user = memberService.userInfo(memberDto.getUserid());
		
		if (user == null) {
			return new ResponseEntity<MemberDto>(result, HttpStatus.OK);
		} else if (!user.getUsername().equals(memberDto.getUsername())
				|| !user.getEmail().equals(memberDto.getEmail())) {
			return new ResponseEntity<MemberDto>(result, HttpStatus.OK);
		}
		return new ResponseEntity<MemberDto>(user, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<MemberDto> register(@RequestBody MemberDto memberDto) throws Exception {
		MemberDto user = memberService.userInfo(memberDto.getUserid());
		//중복되는 아이디가 없으면
		if (user == null) {
			memberService.userRegister(memberDto);
			user = memberDto;
		} else {
			user = null;
		}
		//성공 시, 등록된 유저 반환. 실패 시, null 반환
		return new ResponseEntity<MemberDto>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/{userid}")
	@ResponseBody
	public String delete(@PathVariable("userid") String userid) throws Exception {
		System.out.println(userid);
		memberService.userDelete(userid);
		return SUCCESS;
	}
	
	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<MemberDto> update(@RequestBody MemberDto memberDto) throws Exception {
		memberService.userUpdate(memberDto);
		return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
	}

}
