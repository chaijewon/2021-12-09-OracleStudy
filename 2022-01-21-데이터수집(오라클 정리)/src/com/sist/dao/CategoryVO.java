package com.sist.dao;
// 오라클에서 읽어올 데이터를 저장 하는 공간 (값만 가지고 있는 클래스) 
/*
 *   ~VO => 값만 가지고 있는 클래스 (스프링)
 *   ~DTO => 데이터를 모아서 한번에 전송할 목적 (마이바티스)
 *   ~Bean => JSP에서 핵심 
 *   ----------------------------------------------
 *   프로그램은 데이터 관리 목적 
 *           --------- 변수 , 배열(같은 데이터형) , 클래스(다른 데이터형을 모아서 관리)
 *           클래스 =>  데이터만 관리하는 클래스 (~VO) 
 *                    데이터를 처리하는 클래스 (~DAO , ~Manager)
 *                    
 *  오라클 => Category
 *  CNO     NOT NULL NUMBER   ===> int , double  
	TITLE   NOT NULL VARCHAR2(200) ==> String
	SUBJECT NOT NULL VARCHAR2(200) 
	POSTER  NOT NULL VARCHAR2(260) 
	LINK    NOT NULL VARCHAR2(100)
	                CHAR , CLOB 
	                DATE ============> java.util.Date
 */
public class CategoryVO {
    private int cno;
    private String title;
    private String subject;
    private String poster;
    private String link;
    
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
   
}
