package com.sist.main;
// => 자바(main) => JSP(브라우저)
/*
 * 
 *    클래스의 용도 (웹)
 *    ----------
 *     데이터베이스 , 외부 데이터 크롤링 => 저장할 변수 (~VO)
 *          => VO (테이블별로 따로 설정)
 *     데이터베이스 연결 (송,수신) => ~DAO
 *          => JOIN이나 , SubQuery이외에는 테이블당 1개씩 제작 
 *     외부데이터 크롤링 (송,수신) => ~Manager (뉴스읽기 , 맛집 크롤링)
 *          => 사이트한개에 한개씩 만든다 
 *     유저들이 보는 프로그램 => main메소드를 가지고 있는 클래스 
 *     
 *     -----------------------------------------------------
 *     
 */
import java.util.*;
public class ClientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // 오라클 => 오라클연결 클래스 => DAO
		EmpDAO dao=new EmpDAO();
		// 1. 사원 정보 전체 출력 
		List<EmpVO> list=dao.empListData();
		// 2. 출력 
		System.out.println("===== 사원 목록 =======");
		for(EmpVO vo:list)
		{
			// 문자열 => null , 숫자 => 0
			System.out.println(vo.getEmpno()+" "
					          +vo.getEname()+" "
					          +vo.getJob()+" "
					          +vo.getMgr()+" "
					          +vo.getHiredate().toString()+" "
					          +vo.getSal()+" "
					          +vo.getComm()+" "
					          +vo.getDeptno());
		}
		System.out.println("\n");
		System.out.println("===== 부서 정보 목록 ======");
		List<DeptVO> dList=dao.deptListData();
		for(DeptVO vo:dList)
		{
			System.out.println(vo.getDeptno()+" "
					+vo.getDname()+" "
					+vo.getLoc());
		}
		System.out.println("\n");
		System.out.println("===== 급여 정보 목록 ======");
		List<SalGradeVO> sList=dao.salgradeListData();
		for(SalGradeVO vo:sList)
		{
			System.out.println(vo.getGrade()+" "
					+vo.getLosal()+" "
					+vo.getHosal());
		}
		
		System.out.println("\n");
		System.out.println("===== Emp와 Dept 조인 목록 =====");
		List<EmpVO> eList=dao.empDeptJoinData();
		for(EmpVO vo:eList)
		{
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getHiredate().toString()+" "
					+vo.getSal()+" "
					+vo.getDvo().getDname()+" "
					+vo.getDvo().getLoc());
		}
		
		System.out.println("\n");
		System.out.println("====== Emp와 Salgrade 조인 목록 =====");
		List<EmpVO> esList=dao.empSalgradeJoinData();
		for(EmpVO vo:esList)
		{
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getHiredate().toString()+" "
					+vo.getSal()+" "
					+vo.getSvo().getGrade());
		}
		
		System.out.println("\n");
		System.out.println("===== Emp,Dept,Salgrade 조인 목록 =====");
		List<EmpVO> edsList=dao.empDeptSalgradeJoinData();
		for(EmpVO vo:edsList)
		{
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getHiredate().toString()+" "
					+vo.getSal()+" "
					+vo.getDvo().getDname()+" "
					+vo.getDvo().getLoc()+" "
					+vo.getSvo().getGrade());
		}
		
		System.out.println("\n");
		System.out.println("===== 사용자가 보내준 데이터 첨부 후 결과값 =====");
		// 1. 사용자 요청 
		Scanner scan=new Scanner(System.in);
		System.out.print("사번 입력:");
		int empno=scan.nextInt();
		
		EmpVO vo=dao.empDetailData(empno);
		// 출력
		System.out.println("사번:"+vo.getEmpno());
		System.out.println("이름:"+vo.getEname());
		System.out.println("직위:"+vo.getJob());
		System.out.println("입사일:"+vo.getHiredate().toString());
		System.out.println("급여:"+vo.getSal());
		System.out.println("부서명:"+vo.getDvo().getDname());
		System.out.println("근무지:"+vo.getDvo().getLoc());
		System.out.println("호봉:"+vo.getSvo().getGrade());
		
		System.out.println("\n");
		System.out.println("==== JDBC에서 서브쿼리 이용 =====");
		List<EmpVO> subList=dao.empSubQueryData();
		for(EmpVO s:subList)
		{
			System.out.println(s.getEname()+" "
					+s.getJob()+" "
					+s.getHiredate().toString()+" "
					+s.getSal()+" "
					+s.getDvo().getDname()+" "
					+s.getDvo().getLoc());
		}
		
		System.out.println("\n");
		System.out.println("==== JDBC에서 서브쿼리 이용 =====");
		List<EmpVO> subList2=dao.empSubQueryData2();
		for(EmpVO s:subList2)
		{
			System.out.println(s.getEname()+" "
					+s.getJob()+" "
					+s.getHiredate().toString()+" "
					+s.getSal()+" "
					+s.getDvo().getDname()+" "
					+s.getDvo().getLoc());
		}
		
		System.out.println("\n");
		System.out.println("===== JOIN대신 스칼라서브쿼리 사용 =====");
		subList2=dao.empSubQueryListData();
		for(EmpVO s:subList2)
		{
			System.out.println(s.getEname()+" "
					+s.getJob()+" "
					+s.getHiredate().toString()+" "
					+s.getSal()+" "
					+s.getDvo().getDname()+" "
					+s.getDvo().getLoc());
		}
		
		System.out.println("\n");
		System.out.println("=====인라인뷰를 이용한 데이터 자르기 =====");
		subList2=dao.empTon5Data();
		for(EmpVO s:subList2)
		{
			System.out.println(s.getEname()+" "
					+s.getJob()+" "
					+s.getHiredate().toString()+" "
					+s.getSal());
		}
		
	}

}












