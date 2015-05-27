package com.company.ruanman.httptest;

import com.company.ruanman.httptest.test.TimeScheduleRelationTest;
import com.company.ruanman.httptest.test.TrainingCourseTest;
import com.company.ruanman.httptest.test.TraningPlanTest;
import com.company.ruanman.httptest.test.UploadFileTest;
import com.company.ruanman.httptest.test.UserinfoBusinessTest;
import com.company.ruanman.httptest.test.UserinfoTest;

public class MainTest {

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    {
      UserinfoTest.main(null);
      UserinfoBusinessTest.main(null);
      UploadFileTest.main(null);
      TraningPlanTest.main(null);
      TrainingCourseTest.main(null);
      UploadFileTest.main(null);
      UserinfoTest.main(null);
      UserinfoTest.main(null);
      TimeScheduleRelationTest.main(null);

    }
  }
}
