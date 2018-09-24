package luj.generate.annotation.process.file;

import javax.annotation.processing.Filer;

final class ResourceFilerImpl implements ResourceFiler {

  ResourceFilerImpl(Filer filer) {
    _filer = filer;
  }

  @Override
  public File createFile(String content) {
    return new FileImpl(content, _filer);
  }

  private final Filer _filer;
}
