package com.ssafy.happyhouse.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NoticeDto {
	private int no;
	private String title;
	private String content;
	private String author;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate datetime;
	private int view;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public LocalDate getDatetime() {
		return datetime;
	}
	
	public void setDatetime(LocalDate datetime) {
		this.datetime = datetime;
	}
	
	public int getView() {
		return view;
	}
	
	public void setView(int view) {
		this.view = view;
	}

	@Override
	public String toString() {
		return "NoticeDto [no=" + no + ", title=" + title + ", content=" + content + ", author=" + author
				+ ", datetime=" + datetime + ", view=" + view + "]";
	}
}
