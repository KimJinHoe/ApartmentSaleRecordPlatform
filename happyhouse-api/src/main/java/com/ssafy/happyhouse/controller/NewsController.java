package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;
@RestController
@RequestMapping("/news")
public class NewsController {
	
	private final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@GetMapping("/list")
	public ResponseEntity<List<String>> list() throws IOException {
		String url1 = "https://news.google.com/search?q=%EC%95%84%ED%8C%8C%ED%8A%B8&hl=ko&gl=KR&ceid=KR%3Ako"; //구글뉴스 url
		Document doc = Jsoup.connect(url1).get();
		Elements el = doc.select(".DY5T1d");
		String str = "http://news.google.com/";
		List<String> list = el.eachText().subList(0, 100);
        List<String> linkList = el.eachAttr("href");
        for(int i=0; i<100; i++) {
        	list.add(str.concat(linkList.get(i)));
        }
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
}
