package com.sist.dao;
/*
 *  NO         NUMBER   =======> int      
	TITLE      VARCHAR2(500)  
	POSTER     VARCHAR2(260)  
	CONTENT    VARCHAR2(4000) 
	AUTHOR     VARCHAR2(50)   
	PRICE      VARCHAR2(20)   
	REGDATE    VARCHAR2(30)   
	ISBN       VARCHAR2(20)   
	TAGS       VARCHAR2(4000) ==> String
 */
public class BooksVO {
    private int no;
    private String title;
    private String poster;
    private String content;
    private String author;
    private String price;
    private String regdate;
    private String isbn;
    private String tags;
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
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	   
}
