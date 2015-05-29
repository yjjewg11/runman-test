package com.company.ruanman.httptest.test;

import java.io.ByteArrayInputStream;

import net.sf.json.JSONObject;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.company.news.Constants;
import com.company.news.jsonform.TrainingPlanJsonform;
import com.company.news.rest.RestConstants;
import com.company.news.rest.util.TimeUtils;
import com.company.ruanman.httptest.AbstractHttpTest;
import com.company.ruanman.httptest.HttpUtils;
import com.company.ruanman.httptest.JSONUtils;
import com.company.ruanman.httptest.TestConstants;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class TraningPlanTest  extends AbstractHttpTest {
  
  public UserinfoTest user=new UserinfoTest();
  /**
   * run this testcase as a suite from the command line
   * @param args - ignored
   * @throws Exception 
   */
  public static void main(String args[]) throws Exception {
    TraningPlanTest o=new TraningPlanTest();
    o.testTrainingPlanSaveSuccess();
    o.testTrainingPlanGetSuccess();
    o.testTrainingPlanMySuccess();
    o.teststatusRequest();
    o.teststatuspay();
    o.teststatuscomplete();
    o.testTrainingPlanqueryPublishSuccess();
    o.testTrainingPlanquerytrainermy();
  }
  

  /**
   * supply this test cases as part of a suite
   * @return
   */
  public static Test suite() {
      return new TestSuite( TraningPlanTest.class );
  }
  
  public void testTrainingPlanMySuccess() throws Exception {
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/trainingPlan/my.json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  public void testTrainingPlanqueryPublishSuccess() throws Exception {
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/trainingPlan/query/publish.json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  public void testTrainingPlanquerytrainermy() throws Exception {
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/trainingPlan/query/trainer/my.json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  public String data_id=null;
  public void testTrainingPlanGetSuccess() throws Exception {
    if(data_id==null)data_id="1";
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/trainingPlan/"+data_id+".json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  /**
   * Verifies that submitting the login form without entering a name results in a page
   * containing the text "Login failed"
   **/
  public void testTrainingPlanSaveSuccess() throws Exception {
      WebConversation     conversation = new WebConversation();
      //GetMethodWebRequest
      TrainingPlanJsonform form =new TrainingPlanJsonform();
      form.setExercise_mode(1);
      form.setEnd_time(TimeUtils.getCurrentTimestamp());
      form.setMap_coordinates("116.307629,40.058359");
      form.setPlace("东郊记忆");
      form.setRun_times(30);
      form.setPrice(50.2);
      form.setRunKM(20);
      form.setId(0l);
      form.setStart_time(TimeUtils.getCurrentTimestamp());
      String json=JSONUtils.getJsonString(form);
      HttpUtils.printjson(json);
      ByteArrayInputStream input=new ByteArrayInputStream(json.getBytes(Constants.Charset));
      PostMethodWebRequest  request = new PostMethodWebRequest( TestConstants.host+"rest/trainingPlan/save.json"+user.addParameter_JSESSIONID(),input,TestConstants.contentType );
      request.setParameter(RestConstants.Return_JSESSIONID, user.getLoginSessionid());
      WebResponse response = tryGetResponse(conversation, request );
       
        HttpUtils.println(conversation, request, response);
        assertTrue( "登录-成功", response.getText().indexOf( "success" ) != -1 );
        if (response.getContentType().equals("application/json")) {
          JSONObject jsonObject = JSONObject.fromObject(response.getText());
          this.data_id=(String)jsonObject.getString("data_id");
         }
  }
  
  
  public void teststatusRequest() throws Exception {
    if(data_id==null)data_id="1";
    PostMethodWebRequest  request = new PostMethodWebRequest( TestConstants.host+"rest/trainingPlan/status/request/"+data_id+".json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  public void teststatuspay() throws Exception {
    if(data_id==null)data_id="1";
    PostMethodWebRequest  request = new PostMethodWebRequest( TestConstants.host+"rest/trainingPlan/status/pay/"+data_id+".json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
  public void teststatuscomplete() throws Exception {
    if(data_id==null)data_id="1";
    PostMethodWebRequest  request = new PostMethodWebRequest( TestConstants.host+"rest/trainingPlan/status/complete/"+data_id+".json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
}
