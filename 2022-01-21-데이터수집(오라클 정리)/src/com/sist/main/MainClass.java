package com.sist.main;
// 사용자 요청 (검색,목록) 
import java.util.*;
import com.sist.dao.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // 오라클 연결 
		FoodDAO dao=new FoodDAO();
		// 카테고리를 읽어서 출력 
		List<CategoryVO> list=dao.categoryListData();
		for(CategoryVO vo:list) // for-each (출력용)
		{
			System.out.println(vo.getCno()+"."
		      +vo.getTitle()+" "+vo.getLink());
		}
	}

}
