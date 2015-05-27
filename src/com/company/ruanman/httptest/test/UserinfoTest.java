package com.company.ruanman.httptest.test;

import java.io.ByteArrayInputStream;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.sf.json.JSONObject;

import com.company.news.Constants;
import com.company.news.jsonform.TrainingPlanJsonform;
import com.company.news.jsonform.UserModifyJsonform;
import com.company.news.rest.RestConstants;
import com.company.news.rest.util.TimeUtils;
import com.company.ruanman.httptest.AbstractHttpTest;
import com.company.ruanman.httptest.HttpUtils;
import com.company.ruanman.httptest.JSONUtils;
import com.company.ruanman.httptest.TestConstants;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;


public class UserinfoTest extends AbstractHttpTest {
  
  public String sessionid=null;
  
  public String getLoginSessionid() throws Exception {
    if(this.sessionid==null){
      testLoginSuccess();
    }
    return sessionid;
  }
  /**
   * run this testcase as a suite from the command line
   * @param args - ignored
   * @throws Exception 
   */
  public static void main(String args[]) throws Exception {
      //junit.textui.TestRunner.run( suite() );
    UserinfoTest o=new UserinfoTest();
   o.testLoginFailed();
   o.testLoginSuccess();
   o.testgetUserInfoSuccess();
    o.testUserModifySuccess();
   o.testlogoutSuccess();
  }
  
  /**
   * supply this test cases as part of a suite
   * @return
   */
  public static Test suite() {
      return new TestSuite( UserinfoTest.class );
  }
  

  /**
   * Verifies that submitting the login form without entering a name results in a page
   * containing the text "Login failed"
   **/
  public void testUserModifySuccess() throws Exception {
      WebConversation     conversation = new WebConversation();
      //GetMethodWebRequest
      UserModifyJsonform form =new UserModifyJsonform();
      form.setName("飨受人生");
      form.setBirth(TimeUtils.getCurrentTimestamp());
      form.setCity("成都");
      form.setIdentity_card("1234567890123456");
      form.setContext("我就是我，不一样的烟火");
      form.setSex(0); 
      form.setReal_name("猜猜猜");
      String json=JSONUtils.getJsonString(form);
      HttpUtils.printjson(json);
      ByteArrayInputStream input=new ByteArrayInputStream(json.getBytes(Constants.Charset));
      PostMethodWebRequest  request = new PostMethodWebRequest( TestConstants.host+"rest/userinfo/modify.json"+this.addParameter_JSESSIONID(),input,TestConstants.contentType );
      request.setParameter(RestConstants.Return_JSESSIONID, getLoginSessionid());
      WebResponse response = tryGetResponse(conversation, request );
       
        HttpUtils.println(conversation, request, response);
        assertTrue( "修改-成功", response.getText().indexOf( "success" ) != -1 );
//      if (response.getContentType().equals("application/json")) {
//        String json = response.getText();
//        Map<String, String> map = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
//        System.out.println(map.get("displayName")); // Benju
//       }
//
//      assertTrue( "Login not rejected", response.getText().indexOf( "Login failed" ) != -1 );
  }

  /**
   * Verifies that submitting the login form without entering a name results in a page
   * containing the text "Login failed"
   **/
  public void testLoginFailed() throws Exception {
      WebConversation     conversation = new WebConversation();
      //GetMethodWebRequest
      WebRequest  request = new PostMethodWebRequest( TestConstants.host+"rest/userinfo/login.json?loginname=13628037995&password=12" );
        WebResponse response = tryGetResponse(conversation, request );
//      WebForm loginForm = response.getForms()[0];
//      request = loginForm.getRequest();
//      response = conversation.getResponse( request );
        
        HttpUtils.println(conversation, request, response);
        assertTrue( "登录-失败-密码错误", response.getText().indexOf( "failed" ) != -1 );
//      if (response.getContentType().equals("application/json")) {
//        String json = response.getText();
//        Map<String, String> map = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
//        System.out.println(map.get("displayName")); // Benju
//       }
//
//      assertTrue( "Login not rejected", response.getText().indexOf( "Login failed" ) != -1 );
  }
  

  /**
   * Verifies that submitting the login form without entering a name results in a page
   * containing the text "Login failed"
   **/
  public void testLoginSuccess() throws Exception {
      WebConversation     conversation = new WebConversation();
      //GetMethodWebRequest
      WebRequest  request = new PostMethodWebRequest( TestConstants.host+"rest/userinfo/login.json?loginname=13628037994&password=1" );
        WebResponse response = tryGetResponse(conversation, request );
//      WebForm loginForm = response.getForms()[0];
//      request = loginForm.getRequest();
//      response = conversation.getResponse( request );
        HttpUtils.println(conversation, request, response);
        assertTrue( "登录-成功", response.getText().indexOf( "success" ) != -1 );
        
        
      if (response.getContentType().equals("application/json")) {
        JSONObject jsonObject = JSONObject.fromObject(response.getText());
        this.sessionid=(String)jsonObject.get("JSESSIONID");
        System.out.println("JSESSIONID="+this.sessionid); // Benju
       }
//
//      assertTrue( "Login not rejected", response.getText().indexOf( "Login failed" ) != -1 );
  }
  
  /**
   * Verifies that submitting the login form without entering a name results in a page
   * containing the text "Login failed"
   **/
  public void testgetUserInfoSuccess() throws Exception {
      WebConversation     conversation = new WebConversation();
      //GetMethodWebRequest
      WebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/userinfo/getUserinfo.json" );
      request.setParameter("JSESSIONID",  this.sessionid);
        WebResponse response = tryGetResponse(conversation, request );
//      WebForm loginForm = response.getForms()[0];
//      request = loginForm.getRequest();
//      response = conversation.getResponse( request );
        HttpUtils.println(conversation, request, response);
        assertTrue( "登录-成功", response.getText().indexOf( "success" ) != -1 );
        
        
      if (response.getContentType().equals("application/json")) {
        JSONObject jsonObject = JSONObject.fromObject(response.getText());
        this.sessionid=(String)jsonObject.get("JSESSIONID");
        System.out.println("JSESSIONID="+this.sessionid); // Benju
       }
//
//      assertTrue( "Login not rejected", response.getText().indexOf( "Login failed" ) != -1 );
  }
  
  /**
   * Verifies that submitting the login form without entering a name results in a page
   * containing the text "Login failed"
   **/
  public void testlogoutSuccess() throws Exception {
      WebConversation     conversation = new WebConversation();
      //GetMethodWebRequest
      WebRequest  request = new PostMethodWebRequest( TestConstants.host+"rest/userinfo/logout.json"+this.addParameter_JSESSIONID());
        WebResponse response = tryGetResponse(conversation, request );
//      WebForm loginForm = response.getForms()[0];
//      request = loginForm.getRequest();
//      response = conversation.getResponse( request );
        HttpUtils.println(conversation, request, response);
        assertTrue( "成功", response.getText().indexOf( "success" ) != -1 );
  }
  public String addParameter_JSESSIONID() throws Exception {
    return "?JSESSIONID="+this.getLoginSessionid();
  }

  public void setSessionid(String sessionid) {
    this.sessionid = sessionid;
  }
}
