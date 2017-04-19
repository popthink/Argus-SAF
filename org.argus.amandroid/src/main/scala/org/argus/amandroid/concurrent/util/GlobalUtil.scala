/*
 * Copyright (c) 2017. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.amandroid.concurrent.util

import org.argus.amandroid.core.AndroidGlobalConfig
import org.argus.amandroid.core.util.AndroidLibraryAPISummary
import org.argus.jawa.core.util.FileUtil
import org.argus.jawa.core.{Constants, Global}
import org.argus.jawa.core.util._

object GlobalUtil {
  def buildGlobal(global: Global, outApkUri: FileResourceUri, srcs: ISet[String]): Unit = {
    global.setJavaLib(AndroidGlobalConfig.settings.lib_files)
    srcs foreach {
      src =>
        val fileUri = FileUtil.appendFileName(outApkUri, src)
        if(FileUtil.toFile(fileUri).exists()) {
          //store the app's pilar code in AmandroidCodeSource which is organized class by class.
          global.load(fileUri, Constants.JAWA_FILE_EXT, AndroidLibraryAPISummary)
        }
    }
  }
}
