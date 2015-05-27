package com.company.ruanman.httptest.test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import net.sf.json.JSONObject;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.company.news.Constants;
import com.company.news.jsonform.TimeScheduleRelationJsonform;
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

public class UserinfoBusinessTest  extends AbstractHttpTest {
  public String data_id=null;
  public UserinfoTest user=new UserinfoTest();
  /**
   * run this testcase as a suite from the command line
   * @param args - ignored
   * @throws Exception 
   */
  public static void main(String args[]) throws Exception {
    UserinfoBusinessTest o=new UserinfoBusinessTest();
    o.testTrainingcoachqueryPublishSuccess();
  }
  

  /**
   * supply this test cases as part of a suite
   * @return
   */
  public static Test suite() {
      return new TestSuite( UserinfoBusinessTest.class );
  }
  
  public void testTrainingcoachqueryPublishSuccess() throws Exception {
    GetMethodWebRequest  request = new GetMethodWebRequest( TestConstants.host+"rest/coach/query.json"+user.addParameter_JSESSIONID());
    WebConversation     conversation = new WebConversation();
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "GET-成功", response.getText().indexOf( "success" ) != -1 );
  }
 
}
