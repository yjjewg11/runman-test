package com.company.ruanman.httptest.test;

import java.io.ByteArrayInputStream;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.company.news.Constants;
import com.company.news.jsonform.UserRelationTrainingCourseJsonform;
import com.company.news.rest.RestConstants;
import com.company.ruanman.httptest.AbstractHttpTest;
import com.company.ruanman.httptest.HttpUtils;
import com.company.ruanman.httptest.JSONUtils;
import com.company.ruanman.httptest.TestConstants;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class UserRelationTrainingCourseTest  extends AbstractHttpTest {
  
  public UserinfoTest user=new UserinfoTest();
  /**
   * run this testcase as a suite from the command line
   * @param args - ignored
   * @throws Exception 
   */
  public static void main(String args[]) throws Exception {
    UserRelationTrainingCourseTest o=new UserRelationTrainingCourseTest();
    o.testUserRelationTrainingCourseSaveSuccess();
    o.testUserRelationTrainingCourseQuerySuccess();
    o.testUserRelationTrainingCourseGetSuccess();
  }
  

  /**
   * supply this test cases as part of a suite
   * @return
   */
  public static Test suite() {
      return new TestSuite( UserRelationTrainingCourseTest.class );
  }
  public void testUserRelationTrainingCourseQuerySuccess() throws Exception {
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/userRelationTrainingCourse/query.json");
    request.setParameter("JSESSIONID", user.sessionid);
    request.setParameter("course_id", "1");
    request.setParameter("time_schedule_id", "1");
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  public void testUserRelationTrainingCourseGetSuccess() throws Exception {
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/userRelationTrainingCourse/1.json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  /**
   * Verifies that submitting the login form without entering a name results in a page
   * containing the text "Login failed"
   **/
  public void testUserRelationTrainingCourseSaveSuccess() throws Exception {
      WebConversation     conversation = new WebConversation();
      //GetMethodWebRequest
      UserRelationTrainingCourseJsonform form =new UserRelationTrainingCourseJsonform();
      form.setCourse_id(1l);
      form.setTime_schedule_id(1l);
      String json=JSONUtils.getJsonString(form);
      HttpUtils.printjson(json);
      ByteArrayInputStream input=new ByteArrayInputStream(json.getBytes(Constants.Charset));
      PostMethodWebRequest  request = new PostMethodWebRequest( TestConstants.host+"rest/userRelationTrainingCourse/save.json"+user.addParameter_JSESSIONID(),input,TestConstants.contentType );
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
