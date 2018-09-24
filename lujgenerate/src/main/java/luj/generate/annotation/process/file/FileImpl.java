package luj.generate.annotation.process.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;

final class FileImpl implements ResourceFiler.File {

  FileImpl(String content, Filer filer) {
    _content = content;
    _filer = filer;
  }

  @Override
  public void writeTo(JavaFileManager.Location location, String pkg, String relativeName,
      Element... originatingElements) throws IOException {
    FileObject fileObj = _filer.createResource(location, pkg, relativeName, originatingElements);

    try (Writer writer = toWriter(fileObj.openOutputStream())) {
      writer.write(_content);
    }
  }

  private Writer toWriter(OutputStream out) {
    return new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
  }

  private final String _content;

  private final Filer _filer;
}
