package luj.generate.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaFileObject;

class Utf8FileObj implements JavaFileObject {

  @Override
  public Kind getKind() {
    return _fileObj.getKind();
  }

  @Override
  public boolean isNameCompatible(String simpleName, Kind kind) {
    return _fileObj.isNameCompatible(simpleName, kind);
  }

  @Override
  public NestingKind getNestingKind() {
    return _fileObj.getNestingKind();
  }

  @Override
  public Modifier getAccessLevel() {
    return _fileObj.getAccessLevel();
  }

  @Override
  public URI toUri() {
    return _fileObj.toUri();
  }

  @Override
  public String getName() {
    return _fileObj.getName();
  }

  @Override
  public InputStream openInputStream() throws IOException {
    return _fileObj.openInputStream();
  }

  @Override
  public OutputStream openOutputStream() throws IOException {
    return _fileObj.openOutputStream();
  }

  @Override
  public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
    return _fileObj.openReader(ignoreEncodingErrors);
  }

  @Override
  public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
    return _fileObj.getCharContent(ignoreEncodingErrors);
  }

  @Override
  public Writer openWriter() throws IOException {
    return _fileObj.openWriter();
  }

  @Override
  public long getLastModified() {
    return _fileObj.getLastModified();
  }

  @Override
  public boolean delete() {
    return _fileObj.delete();
  }

  Utf8FileObj(JavaFileObject fileObj) {
    _fileObj = fileObj;
  }

  private final JavaFileObject _fileObj;
}
