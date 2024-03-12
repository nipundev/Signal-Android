package org.thoughtcrime.securesms.logsubmit;

import android.content.Context;

import androidx.annotation.NonNull;
import io.github.pixee.security.SystemCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogSectionLogcat implements LogSection {

  @Override
  public @NonNull String getTitle() {
    return "LOGCAT";
  }

  @Override
  public @NonNull CharSequence getContent(@NonNull Context context) {
    try {
      final Process        process        = SystemCommand.runCommand(Runtime.getRuntime(), "logcat -d");
      final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      final StringBuilder  log            = new StringBuilder();
      final String         separator      = System.getProperty("line.separator");

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        log.append(line);
        log.append(separator);
      }
      return log.toString();
    } catch (IOException ioe) {
      return "Failed to retrieve.";
    }
  }
}
