package com.sist.dao;
import java.sql.*;
public class DataBase {
   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   public DataBase()
   {
	   // 드라이버 등록 
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	   }catch(Exception ex){}
   }
   public Connection getConnection(Connection conn)
   {
	   // 오라클 연결
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
	   }catch(Exception ex){}
	   
	   return conn;
   }
   public void disConnection(Connection conn,PreparedStatement ps)
   {
	   // 오라클 닫기 
	   try
	   {
		   if(ps!=null) ps.close();
		   if(conn!=null) conn.close();
	   }catch(Exception ex) {}
   }
}
