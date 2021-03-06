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
    File result = new File("./tmp/tmp1");
    while(result.exists()) {
      result = new File(result.getAbsolutePath() + "1");
    }
    return result;
  }
}
