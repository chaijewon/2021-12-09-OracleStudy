package com.sist.main;
// ~VO => ROW(���ٿ� ����� �����͸� �޴� Ŭ����)
/*
 *   ����Ŭ�� �����͸� ����(���� => DML)
 *   GRADE    NUMBER  ==> int
     LOSAL    NUMBER  ==> int
     HISAL    NUMBER  ==> int
     
 */
// �޿� ���� 
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
