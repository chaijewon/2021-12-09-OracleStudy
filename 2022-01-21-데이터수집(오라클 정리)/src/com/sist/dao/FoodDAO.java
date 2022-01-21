package com.sist.dao;
/*
 *   JDBC : DML (SELECT , INSERT , UPDATE , DELETE) 
 *          => 사용자 요청시마다 직접 SQL문장을 만들어서 결과값을 사용자에 보내주는 역할
 *          
 *          프로그램 순서 : 오라클 연결 / 오라클 닫기 => 한사람당 1개만 연결이 가능 
 *                       => 디자인 패턴 (싱글턴) => DAO한개로 재사용
 *                       
 *          1) 드라이버 등록 
 *             Class.forName("oracle.jdbc.driver.OracleDriver")
 *             => 자바 <========> 오라클 
 *                      드라이버 => SW
 *          2) 오라클 연결 
 *             Connection => getConnection(URL,username,password)
 *                           => 오라클  conn username/password
 *          3) SQL문장 전송 
 *             PreparedStatement => conn.preparedStatement(SQL)
 *          4) 실행후 결과값 받기(SELECT)
 *             ResultSet rs=ps.executeQuery() => ~VO
 *          4-1) 실행만 요청 (INSERT , UPDATE , DELETE)
 *             ps.executeUpdate() : commit() 수행 
 *             => AutoCommit() : JDBC
 *          5) 오라클 연결 해제 
 *             ps.close() , conn.close()
 */
/*
 *    1. 카테고리 읽기 (cno , link , title) 
 *       데이터 검색 : SELECT 
 *       형식)
 *            SELECT * | column1,column2...
 *            FROM table_name 
 *            [
 *                WHERE 조건문 => 컬럼명|함수명 연산자 값
 *                GROUP BY 그룹컬럼|함수명  => 두개인 경우(컬럼명,컬럼명)
 *                HAVING 그룹조건 => GROUP BY가 있는 경우만 사용이 가능 
 *                ORDER BY 컬럼|함수 ASC/DESC 
 *                         -------
 *                         컬럼의 순서 (숫자) 
 *            ]
 *            
 *            실행순서 
 *            FROM 
 *            WHERE 
 *            SELECT 
 *            ORDER BY ==> ASC / DESC => ASC 생략이 가능 
 *                         -----------
 *                         INDEX_ASC() , INDEX_DESC() => 속도 향상
 *      2. 데이터 저장 : INSERT 
 *         형식) 
 *              ***1) 전체를 추가
 *                    INSERT INTO table_name VALUES(값..)
 *                                ----------       ------
 *                                 컬럼의 갯수         갯수와 일치 (순서)
 *              ***2) 원하는 컬럼값만 추가 (NULL값을 허용 , DEFAULT) 
 *                    INSERT INTO table_name(컬럼명1,컬럼명2..)
 *                    VALUES(값1,값2..)
 *      3. 데이터 수정 : UPADTE 
 *         형식)
 *               ----------------------
 *               UPDATE table_name SET
 *               컬럼명=값 , 컬럼명=값 ...
 *               ---------------------- 전체수정 (ROW)
 *               [WHERE 조건문] => 조건에 맞는 것만 수정 
 *      4. 데이터 삭제 : DELETE 
 *         형식) 
 *               -----------------------
 *               DELETE FROM table_name
 *               ----------------------- 전체삭제 (ROW)
 *               [WHERE 조건문] => 조건에 맞는 것만 삭제 
 *      *** 페이징 기법 , 검색 ==> 최근 면접 (포토폴리어) => 기본 자바  
 *              
 */
