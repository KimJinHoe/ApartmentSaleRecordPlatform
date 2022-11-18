package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentDto : 도서 상세정보에 작성하는 도서평에 대한 글정보")
public class CommentDto {

	@ApiModelProperty(value = "댓글번호")
	private int commentno;
	@ApiModelProperty(value = "작성자이름")
	private String username;
	@ApiModelProperty(value = "댓글")
	private String comment;
	@ApiModelProperty(value = "작성시각")
	private String time;
	@ApiModelProperty(value = "글번호", example = "1")
	private int no;

	public int getCommentno() {
		return commentno;
	}

	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}

	public String getUsername() {
		return username;
	}

	public void setUser_name(String username) {
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "CommentDto [commentno=" + commentno + ", username=" + username + ", comment=" + comment
				+ ", time=" + time + ", no=" + no + "]";
	}

}
