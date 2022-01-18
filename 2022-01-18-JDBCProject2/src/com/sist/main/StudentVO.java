package com.sist.main;
// 오라클 <=> 자바 연동 
// 컬럼 <=> 데이터형 일치 
/*
 *   1. 데이터베이스 연동 
 *      = 오라클 / 자바 => 매칭 (~VO)
 *      = 실제 오라클 연결 => SQL문장 제어 => (~DAO)
 *      
 *   오라클                         자바 
 *   -----
 *   문자                          
 *    CHAR , VARCHAR2, CLOB       String
 *   숫자 
 *     정수 (NUMBER)               int
 *     실수 (NUMBER)               double
 *   날짜
 *     DATE                       java.util.Date
 */
// ~VO는 반드시 캡슐화 방식 
// => 오라클 연결후 바로 출력이 아니라 => 모아서 브라우저,모바일 
// => 오라클에서 데이터 전송시 => ROW (Record단위)
// => INSERT , UPADTE ,DELETE => 한줄이 변경 
public class StudentVO {
    private int hakbun;
    private String name;
    private int kor;
    private int eng;
    private int math;
	public int getHakbun() {
		return hakbun;
	}
	public void setHakbun(int hakbun) {
		this.hakbun = hakbun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
   
}
