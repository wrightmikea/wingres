// https://github.com/commandlinetools/wingres - test/java/org/commandlinetools/wingres/installer/TestInstaller.java
/*
 *  (c) Copyright 2011 Michael A. Wright.  All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.commandlinetools.wingres.installer;

import junit.framework.TestCase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Tests the wingres installer
 */
public class InstallerTest extends TestCase {
  public void testFirst() {
     File tmpDir = newTempDir();
    assertEquals("precondition: target directory does not yet exist", false, tmpDir.exists());
     Main.main(new String[]{tmpDir.getAbsolutePath()});
    assertEquals("postcondition: target directory created", true, tmpDir.exists());
  }
  private File newTempDir() {
    Date now = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS");
    String ts = sdf.format(now);
    File result = new File("build/tmp/wingres" + ts);
    log("newTempDir " + result.getAbsolutePath());
    return result;
  }
  private void log(String diag) {
    String gresDebug = System.getProperty("GRES_DEBUG");
    if (null != gresDebug) {
      System.err.println("log: " + diag);
    }
  }
}
