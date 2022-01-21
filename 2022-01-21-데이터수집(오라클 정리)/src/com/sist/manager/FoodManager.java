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
import org.jsoup.nodes.Element;
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
    	    FoodHouseVO fvo=new FoodHouseVO();
    		for(CategoryVO vo:list)
    		{
    			System.out.println("======="+vo.getTitle()+"========");
    			//WEB연결 => 데이터를 읽어 온다 
    			Document doc=Jsoup.connect(vo.getLink()).get();
    			Elements link=doc.select("span.title a");
    			// <a> , <img> => attr() => text()
    			fvo.setCno(vo.getCno());
    			for(int i=0;i<link.size();i++)
    			{
    				//System.out.println((i+1)+"."+link.get(i).attr("href"));
    			    // https://www.mangoplate.com/
    				
    				Document doc2=Jsoup.connect("https://www.mangoplate.com"
    			                     +link.get(i).attr("href")).get();
    				// figure class="restaurant-photos-item"
    				Elements poster=doc2.select("figure.restaurant-photos-item img.center-croping");
    				String img="";
    				for(int j=0;j<poster.size();j++)
    				{
    					System.out.println(poster.get(j).attr("src"));
    					img+=poster.get(j).attr("src")+"^";
    				}
    				img=img.substring(0,img.lastIndexOf("^"));
    				img=img.replace("&", "#");
    				fvo.setPoster(img);
    				/*
    				 *   <span class="title">
		                  <h1 class="restaurant_name">와려</h1>
		                    <strong class="rate-point ">
		                      <span>4.6</span>
		                    </strong>
		
		                  <p class="branch"></p>
		                </span>
    				 */
    				Element name=doc2.selectFirst("span.title h1.restaurant_name");
    				System.out.println(name.text());
    				fvo.setName(name.text());
    				Element score=doc2.selectFirst("strong.rate-point span");
    				System.out.println(score.text());
    				fvo.setScore(Double.parseDouble(score.text().trim()));
    				
    				/*
    				 *   <img src="aaa"> => attr("src")
    				 *   <td>aaa</td> ==> text()
    				 *   
    				 *   <tr class="only-desktop">
		                  <th>주소</th>
		                  <td>서울특별시 강남구 테헤란로98길 28 2F 2호<br>
		                        <span class="Restaurant__InfoAddress--Rectangle">지번</span>
		                      <span class="Restaurant__InfoAddress--Text">서울시 강남구 대치동 950-11 2F 2호</span>
		                  </td>
		                </tr>
		
		                <tr class="only-desktop">
		                  <th>전화번호</th>
		                  <td>02-508-2648</td>
		                </tr>
    				 */
    				Element address=doc2.select("tr.only-desktop td").get(0);
    				System.out.println(address.text());
    				fvo.setAddress(address.text());
    				try
    				{
    				  Element tel=doc2.select("tr.only-desktop td").get(1);
    				  System.out.println(tel.text());
    				  fvo.setTel(tel.text());
    				}catch(Exception ex)
    				{
    					System.out.println("전화번호 없음");
    					fvo.setTel("no");
    				}
    				
    				/*
    				 *  <table class="info">
    				 *  <tr>
                  <th>음식 종류</th>
                  <td>
                    <span>정통 일식 / 일반 일식</span>
                  </td>
                </tr>

                <tr>
                  <th>가격대</th>
                  <td>4만원 이상</td>
                </tr>
    				 */
    				try
    				{
    				  Element type=doc2.select("table.info tr td span").get(2);
    				  System.out.println(type.text());
    				  fvo.setType(type.text());
    				}catch(Exception ex){
    					System.out.println("등록된 음식종류가 없음");
    					fvo.setType("no");
    				}
    				
    				try
    				{
    				  Element price=doc2.select("table.info tr td").get(3);
    				  System.out.println(price.text());
    				  fvo.setPrice(price.text());
    				}catch(Exception ex){
    					System.out.println("가격대가 없습니다");
    					fvo.setPrice("no");
    				}
    				/*
    				 *   <tr>
                  <th>주차</th>
                  <td>발렛 </td>
                </tr>

                <tr>
                  <th style="vertical-align:top;">영업시간</th>
                  <td>12:00 - 22:00</td>
                </tr>

    				 */
    				try
    				{
    				  Element parking=doc2.select("table.info tr td").get(4);
    				  System.out.println(parking.text());
    				  fvo.setParking(parking.text());
    				}catch(Exception ex){
    					System.out.println("주차 없음");
    					fvo.setParking("no");
    				}
    				
    				try
    				{
    				  Element time=doc2.select("table.info tr td").get(5);
    				  System.out.println(time.text());
    				  fvo.setTime(time.text());
    				}catch(Exception ex){
    					System.out.println("영업시간 없음");
    					fvo.setTime("no");
    				}
    				
    				/*
    				 *  <td class="menu_td">
                    <ul class="Restaurant_MenuList">
                        <li class="Restaurant_MenuItem">
                          <span class="Restaurant_Menu">런치 오마카세</span>
                            <span class="Restaurant_MenuPrice">60,000원</span>
                        </li>
                        <li class="Restaurant_MenuItem">
                          <span class="Restaurant_Menu">디너 오마카세</span>
                            <span class="Restaurant_MenuPrice">120,000원</span>
                        </li>
                    </ul>
                  </td>
    				 */
    				try
    				{
    				  Element menu=doc2.selectFirst("table.info td.menu_td");
    				  System.out.println(menu.text());
    				  fvo.setMenu(menu.text());
    				}catch(Exception ex)
    				{
    					System.out.println("등록된 메뉴 없음");
    					fvo.setMenu("no");
    				}
    				// 데이터베이스에 저장 
    				dao.foodInsert(fvo);
    				System.out.println("=========================");
    			}
    		}
    		System.out.println("저장 완료!!");
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());// 에러난 위치지정이 없다 
    	}
    }
}














