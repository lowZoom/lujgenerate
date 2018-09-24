package luj.generate.annotation.process.file;

import java.io.IOException;
import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.JavaFileManager;

public interface ResourceFiler {

  interface File {

    void writeTo(JavaFileManager.Location location, String pkg, String relativeName,
        Element... originatingElements) throws IOException;
  }

  static ResourceFiler from(Filer filer) {
    return new ResourceFilerImpl(filer);
  }

  File createFile(String content);
}
