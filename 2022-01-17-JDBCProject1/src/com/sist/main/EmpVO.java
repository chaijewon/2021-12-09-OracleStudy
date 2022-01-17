package com.sist.main;
/*
 *      EMPNO    NOT NULL NUMBER(4)   ==> int  
		ENAME             VARCHAR2(10) => String
		JOB               VARCHAR2(9)  => String
		MGR               NUMBER(4)    => int
		HIREDATE          DATE         => java.util.Date
		                  -------------
		SAL               NUMBER(7,2)  => int
		COMM              NUMBER(7,2)  => int
		                  ------------- 저정된 데이터가 정수형 
		DEPTNO            NUMBER(2)    => int 
 */
// 사원 정보 
import java.util.*;
public class EmpVO {
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hiredate;
    private int sal;
    private int comm;
    private int deptno;
    
    // JOIN이나 SbuQuery를 이용하는 경우에 처리 (클래스 포함해서 사용)
    private DeptVO dvo=new DeptVO();
    private SalGradeVO svo=new SalGradeVO();
    
    
    
	public DeptVO getDvo() {
		return dvo;
	}
	public void setDvo(DeptVO dvo) {
		this.dvo = dvo;
	}
	public SalGradeVO getSvo() {
		return svo;
	}
	public void setSvo(SalGradeVO svo) {
		this.svo = svo;
	}
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
   
}
