package com.sist.main;
// ����Ŭ <=> �ڹ� ���� 
// �÷� <=> �������� ��ġ 
/*
 *   1. �����ͺ��̽� ���� 
 *      = ����Ŭ / �ڹ� => ��Ī (~VO)
 *      = ���� ����Ŭ ���� => SQL���� ���� => (~DAO)
 *      
 *   ����Ŭ                         �ڹ� 
 *   -----
 *   ����                          
 *    CHAR , VARCHAR2, CLOB       String
 *   ���� 
 *     ���� (NUMBER)               int
 *     �Ǽ� (NUMBER)               double
 *   ��¥
 *     DATE                       java.util.Date
 */
// ~VO�� �ݵ�� ĸ��ȭ ��� 
// => ����Ŭ ������ �ٷ� ����� �ƴ϶� => ��Ƽ� ������,����� 
// => ����Ŭ���� ������ ���۽� => ROW (Record����)
// => INSERT , UPADTE ,DELETE => ������ ���� 
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
