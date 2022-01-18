package com.sist.main;
import java.util.*;
public class MainClass {
	static StudentDAO dao=new StudentDAO();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // 1. 오라클 연결 
		
		// 2. 사용자 입력 
		/*Scanner scan=new Scanner(System.in);
		System.out.print("이름 입력:");
		String name=scan.next();
		System.out.print("국어 점수:");
		int kor=scan.nextInt();
		System.out.print("영어 점수:");
		int eng=scan.nextInt();
		System.out.print("수학 점수:");
		int math=scan.nextInt();
		
		// 사용자가 입력한 값을 묶어서 오라클로 전송 
		StudentVO vo=new StudentVO();
		vo.setName(name);
		vo.setEng(eng);
		vo.setMath(math);
		vo.setKor(kor);
		
		// 오라클에서 INSERT요청 
		dao.studentInsert(vo);
		System.out.println("***** 오라클 데이터 추가 완료 *****");*/
		
		// 전체 학생 출력 
		studentPrint();
		
		// 수정 
		/*Scanner scan=new Scanner(System.in);
		System.out.print("수정할 학번 선택:");
		int hakbun=scan.nextInt();
		System.out.print("국어 변경:");
		int kor=scan.nextInt();
		System.out.print("영어 변경:");
		int eng=scan.nextInt();
		System.out.print("수학 변경:");
		int math=scan.nextInt();
		// 입력된 데이터를 모아서 DAO로 전송 
		StudentVO vo=new StudentVO();
		vo.setHakbun(hakbun);
		vo.setKor(kor);
		vo.setEng(eng);
		vo.setMath(math);
		
		dao.studentUpdate(vo);
		
		System.out.println("***** 수정이 완료되었습니다 *****");
		// 전체 학생 출력 
		studentPrint();*/
		
		// 삭제 
		Scanner scan=new Scanner(System.in);
		System.out.print("삭제할 학번 선택:");
		int hakbun=scan.nextInt();
		// DAO로 삭제요청 
		dao.studentDelete(hakbun);
		System.out.println("***** 삭제 완료 *****");
		// 전체 학생 출력
		studentPrint();

		
	}
	public static void studentPrint()
	{
		// 학생 목록 요청 
		List<StudentVO> list=dao.studentListData();
		// 사용자에게 출력 
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
