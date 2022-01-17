package com.sist.main;
// ~VO => ROW(한줄에 저장된 데이터를 받는 클래스)
/*
 *   오라클은 데이터만 저장(관리 => DML)
 *   GRADE    NUMBER  ==> int
     LOSAL    NUMBER  ==> int
     HISAL    NUMBER  ==> int
     
 */
// 급여 정보 
public class SalGradeVO {
    private int grade;
    private int losal;
    private int hosal;
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getLosal() {
		return losal;
	}
	public void setLosal(int losal) {
		this.losal = losal;
	}
	public int getHosal() {
		return hosal;
	}
	public void setHosal(int hosal) {
		this.hosal = hosal;
	}
  
}
