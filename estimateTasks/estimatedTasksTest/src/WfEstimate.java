

import java.util.concurrent.TimeUnit;

public class WfEstimate {

  public static void setEstimateDays(int days) {}
  public static void setEstimateHours(int hours) {};

  public static void setEstimate(int amound, TimeUnit unit) {};
  public static void setEstimate(String what) {};


  public static void tryIt() {
    WfEstimate.setEstimate(4, TimeUnit.HOURS);
  }

}
