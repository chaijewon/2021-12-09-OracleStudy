package com.sist.main;
/*
 *    DEPTNO    NUMBER(2)     ==> int
      DNAME     VARCHAR2(14)  ==> String
      LOC       VARCHAR2(13)  ==> String
      
      => �ڹ� ����  <==> ����Ŭ �÷�  ===> Ŭ����(1��,1��) , row(1��,1��)
      
 */
// �μ� ���� 
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
