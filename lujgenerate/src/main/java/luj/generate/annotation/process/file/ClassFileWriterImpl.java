package luj.generate.annotation.process.file;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.annotation.processing.Filer;

final class ClassFileWriterImpl implements ClassFileWriter {

  ClassFileWriterImpl(Filer filer) {
    _filer = filer;
  }

  @Override
  public void write(String packageName, TypeSpec classSpec) throws IOException {
    JavaFile.builder(packageName, classSpec).build().writeTo(_filer);
  }

  private final Filer _filer;
}
