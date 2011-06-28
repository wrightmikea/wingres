package org.commandlinetools.wingres.installer.test.helpers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Static test helper utility methods.
 */
public class Utility {
  public static File newTempDir() {
    Date now = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS");
    String ts = sdf.format(now);
    File result = new File("build/tmp/wingres" + ts);
    log("newTempDir " + result.getAbsolutePath());
    return result;
  }

  public static void log(String diag) {
    String gresDebug = System.getProperty("GRES_DEBUG");
    if (null != gresDebug) {
      System.err.println("log: " + diag);
    }
  }

}
