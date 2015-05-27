package com.company.ruanman.httptest.test;

import java.io.ByteArrayInputStream;

import net.sf.json.JSONObject;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.company.news.Constants;
import com.company.news.jsonform.TrainingCourseJsonform;
import com.company.news.rest.RestConstants;
import com.company.ruanman.httptest.AbstractHttpTest;
import com.company.ruanman.httptest.HttpUtils;
import com.company.ruanman.httptest.JSONUtils;
import com.company.ruanman.httptest.TestConstants;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class TrainingCourseTest  extends AbstractHttpTest {
  public String data_id=null;
  public UserinfoTest user=new UserinfoTest();
  /**
   * run this testcase as a suite from the command line
   * @param args - ignored
   * @throws Exception 
   */
  public static void main(String args[]) throws Exception {
    TrainingCourseTest o=new TrainingCourseTest();
    o.testTrainingCourseSaveSuccess();
    o.testTrainingCourseGetSuccess();
    o.testTrainingCourseMySuccess();
    o.testTrainingCoursequeryPublishSuccess();
  }
  

  /**
   * supply this test cases as part of a suite
   * @return
   */
  public static Test suite() {
      return new TestSuite( TrainingCourseTest.class );
  }
  public void testTrainingCourseMySuccess() throws Exception {
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/trainingCourse/my.json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  public void testTrainingCoursequeryPublishSuccess() throws Exception {
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/trainingCourse/queryPublish.json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  public void testTrainingCourseGetSuccess() throws Exception {
    if(data_id==null)data_id="1";
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/trainingCourse/"+data_id+".json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  /**
   * Verifies that submitting the login form without entering a name results in a page
   * containing the text "Login failed"
   **/
  public void testTrainingCourseSaveSuccess() throws Exception {
      WebConversation     conversation = new WebConversation();
      //GetMethodWebRequest
      TrainingCourseJsonform form =new TrainingCourseJsonform();
      form.setExercise_mode(1);
      form.setDifficulty_degree(1);
      form.setContext("基础训练");
      form.setMax_students(1);
      form.setPlace("东郊记忆");
      form.setPrice(100d);
      form.setTime_length(50);
      form.setTitle("基础训练");
     // form.setId(1l);
      String json=JSONUtils.getJsonString(form);
      HttpUtils.printjson(json);
      ByteArrayInputStream input=new ByteArrayInputStream(json.getBytes(Constants.Charset));
      PostMethodWebRequest  request = new PostMethodWebRequest( TestConstants.host+"rest/trainingCourse/save.json"+user.addParameter_JSESSIONID(),input,TestConstants.contentType );
      request.setParameter(RestConstants.Return_JSESSIONID, user.getLoginSessionid());
      WebResponse response = tryGetResponse(conversation, request );
       
        HttpUtils.println(conversation, request, response);
        assertTrue( "保存-成功", response.getText().indexOf( "success" ) != -1 );
      if (response.getContentType().equals("application/json")) {
        JSONObject jsonObject = JSONObject.fromObject(response.getText());
        this.data_id=(String)jsonObject.getString("data_id");
       }
//
//      assertTrue( "Login not rejected", response.getText().indexOf( "Login failed" ) != -1 );
  }
}