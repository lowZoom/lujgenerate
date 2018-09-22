package luj.generate.file;

import java.io.IOException;
import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

class Utf8Filer implements Filer {

  @Override
  public JavaFileObject createSourceFile(CharSequence name,
      Element... originatingElements) throws IOException {
    return _filer.createSourceFile(name, originatingElements);
  }

  @Override
  public JavaFileObject createClassFile(CharSequence name,
      Element... originatingElements) throws IOException {
    return _filer.createClassFile(name, originatingElements);
  }

  @Override
  public FileObject createResource(JavaFileManager.Location location,
      CharSequence pkg, CharSequence relativeName,
      Element... originatingElements) throws IOException {
    return _filer.createResource(location, pkg, relativeName, originatingElements);
  }

  @Override
  public FileObject getResource(JavaFileManager.Location location,
      CharSequence pkg, CharSequence relativeName) throws IOException {
    return _filer.getResource(location, pkg, relativeName);
  }

  Utf8Filer(Filer filer) {
    _filer = filer;
  }

  private final Filer _filer;
}
