package com.sist.main;
import java.util.*;
//snfcnscns
public class MainClass {
	static StudentDAO dao=new StudentDAO();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // 1. ����Ŭ ���� 
		
		// 2. ����� �Է� 
		/*Scanner scan=new Scanner(System.in);
		System.out.print("�̸� �Է�:");
		String name=scan.next();
		System.out.print("���� ����:");
		int kor=scan.nextInt();
		System.out.print("���� ����:");
		int eng=scan.nextInt();
		System.out.print("���� ����:");
		int math=scan.nextInt();
		
		// ����ڰ� �Է��� ���� ��� ����Ŭ�� ���� 
		StudentVO vo=new StudentVO();
		vo.setName(name);
		vo.setEng(eng);
		vo.setMath(math);
		vo.setKor(kor);
		
		// ����Ŭ���� INSERT��û 
		dao.studentInsert(vo);
		System.out.println("***** ����Ŭ ������ �߰� �Ϸ� *****");*/
		
		// ��ü �л� ��� 
		studentPrint();
		
		// ���� 
		/*Scanner scan=new Scanner(System.in);
		System.out.print("������ �й� ����:");
		int hakbun=scan.nextInt();
		System.out.print("���� ����:");
		int kor=scan.nextInt();
		System.out.print("���� ����:");
		int eng=scan.nextInt();
		System.out.print("���� ����:");
		int math=scan.nextInt();
		// �Էµ� �����͸� ��Ƽ� DAO�� ���� 
		StudentVO vo=new StudentVO();
		vo.setHakbun(hakbun);
		vo.setKor(kor);
		vo.setEng(eng);
		vo.setMath(math);
		
		dao.studentUpdate(vo);
		
		System.out.println("***** ������ �Ϸ�Ǿ����ϴ� *****");
		// ��ü �л� ��� 
		studentPrint();*/
		
		// ���� 
		Scanner scan=new Scanner(System.in);
		System.out.print("������ �й� ����:");
		int hakbun=scan.nextInt();
		// DAO�� ������û 
		dao.studentDelete(hakbun);
		System.out.println("***** ���� �Ϸ� *****");
		// ��ü �л� ���
		studentPrint();

		
	}
	public static void studentPrint()
	{
		// �л� ��� ��û 
		List<StudentVO> list=dao.studentListData();
		// ����ڿ��� ��� 
		for(StudentVO vo:list)
		{
			System.out.println(vo.getHakbun()+" "
							+vo.getName()+" "
							+vo.getKor()+" "
							+vo.getEng()+" "
							+vo.getMath());
		}
	}

}
