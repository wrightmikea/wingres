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
import org.commandlinetools.wingres.installer.test.helpers.Utility;
import sun.net.idn.StringPrep;

import java.io.File;

/**
 * Tests the wingres installer
 */
public class InstallerTest extends TestCase {
  public void testFirst() {
    File tmpDir = Utility.newTempDir();
    assertFalse("precondition: target directory does not yet exist", tmpDir.exists());
    Main.main(new String[]{tmpDir.getAbsolutePath()});
    assertTrue("postcondition: target directory created", tmpDir.exists());
    assertExists(tmpDir, new String[] {"bin", "etc", "lib", "var/data", "var/reports"});
    assertExists(tmpDir, "var/data", new String[] {"wingres-snapshot-0.0.1.zip" });
    assertExists(tmpDir, "bin", new String[] { "gres.cmd" });
  }

  private void assertExists(File grandparent, String parentName, String[] childrenNames) {
    File parent = new File(grandparent, parentName);
    assertExists(parent, childrenNames);
  }
  private void assertExists(File parent, String[] childrenNames) {
    for (String childName: childrenNames) {
      assertExists(parent, childName);
    }
  }

  private void assertExists(File parent, String childName) {
    File target = new File(parent, childName);
    assertTrue("postcondition: expect to create target: " + target.getAbsolutePath(), target.exists());
  }
}
