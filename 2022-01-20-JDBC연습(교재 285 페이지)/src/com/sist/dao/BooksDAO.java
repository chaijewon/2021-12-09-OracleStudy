package com.sist.dao;
import java.util.*;
import java.sql.*;
public class BooksDAO {
    private Connection conn;
    private PreparedStatement ps;
    private DataBase db=new DataBase();
    // 기능 설정 
    // 1. Books에 있는 목록 (번호, 제목 , 저자)
    public List<BooksVO> booksListDate(int page)
    {
    	List<BooksVO> list=new ArrayList<BooksVO>();
    	try
    	{
    		// 연결 
    		conn=db.getConnection(conn);
    		// SQL문장을 만든다 
    		String sql="SELECT no,title,price "
    				  +"FROM books "
    				  +"ORDER BY 1";
    		// SQL문장을 오라클로 전송 
    		ps=conn.prepareStatement(sql);
    		// 실행요청 
    		ResultSet rs=ps.executeQuery(); // SQL문장을 실행하고 데이터를 메모리에 저장 요청
    	    // 페이지를 나눠서 사용자에게 보내준다 
    		int i=0; // 10개씩 나눠주는 변수 
    		int j=0; // while이 돌아가는 횟수 
    		int pagecnt=(page*10)-10;
    		// 출력의 시작위치를 잡는다 
    		/*
    		 *   1page : 0 ~ 9
    		 *   2page : 10 ~ 19
    		 *   
    		 */
    		while(rs.next())
    		{
    			if(i<10 && j>=pagecnt)
    			{
    				BooksVO vo=new BooksVO();
    				vo.setNo(rs.getInt(1));
    				vo.setTitle(rs.getString(2));
    				vo.setPrice(rs.getString(3));
    				
    				list.add(vo);
    				i++;
    			}
    			j++;
    		}
    		rs.close();
    		
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		db.disConnection(conn, ps);
    	}
    	return list;
    }
}
