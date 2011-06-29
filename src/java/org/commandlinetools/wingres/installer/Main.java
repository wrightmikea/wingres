// https://github.com/commandlinetools/wingres - src/java/org/commandlinetools/wingres/installer/Main.java
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

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.Map;

/**
 * Installs wingres
 */
public class Main {
  public static void main(String[] args) {
    System.out.println("wingres installer");
    String override = null;
    if (0 < args.length) {
      override = args[0];
    }
    File target = new File(getGresHome(override));
    System.out.println("target=" + target.getAbsolutePath());
    boolean created = target.mkdirs();
    if (created) {
      createDirectoryStructure(target.getAbsolutePath());
    } else {
      System.out.println("Wingres Installer unable to create " + target.getAbsolutePath());
    }
    populateFiles(target.getAbsolutePath());
  }

  private static String getGresHome(String override) {
    String gresHome = override;
    // if not overridden, look for environment variable
    if (null == gresHome) {
      Map<String, String> env = System.getenv();
      gresHome = env.get("GRES_HOME");
    }
    // if neither overridden nor specified in environment, use default
    if (null == gresHome) {
      gresHome = "C:\\wingres";
    }
    return gresHome;
  }

  private static void createDirectoryStructure(String gresHome) {
    String[] subDirNames = {"bin", "etc", "lib", "var/data", "var/reports"};
    String diag = null;
    try {
      for (String subDirName : subDirNames) {
        diag = subDirName;
        createSubDir(gresHome, subDirName);
      }
    } catch (Exception ex) {
      System.err.println("error creating " + diag + ", caught " + ex);
    }
  }

  private static void createSubDir(String gresHome, String subDirName) throws Exception {
    File subDir = new File(gresHome, subDirName);
    boolean created = subDir.mkdirs();
    if (!created) {
      throw new Exception("unable to create " + subDir.getAbsolutePath());
    }
  }

  private static void populateFiles(String gresHome) {
    populateBinDir(gresHome);
  }

  private static void populateBinDir(String gresHome) {
    try {
      URL source = new URL("http://gres.commandlinetools.org/downloads/gres-snapshot-0.0.1.jar");
      File dest = new File(gresHome, "bin/gres.cmd");
      int timeout = 1000;
      FileUtils.copyURLToFile(source, dest, timeout, timeout);
    } catch (Exception ex) {
       System.err.println("ex=" + ex);
    }
  }
}
