package com.company.ruanman.httptest.test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.company.news.Constants;
import com.company.news.jsonform.TimeScheduleRelationJsonform;
import com.company.news.rest.RestConstants;
import com.company.ruanman.httptest.AbstractHttpTest;
import com.company.ruanman.httptest.HttpUtils;
import com.company.ruanman.httptest.JSONUtils;
import com.company.ruanman.httptest.TestConstants;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class TimeScheduleRelationTest  extends AbstractHttpTest {
  
  public UserinfoTest user=new UserinfoTest();
  /**
   * run this testcase as a suite from the command line
   * @param args - ignored
   * @throws Exception 
   */
  public static void main(String args[]) throws Exception {
    TimeScheduleRelationTest o=new TimeScheduleRelationTest();
    o.testTimeScheduleRelationSaveSuccess();
    o.testTimeScheduleRelationQuerySuccess();
    o.testTimeScheduleRelationGetSuccess();
  }
  

  /**
   * supply this test cases as part of a suite
   * @return
   */
  public static Test suite() {
      return new TestSuite( TimeScheduleRelationTest.class );
  }
  public void testTimeScheduleRelationQuerySuccess() throws Exception {
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/timeScheduleRelation/query.json");
    request.setParameter("JSESSIONID", user.sessionid);
    request.setParameter("type", "1");
    request.setParameter("relation_id", "1");
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  public void testTimeScheduleRelationGetSuccess() throws Exception {
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/timeScheduleRelation/1.json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  /**
   * Verifies that submitting the login form without entering a name results in a page
   * containing the text "Login failed"
   **/
  public void testTimeScheduleRelationSaveSuccess() throws Exception {
      WebConversation     conversation = new WebConversation();
      //GetMethodWebRequest
      ArrayList list=new ArrayList();
      for(int i=0;i<2;i++){
        TimeScheduleRelationJsonform form =new TimeScheduleRelationJsonform();
        form.setRelation_id(1l);
        form.setStart_time("14:00:00");
        form.setEnd_time("16:00:00");
        form.setTime_period(1);
        form.setDays("1,3,5");
        form.setType(1);
        list.add(form);
      }
    
      String json=JSONUtils.getJsonString(list);
      HttpUtils.printjson(json);
      ByteArrayInputStream input=new ByteArrayInputStream(json.getBytes(Constants.Charset));
      PostMethodWebRequest  request = new PostMethodWebRequest( TestConstants.host+"rest/timeScheduleRelation/save.json"+user.addParameter_JSESSIONID(),input,TestConstants.contentType );
      request.setParameter(RestConstants.Return_JSESSIONID, user.getLoginSessionid());
      WebResponse response = tryGetResponse(conversation, request );
       
        HttpUtils.println(conversation, request, response);
        assertTrue( "保存-成功", response.getText().indexOf( "success" ) != -1 );
//      if (response.getContentType().equals("application/json")) {
//        String json = response.getText();
//        Map<String, String> map = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
//        System.out.println(map.get("displayName")); // Benju
//       }
//
//      assertTrue( "Login not rejected", response.getText().indexOf( "Login failed" ) != -1 );
  }
}
