package com.company.ruanman.httptest.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import net.sf.json.JSONObject;

import com.company.ruanman.httptest.AbstractHttpTest;
import com.company.ruanman.httptest.HttpUtils;
import com.company.ruanman.httptest.TestConstants;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class UploadFileTest extends AbstractHttpTest {
  public UserinfoTest user= new UserinfoTest();
  public  String imgurl=null;
  public  Integer fileNumber=0;
  public static void main(String args[]) throws Exception {
  //  junit.textui.TestRunner.run( new TestSuite( UploadFileTest.class ));
    UploadFileTest o=new UploadFileTest();
     o.testuploadMyheadImg();
     o.testdownSuccess();
     o.testuploadIdentityCardImg();
     o.testdownSuccess();
     o.testuploadMarathonImg();
     o.testdownSuccess();
      //new UploadFileTest().testdownMyheadSuccess();
  }
  /**
   * Verifies that submitting the login form without entering a name results in a page
   * containing the text "Login failed"
   **/
  public void testuploadMyheadImg() throws Exception {
      String url=TestConstants.host+"rest/uploadFile/uploadMyheadImg.json"+user.addParameter_JSESSIONID();
      uploadImg(url);
  }
  
  public void testuploadIdentityCardImg() throws Exception {
    String url=TestConstants.host+"rest/uploadFile/uploadIdentityCardImg.json"+user.addParameter_JSESSIONID();
    uploadImg(url);
}
  public void testuploadMarathonImg() throws Exception {
    String url=TestConstants.host+"rest/uploadFile/uploadMarathonImg.json"+user.addParameter_JSESSIONID();
    uploadImg(url);
}
  
  public void uploadImg(String url) throws Exception {
    WebConversation     conversation = new WebConversation();
    //GetMethodWebRequest
    File file=new File("H:/work_资料/myhead.jpg");
    
//    WebForm webForm=new WebForm();
  
    PostMethodWebRequest  request = new PostMethodWebRequest(url,true );
    request.selectFile("file", file);
      WebResponse response = tryGetResponse(conversation, request );
      HttpUtils.println(conversation, request, response);
      assertTrue( "成功", response.getText().indexOf( "imgurl" ) != -1 );
    if (response.getContentType().equals("application/json")) {
      JSONObject jsonObject = JSONObject.fromObject(response.getText());
      this.imgurl=(String)jsonObject.get("imgurl");
    }
}
  /**
   * Verifies that submitting the login form without entering a name results in a page
   * containing the text "Login failed"
   **/
  public void testdownSuccess() throws Exception {
      WebConversation     conversation = new WebConversation();
      //GetMethodWebRequest
      String url=TestConstants.host+"rest/uploadFile/getImgFile.json?guid=8af409254d666494014d666494110000";
      //localhost
       url=TestConstants.host+"rest/uploadFile/getImgFile.json?guid=402886fc4d617301014d6175688b0001";
      if(this.imgurl!=null){
        url=TestConstants.host+this.imgurl;
      }
      WebRequest  request = new GetMethodWebRequest(url );
    //  request.setParameter("type", "1");
    //  request.setParameter(RestConstants.Return_JSESSIONID, user.getLoginSessionid());
        WebResponse response = tryGetResponse(conversation, request );
        
       // HttpUtils.println(conversation, request, response);
        InputStream  stream=response.getInputStream();
        String disposition=response.getHeaderField("Content-Disposition");
        String filename=HttpUtils.getFileNameByResponseHeader(disposition);
        File file=new File("H:/work_资料/"+(fileNumber++)+filename);
        FileOutputStream fileout = null;   
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
          fileout = new FileOutputStream(file);
          while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
            fileout.write(buffer, 0, bytesRead);
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          if (fileout != null) fileout.close();
          if (stream != null) stream.close();
        }
        System.out.println("file down success.path="+file.getAbsolutePath());
        HttpUtils.printlnHeader(conversation, request, response);
        assertTrue( "成功", (response.getResponseCode()==200) );
  }
  


 
}
