package com.sist.main;
// => �ڹ�(main) => JSP(������)
/*
 *    Ŭ������ �뵵 (��)
 *    ----------
 *     �����ͺ��̽� , �ܺ� ������ ũ�Ѹ� => ������ ���� (~VO)
 *          => VO (���̺��� ���� ����)
 *     �����ͺ��̽� ���� (��,����) => ~DAO
 *          => JOIN�̳� , SubQuery�̿ܿ��� ���̺�� 1���� ���� 
 *     �ܺε����� ũ�Ѹ� (��,����) => ~Manager (�����б� , ���� ũ�Ѹ�)
 *          => ����Ʈ�Ѱ��� �Ѱ��� ����� 
 *     �������� ���� ���α׷� => main�޼ҵ带 ������ �ִ� Ŭ���� 
 */
import java.util.*;
public class ClientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // ����Ŭ => ����Ŭ���� Ŭ���� => DAO
		EmpDAO dao=new EmpDAO();
		// 1. ��� ���� ��ü ��� 
		List<EmpVO> list=dao.empListData();
		// 2. ��� 
		System.out.println("===== ��� ��� =======");
		for(EmpVO vo:list)
		{
			// ���ڿ� => null , ���� => 0
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
		System.out.println("===== �μ� ���� ��� ======");
		List<DeptVO> dList=dao.deptListData();
		for(DeptVO vo:dList)
		{
			System.out.println(vo.getDeptno()+" "
					+vo.getDname()+" "
					+vo.getLoc());
		}
		System.out.println("\n");
		System.out.println("===== �޿� ���� ��� ======");
		List<SalGradeVO> sList=dao.salgradeListData();
		for(SalGradeVO vo:sList)
		{
			System.out.println(vo.getGrade()+" "
					+vo.getLosal()+" "
					+vo.getHosal());
		}
		
		System.out.println("\n");
		System.out.println("===== Emp�� Dept ���� ��� =====");
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
		System.out.println("====== Emp�� Salgrade ���� ��� =====");
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
		System.out.println("===== Emp,Dept,Salgrade ���� ��� =====");
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
		System.out.println("===== ����ڰ� ������ ������ ÷�� �� ����� =====");
		// 1. ����� ��û 
		Scanner scan=new Scanner(System.in);
		System.out.print("��� �Է�:");
		int empno=scan.nextInt();
		
		EmpVO vo=dao.empDetailData(empno);
		// ���
		System.out.println("���:"+vo.getEmpno());
		System.out.println("�̸�:"+vo.getEname());
		System.out.println("����:"+vo.getJob());
		System.out.println("�Ի���:"+vo.getHiredate().toString());
		System.out.println("�޿�:"+vo.getSal());
		System.out.println("�μ���:"+vo.getDvo().getDname());
		System.out.println("�ٹ���:"+vo.getDvo().getLoc());
		System.out.println("ȣ��:"+vo.getSvo().getGrade());
		
		System.out.println("\n");
		System.out.println("==== JDBC���� �������� �̿� =====");
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
		System.out.println("==== JDBC���� �������� �̿� =====");
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
		System.out.println("===== JOIN��� ��Į�󼭺����� ��� =====");
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
		System.out.println("=====�ζ��κ並 �̿��� ������ �ڸ��� =====");
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












