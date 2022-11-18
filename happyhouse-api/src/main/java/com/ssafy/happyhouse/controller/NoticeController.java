package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/notice")
public class NoticeController {
	private final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("")
	@ResponseBody
	public ResponseEntity<List<NoticeDto>> select() throws Exception {
		logger.debug("notice : {}", noticeService.selectAll());
		return new ResponseEntity<List<NoticeDto>>(noticeService.selectAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	@ResponseBody
	public String create(@RequestBody NoticeDto noticeDto) throws Exception {
		logger.debug("to create : {}", noticeDto);
		noticeService.create(noticeDto);
		return "success";
	}



	@GetMapping("/{no}")
	@ResponseBody
	public ResponseEntity<NoticeDto> select(@PathVariable("no") String no) throws Exception {
		logger.debug("notice : {}", noticeService.select(Integer.parseInt(no)));
		return new ResponseEntity<NoticeDto>(noticeService.select(Integer.parseInt(no)), HttpStatus.OK);
	}

	@PutMapping("/{no}")
	public String update(@PathVariable("no") int no, @RequestBody NoticeDto noticeDto) throws Exception {
		noticeDto.setNo(no);
		logger.debug("to update : {}", noticeDto);
		noticeService.update(noticeDto);
		return "success";
	}

	@DeleteMapping("/{no}")
	public String delete(@PathVariable("no") int no) throws Exception {
		NoticeDto noticeDto = new NoticeDto();
		noticeDto.setNo(no);
		System.out.println(noticeDto);
		logger.debug("to update : {}", noticeDto);
		noticeService.delete(noticeDto);
		return "success";
	}
}
