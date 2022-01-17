package com.sist.main;
/*
 *    DEPTNO    NUMBER(2)     ==> int
      DNAME     VARCHAR2(14)  ==> String
      LOC       VARCHAR2(13)  ==> String
      
      => 자바 변수  <==> 오라클 컬럼  ===> 클래스(1명,1개) , row(1명,1개)
      
 */
// 부서 정보 
public class DeptVO {
    private int deptno;
    private String dname;
    private String loc;
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	   
}
