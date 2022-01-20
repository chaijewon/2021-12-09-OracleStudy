package com.sist.main;
// 웹 => JSP파일 (HTML)
/*
 *   오라클 => DML
 */
import java.util.*;
import com.sist.dao.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // 1. 페이지 요청 
		Scanner scan=new Scanner(System.in);
		System.out.print("페이지 입력:");
		int page=scan.nextInt();
		// 해당페이지에 값을 읽어와서 출력 
		// 2. DB연결 => DAO 
		BooksDAO dao=new BooksDAO();
		List<BooksVO> list=dao.booksListDate(page);
		for(BooksVO vo:list)
		{
			System.out.println(vo.getNo()+"."
		          +vo.getTitle()+"("+vo.getPrice()+")");
		}
	}

}