import java.util.*; // 데이터를 모아서 전송 : List(ArrayList)
import java.sql.*; // 오라클 연결 => Connection,PreparedStatement,ResultSet
public class FoodDAO {
   // 연결 객체 선언 
   private Connection conn; // Oracle , My-SQL , MariaDB , DB2 , SQLite 
   // interface => 서로 클래스를 묶어서 한번에 관리 목적 
   // 전송 객체 선언 (SQL)
   private PreparedStatement ps; 
   /*
    *   Statement : 값+SQL동시에 전송 => 단순한 문장 , 전송값이 없는 SQL 
    *   PreparedStatement : 미리 SQL문장을 전송하고 나중에 값을 채운후에 실행 
    *                        => Default
    *   CallableStatement : 프로시저 호출 
    */
   // 오라클 연결 주소(변경이 없는 상태 => 상수형) 
   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   
   // 1. 드라이버 등록 (한번만 등록) => 생성자 
   /*
    *   생성자 : 객체 생성(메모리 저장)할때 호출하는 함수 
    *          역할 : 멤버변수의 초기화를 담당한다 
    *                시작할때 기능이 필요한 경우에 주로 사용 
    *                ---------- 드라이버 설치 , 서버연결 , 자동로그인(ID읽기)
    *          특성 : 리턴형이 없다 , 여러개을 만들어서 사용이 가능(오버로딩)
    *                             -> 오버로딩 : 중복 메소드 정의 
    *                상속이 없다 (상속에서 예외 조건 => static , 생성자) 
    *                어느 클래스든 상관없이 사용이 가능 만든다 (public) 
    *                
    *   소멸자 : 객체가 메모리에서 제거 할때 자동 호출 : finalize()
    *          ------------------------------ gc()
    */
   public FoodDAO()
   {
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   /*
		    *   예외처리 : 8장
		    *   객체지향의 3대 특성 : 6장  
		    *   컬렉션 , 제네릭스 : 11,12장 
		    *   인터페이스 : 7장 
		    */
	   }catch(Exception ex)
	   {
		   ex.printStackTrace(); // ClassNotFoundException : 컴파일(체크)
		   // 체크에외 : 컴파일시에 예외처리가 있는 확인 
		   // 예외확인 , 복구 => try~catch 
		   // 예외회피 => throws 
	   }
   }
   // 2. 오라클 연결 
   public void getConnection()
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
		   // conn hr/happy
	   }catch(Exception ex) 
	   {
		   ex.printStackTrace();
	   }
   }
   // 3. 오라클 해제
   public void disConnection()
   {
	   try
	   {
		   if(ps!=null) ps.close(); // 송수신 
		   if(conn!=null) conn.close(); // 기계
	   }catch(Exception ex){}
   }
   // 4. 맛집과 관련된 기능 => DAO는 재사용 (기능별로 따로 만든다)
   /*
    *   게시판 : BoardVO ,BoardDAO
    *   회원가입 : MemberVO , MemberDAO 
    *   영화 : MovieVO , MovieDAO 
    *   추천 : ReserveVO , ReserveDAO 
    *   기본 => 테이블 (10~20)
    */
   // 4.1 카테고리 읽기 => SELECT 
   public List<CategoryVO> categoryListData()
   {
	   List<CategoryVO> list=new ArrayList<CategoryVO>();
	   try
	   {
		   // 정상적으로 수행 
		   // 1. 오라클 연결
		   getConnection();
		   // 2. SQL문장 
		   String sql="SELECT cno,title,link "
				     +"FROM category "
				     +"ORDER BY cno ASC";
		   // 3. 오라클로 전송 
		   ps=conn.prepareStatement(sql);
		   // 4. ?(전송값이 있는지 확인) 
		   // 5. 실행 요청 => 메모리에 오라클에서 실행된 데이터를 저장 
		   ResultSet rs=ps.executeQuery();
		   // 6. List에 값을 채워서 => 사용자에게 전송 
		   while(rs.next()) // 처음 출력위치 => 마지막까지 읽는다 
		   {
			   CategoryVO vo=new CategoryVO();
			   vo.setCno(rs.getInt(1));
			   vo.setTitle(rs.getString(2));
			   vo.setLink(rs.getString(3));
			   
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex) // SQLException => 상위클래스 : Exception,Throwable
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // conn을 한개만 사용 => 오류,정상 => 무조건 오라클 닫는다 
		   disConnection();
	   }
	   return list;
   }
   // 수집된 데이터를 오라클에 저장 
   /*
    *   NO      NOT NULL NUMBER         
		CNO              NUMBER         
		POSTER  NOT NULL VARCHAR2(4000) 
		NAME    NOT NULL VARCHAR2(200)  
		SCORE   NOT NULL NUMBER(2,1)    
		ADDRESS NOT NULL VARCHAR2(500)  
		TEL     NOT NULL VARCHAR2(20)   
		TYPE    NOT NULL VARCHAR2(100)  
		PRICE   NOT NULL VARCHAR2(100)  
		TIME             VARCHAR2(200)  
		MENU             CLOB           
		GOOD             NUMBER         
		SOSO             NUMBER         
		BAD              NUMBER 
		
		// 메소드 => 매개변수 (3이상 초과하지 않는다)
		 *   => 배열 , class 
    */
   public void foodInsert(FoodHouseVO vo)
   {
	   try
	   {
		   //1. 연결
		   getConnection();
		   //2. SQL문장 제작 
		   String sql="INSERT INTO foodHouse VALUES("
				     +"(SELECT NVL(MAX(no)+1,1) FROM foodHouse),"
				     +"?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		   /*
		    *   String sql="INSERT INTO foodHouse VALUES("
				     +"(SELECT NVL(MAX(no)+1,1) FROM foodHouse),"
				     +vo.getCno()+",'"+vo.getPoster()+"','"+?,?,?,?,?,?,?,?,?,?,?,?)";
		    */
		   //3. SQL문장 오라클로 전송 
		   ps=conn.prepareStatement(sql);
		   //4. ?에 값을 채운다 
		   ps.setInt(1, vo.getCno());
		   ps.setString(2, vo.getPoster()); // => 'aaa'
		   ps.setString(3, vo.getName());
		   ps.setDouble(4, vo.getScore());
		   ps.setString(5, vo.getAddress());
		   ps.setString(6, vo.getTel());
		   ps.setString(7, vo.getType());
		   ps.setString(8, vo.getPrice());
		   ps.setString(9, vo.getTime());
		   ps.setString(10, vo.getMenu());
		   ps.setInt(11, vo.getGood());
		   ps.setInt(12, vo.getSoso());
		   ps.setInt(13, vo.getBad());
		   ps.setString(14, vo.getParking());
		   
		   // 실행 명령
		   ps.executeUpdate(); // commit() => 자동으로 저장 
	   }catch(Exception ex)
	   {
		   // 오류 확인 
		   ex.printStackTrace();
	   }
	   finally
	   {
		   //  닫기
		   disConnection();
	   }
   }
   
}






