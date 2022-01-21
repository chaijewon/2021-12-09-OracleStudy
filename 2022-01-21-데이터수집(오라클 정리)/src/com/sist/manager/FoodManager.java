package com.sist.manager;
// 데이터 수집 : 웹크롤링 
// 사이트를 연결해서 데이터 읽기 => HTML Parser (Jsoup:정적데이터) 
// 동적 (실시간) => 셀레니움 
// 영화 , 뮤직 => 동영상 (유티브)
/*
 *    사이트 : 
 *            결재(장바구니) 10000개의 레시피 (스토어) 
 *            추천 (경로) : 망고플레이트 , 서울 여행(명소, 자연 , 호텔,쇼핑몰...)
 *            예매 : 영화 (네이버) , 맛집(망고플레이트)
 *                 -----  ----
 *            레시피 만드는 방법 : 냉장고(재료) => 재료 선택시 => 레시피(추천)
 *            => 화면 UI + SQL => 채팅 (챗봇)
 *            => 기능 추가 (스프링) => 실제 챗봇
 *            => 최신 기술 (스프링부트 , React,Vue)
 *                                  ----------- Rest API   
 */
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.*;
/*
 *  구분자 => id , class
 *  <span class="title " id="aaa">
 *    span.title , span#aaa
          <a href="/restaurants/vcheyGIxPJ9D"
          onclick="trackEvent('CLICK_RESTAURANT', 
          {&quot;position&quot;:0,&quot;restaurant_key&quot;:
          &quot;vcheyGIxPJ9D&quot;})">
          1.<h3> 껠끄쇼즈</h3>
          </a>
    </span>
 */
public class FoodManager {
	public static void main(String[] args) {
		FoodManager m=new FoodManager();
		m.foodWebData();
	}
    public void foodWebData()
    {
    	try
    	{
    		// 오라클에 있는 link데이터 읽기 
    		FoodDAO dao=new FoodDAO();
    		List<CategoryVO> list=dao.categoryListData();
    		for(CategoryVO vo:list)
    		{
    			System.out.println("======="+vo.getTitle()+"========");
    			//WEB연결 => 데이터를 읽어 온다 
    			Document doc=Jsoup.connect(vo.getLink()).get();
    			Elements link=doc.select("span.title a");
    			// <a> , <img> => attr() => text()
    			for(int i=0;i<link.size();i++)
    			{
    				System.out.println((i+1)+"."+link.get(i).attr("href"));
    			}
    		}
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());// 에러난 위치지정이 없다 
    	}
    }
}














