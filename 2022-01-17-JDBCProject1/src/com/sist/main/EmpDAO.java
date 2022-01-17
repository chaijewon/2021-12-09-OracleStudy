package com.sist.main;
// 오라클 연결 => 송수신 ==> VO,DAO ,Manager (변경:일반 main, JSP, Spring)
import java.util.*; // List
import java.sql.*; // Connection(연결) , PreparedStatement(송수신)
// ResultSet (결과값을 저장해주는 공간)
/*
 *   DDL / DML을 사용할 수 있다 
 *   DDL 
 *    CREATE TABLE , DROP , RENAME , ALTER .... (오라클내에서 보통 처리)
 *    DML (SELECT , INSERT , UPDATE , DELETE)
 *    ---------- 웹,데이터베이스 프로그래머 => 사용자의 SQL을 대신 만들어서 처리
 *    ---------- 사용자에 요청 (데이터베이스에 필요한 => 데이터만 입력)
 */
public class EmpDAO {
   // 연결 객체 ==> Socket
   private Connection conn; //interface => 모든 데이터베이스 처리가 가능 
   // 오라클 , MS-SQL , MySql .... (드라이버명을  변경하면)
   // 1. 드라이버명 , 2. URL , 3. username , 4. password
   // 여기서 사용하는 SQL문장을 표준화가 되어 있다 
   // SQL문장 전송 ==> BufferedReader / OutputStream 
   private PreparedStatement ps; // 미리 SQL문장을 전송 => 나중에 값을 채워서 실행 
   // 오라클 주소(1521) : MS-SQL(1433) , MYSQL(3603)
   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   // HTML/CSS => JSP (MVC) => Spring / AWS
   // MyBatis / React / Vue / Spring-Boot / Chatbot 
   // MariaDB 
   // 드라이버 설정 => 1번 설정 (생성자)
   // => class화 => 재사용으로 활용 => .jar 
   public EmpDAO()
   {
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   // OracleDriver => 읽어오는 클래스 (DriverManager)
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   }
   // 오라클 연결 : 285page ~ 293page => 웹프로그래머의 핵심
   public void getConnection()
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
	   }catch(Exception ex){}
   }
   // 오라클 해제 
   public void disConnection()
   {
	   try
	   {
		   // 실제 통신하는 클래스 닫기 (송수신 => ps)
		   if(ps!=null) ps.close();
		   // 실제 통신 기기 클래스 닫기 (핸드폰 역할 => conn)
		   if(conn!=null) conn.close();
	   }catch(Exception ex) {}
   }
   // 반복이 있는 경우에는 => 메소드화 , 오라클 => 반복적으로 수행 => 함수화 
   //                             PS/SQL 270page ~ 284page 
   //                             함수(Function) , 프로시저 (기능) 
   //                             자동화 처리 (트리거) 
   //                             -----> 자바와 연결 (CallableStatement)
   /*
    *    기본 사이트 : 로그인 , 회원가입(아이디 중복,아이디 찾기 , 우편번호 검색)
    *               공지사항
    */
   // 기능 
   // 1. 사원 목록 출력 (14명 => 1명 <=> EmpVO) => EmpVO를 14개를 묶어준다 
   // List
   public List<EmpVO> empListData() // 인원이 많은 경우 => 페이지 나누기 
   {
	   // 리턴형 
	   List<EmpVO> list=new ArrayList<EmpVO>();
	   try
	   {
		   // 정상적으로 수행 => 처리 
		   /*
		    *    conn hr/happy
                 연결되었습니다.
		    */
		   //1. 연결 
		   getConnection();
		   //2. SQL문장을 작성 
		   String sql="SELECT * FROM emp";
		   //3. SQL문장을 오라클로 전송 
		   ps=conn.prepareStatement(sql);
		   //4. 실행후에 결과값을 가지고 온다 
		   ResultSet rs=ps.executeQuery();
		   while(rs.next()) // 데이터 출력의 첫번째줄에 커서를 위치 
			   // 밑으로 내려가면서 한줄씩 읽어 온다 
			   // next()는 할줄씩 읽어 온다 
		   {
			   EmpVO vo=new EmpVO();// 한명에 대한 정보 모아준다 
			   // 8개의 데이터를 동시에 저장 
			   vo.setEmpno(rs.getInt(1));
			   vo.setEname(rs.getString(2));
			   vo.setJob(rs.getString(3));
			   vo.setMgr(rs.getInt(4));
			   vo.setHiredate(rs.getDate(5));
			   vo.setSal(rs.getInt(6));
			   vo.setComm(rs.getInt(7));
			   vo.setDeptno(rs.getInt(8));
			   
			   list.add(vo); // 14명에 대한 정보를 모아서 처리 
			   /*
			    *   전체 목록 ===========> List
			    *   한명 ,한개 정보 ------> 해당 ~VO
			    */
		   }
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
		   // 에러 확인 
	   }
	   finally
	   {
		   // 닫기 
		   disConnection();
	   }
	   return list;
   }
   // 2. 부서 목록 출력
   public List<DeptVO> deptListData()
   {
	   List<DeptVO> list=new ArrayList<DeptVO>();
	   /*
	    *   List (순서가 있다 (인덱스) , 중복을 허용) => interface
	    *    |
	    *   ----------------------------
	    *   |            |             |
	    *   ArrayList  Vector       Queue  => List를 구현하고 있는 클래스
	    *                              |
	    *                           LinkedList
	    *   => 저장을 할때 => Object => 원하는 데이터형으로 변경 : 제네릭스 
	    */
	   try
	   {
		   //1. 연결 
		   getConnection();
		   //2. SQL문장 
		   String sql="SELECT * FROM dept";
		   // * =>테이블 출력하는 순서로 값을 읽는다 
		   // 주의점 => 공백 
		   //3. 오라클로 SQL문장 전송 
		   ps=conn.prepareStatement(sql);
		   //4. 실행후 결과값을 메모리에 저장해 둔다 
		   ResultSet rs=ps.executeQuery();
		   //5. 클라이언트로 전송하기 위해 List에 데이터를 묶어 둔다 
		   while(rs.next())
		   {
			   DeptVO vo=new DeptVO();
			   vo.setDeptno(rs.getInt(1)); // 오라클 데이터는 1번부터 
			   vo.setDname(rs.getString(2));
			   vo.setLoc(rs.getString(3));
			   // vo => ROW에 있는 데이터만 저장 
			   
			   list.add(vo); // list => 테이블의 모든 정보를 가지고 있다 
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
	   return list;
   }
   // 3. 등급 목록 출력 => salgrade  
   public List<SalGradeVO> salgradeListData()
   {
	   List<SalGradeVO> list=new ArrayList<SalGradeVO>();
	   try
	   {
		   //1. 연결 
		   getConnection();
		   //2. SQL문장 
		   String sql="SELECT * FROM salgrade";
		   //3. 오라클로 전송 
		   ps=conn.prepareStatement(sql);
		   //4. 실행 결과를 메모리에 저장 => ResultSet 
		   //*** ? (사용자 보내준 데이터가 있는 경우에는 => ?에 값을 채운후에 실행)
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   SalGradeVO vo=new SalGradeVO();
			   vo.setGrade(rs.getInt("grade")); // 컬럼명 / 인덱스 
			   vo.setLosal(rs.getInt("losal"));
			   vo.setHosal(rs.getInt("hisal"));
			   // 함수나 별칭을 사용시에는 ==> 인덱스를 이용하는 경우가 더 많다
			   
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   // 오류 확인 
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // 닫기 
		   disConnection();
	   }
	   return list;
   }
   // 4. 사원,부서 => JOIN => 자바에서는 포함클래스 
   public List<EmpVO> empDeptJoinData()
   {
	   List<EmpVO> list=new ArrayList<EmpVO>();
	   try
	   {
		   // 1. 연결 
		   getConnection();
		   // 2. SQL문장 
		   String sql="SELECT empno,ename,job,hiredate,sal,"
				     +"dname,loc "
				     +"FROM emp,dept "
				     +"WHERE emp.deptno=dept.deptno";
		   // 3. SQL문장 전송 
		   ps=conn.prepareStatement(sql);
		   // 4. 실행후 결과값을 메모리제 저장 
		   ResultSet rs=ps.executeQuery();
		   // 5. 메모리에 저장된 데이터를 List에 추가 
		   while(rs.next())
		   {
			   EmpVO vo=new EmpVO(); //Row한개씩 채운다 
			   vo.setEmpno(rs.getInt(1));
			   vo.setEname(rs.getString(2));
			   vo.setJob(rs.getString(3));
			   vo.setHiredate(rs.getDate(4));
			   vo.setSal(rs.getInt(5));
			   vo.getDvo().setDname(rs.getString(6));
			   vo.getDvo().setLoc(rs.getString(7));
			   
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   // 오류 처리
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // 닫기
		   disConnection();
	   }
	   return list;
   }
   // 5. 사원,등급 => JOIN (NON-EQUI-JOIN) =을 사용하지 않는다 
   // BETWEEN ~ AND 
   // INNER JOIN
   // => ANSI JOIN
   public List<EmpVO> empSalgradeJoinData()
   {
	   List<EmpVO> list=new ArrayList<EmpVO>();
	   try
	   {
		   // 1. 오라클 연결 
		   getConnection();
		   // 2. SQL문장 제작 
		   String sql="SELECT empno,ename,job,hiredate,sal,grade "
				     +"FROM emp JOIN salgrade "
				     +"ON sal BETWEEN losal AND hisal";
		   // 3. SQL문장 전송 
		   ps=conn.prepareStatement(sql);
		   // 4. 실행후에 메모리에 결과값 저장 
		   ResultSet rs=ps.executeQuery();
		   // 5. 메모리에 저장된 내용을 클라이언트가 볼 수 있게 List에 저장
		   while(rs.next())
		   {
			   EmpVO vo=new EmpVO();
			   vo.setEmpno(rs.getInt(1));
			   vo.setEname(rs.getString(2));
			   vo.setJob(rs.getString(3));
			   vo.setHiredate(rs.getDate(4));
			   vo.setSal(rs.getInt(5));
			   vo.getSvo().setGrade(rs.getInt(6));
			   
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   // 오류 처리
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // 닫기
		   disConnection();
	   }
	   return list;
   }
   /*
    *      INNER JOIN : 가장 많이 사용되는 조인 
    *        EQUI_JOIN(연산자 =)
    *        
    *        SELECT ~ 
    *        FROM A,B
    *        WHERE A.col=B.col
    *        
    *        SELECT ~
    *        FROM A JOIN B
    *        ON A.col=B.col 
    *        
    *        NON_EQUI_JOIN(연산자 =이 아닌 다른 연산자:BETWEEN,IN,비교,논리)
    *      
    *        SELECT ~
    *        FROM A,B
    *        WHERE sal BETWEEN 값 AND 값 
    *        
    *        SELECT ~
    *        FROM A JOIN B
    *        ON sal BETWEEN 값 AND 값 
    *        
    *      OUTER JOIN => NULL이 있는 경우에 데이터 읽기 
    *        LEFT OUTER JOIN
    *        SELECT ~ 
    *        FROM A,B
    *        WHERE A.col=B.col(+)
    *        
    *        SELECT ~
    *        FROM A LEFT OUTER JOIN B
    *        ON A.col=B.col 
    *        
    *        
    *        RIGTH OUTER JOIN 
    *        SELECT ~ 
    *        FROM A,B
    *        WHERE A.col(+)=B.col
    *        
    *        SELECT ~
    *        FROM A RIGTH OUTER JOIN B
    *        ON A.col=B.col 
    */
   // 6. 사원,부서,등급 => JOIN
   public List<EmpVO> empDeptSalgradeJoinData()
   {
	   List<EmpVO> list=new ArrayList<EmpVO>();
	   try
	   {
		   //1. 오라클 연결 
		   getConnection();
		   //2. SQL문장 
		   /*String sql="SELECT empno,ename,job,hiredate,sal,"//emp
				     +"dname,loc," // dept 
				     +"grade " // salgrade
				     +"FROM emp,dept,salgrade "
				     +"WHERE emp.deptno=dept.deptno "
				     +"AND sal BETWEEN losal AND hisal";*/
		   String sql="SELECT empno,ename,job,hiredate,sal,"
				     +"dname,loc,"
				     +"grade "
				     +"FROM emp JOIN dept "
				     +"ON emp.deptno=dept.deptno "
				     +"JOIN salgrade "
				     +"ON sal BETWEEN losal AND hisal";
		   //3. SQL문장 => 오라클로 전송 
		   ps=conn.prepareStatement(sql);
		   //4. 실행후에 메모리에 저장 요청 
		   ResultSet rs=ps.executeQuery();
		   //5. 메모리에 있는 데이터를 List로 이동 
		   while(rs.next())
		   {
			   EmpVO vo=new EmpVO();
			   vo.setEmpno(rs.getInt(1));
			   vo.setEname(rs.getString(2));
			   vo.setJob(rs.getString(3));
			   vo.setHiredate(rs.getDate(4));
			   vo.setSal(rs.getInt(5));
			   ///////////////////////////////////////
			   vo.getDvo().setDname(rs.getString(6));
			   vo.getDvo().setLoc(rs.getString(7));
			   /////////////////// DeptVO
			   vo.getSvo().setGrade(rs.getInt(8));
			   /////////////////// SalGradeVO
			   
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   // 오류 처리 
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // 닫기
		   disConnection();
		   
	   }
	   return list;
   }
   // 사용자 요청값이 있는 경우 => ?
   // 7788의 사번을 가지고 있는 사원의 사번,이름,직위,입사일,근무지,부서명,급여등급 
   // 사원 1명의 데이터를 출력 => EmpVO
   /*
    *   화면 출력 
    *   -------
    *   1. 목록 ==> List
    *   2. 목록에서 한개를 클릭 => 상세보기 ==> ~VO 
    *   3. 일반 변수 => boolean(로그인처리, 아이디 중복) , int (총페이지)
    *   4. insert , update ,delete => 오라클 자체 처리 => void 
    *   5. 제목만 , 장르만 , 가수만 , 출연만 .... List<String>
    *   
    *   ------------------------------------------------------
    *   MovieVO => 영화 1개에 대한 정보를 가지고 있다 
    *           => List<MovieVO> : 영화 여러개를 묶는다 
    *           => 극장명 (날짜 => 시간 => 좌석)
    *   EmpVO   => 사원 1명에 대한 정보
    *           => 부서 , 등급 
    *   MusicVO => 음악 1개에 대한 정보
    *   FoodVO  => 맛집 1개에 대한 정보
    *   RecipeVO => 레시피 1개에 대한 정보 
    *   ------------ 해당 테이블의 컬럼명과 일치 + 추가가 가능 ---------
    *   
    *   자바 <=======> 오라클 
    *      1. 오라클에 있는 데이터를 자바에서 받는다 
    *         오라클 (컬럼) = 자바(멤버변수) 
    *      2. 여러개를 가지고 올때는 List에 묶어서 처리 
    */
   public EmpVO empDetailData(int empno)
   {
	   EmpVO vo=new EmpVO();
	   try
	   {
		   // 1. 오라클 연결 
		   getConnection();
		   // 2. SQL문장 제작 (사번,이름,직위,입사일,근무지,부서명,급여등급)
		   String sql="SELECT empno,ename,job,hiredate,"
				     +"loc,dname,"
				     +"grade,sal "
				     +"FROM emp,dept,salgrade "
				     +"WHERE emp.deptno=dept.deptno "
				     +"AND sal BETWEEN losal AND hisal "
				     +"AND empno=?";
		   // 3. SQL문장을 오라클로 전송 
		   ps=conn.prepareStatement(sql);
		   
		   // 4. ?에 값을 채워준다 
		   ps.setInt(1, empno); // ?는 번호가 1번부터 '7788'
		   // 1번째에 있는 ?에값을 채운다 
		   // *** ps.setString(2,"홍길동") ==> '홍길동'로 변환한다
		   // WHERE ename=홍길동 (X)
		   // WHERE ename='홍길동' (O) => 날짜,문자열은 반드시 ''
		   // 5. ?에 값을 채웠으면 => 실행요청 => 메모리에 저장 
		   ResultSet rs=ps.executeQuery();
		   rs.next();// 데이터 출력 위치에 커서 이동 
		   // 6. EmpVO에 값을 채운다 
		   vo.setEmpno(rs.getInt(1));
		   vo.setEname(rs.getString(2));
		   vo.setJob(rs.getString(3));
		   vo.setHiredate(rs.getDate(4));
		   ////////////////// DeptVO => getDvo()
		   vo.getDvo().setLoc(rs.getString(5));
		   vo.getDvo().setDname(rs.getString(6));
		   ////////////////// SalGradeVO => getSvo()
		   vo.getSvo().setGrade(rs.getInt(7));
		   vo.setSal(rs.getInt(8));
		   
		   rs.close();
		   
	   }catch(Exception ex)
	   {
		   // 오류 발생 
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // 닫기 
		   disConnection();
	   }
	   return vo;
   }
   // 7. SubQuery 
}









