package luj.generate.annotation.process.type;

import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import luj.generate.annotation.process.file.ClassFileWriter;

final class PackageImpl implements ProcType.Package {

  PackageImpl(String packageName, ClassFileWriter classFileWriter) {
    _packageName = packageName;
    _classFileWriter = classFileWriter;
  }

  @Override
  public String getName() {
    return _packageName;
  }

  @Override
  public void writeToFile(TypeSpec classSpec) throws IOException {
    _classFileWriter.write(_packageName, classSpec);
  }

  private final String _packageName;

  private final ClassFileWriter _classFileWriter;
}
