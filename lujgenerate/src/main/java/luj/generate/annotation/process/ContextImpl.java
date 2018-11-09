package luj.generate.annotation.process;

import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import luj.generate.annotation.process.file.ClassFileWriter;
import luj.generate.annotation.process.type.ProcType;

final class ContextImpl implements SingleAnnoProc.Context {

  ContextImpl(ProcType processingType, ProcessingEnvironment processingEnv,
      ClassFileWriter classFileWriter) {
    _processingType = processingType;
    _processingEnv = processingEnv;

    _classFileWriter = classFileWriter;
  }

  @Override
  public ProcType getProcessingType() {
    return _processingType;
  }

  @Override
  public AnnoProc.Log getLogger() {
    return new LogImpl(_processingEnv.getMessager());
  }

  @Override
  public void writeToFile(String packageName, TypeSpec classSpec) throws IOException {
    _classFileWriter.write(packageName, classSpec);
  }

  @Override
  public Filer getFiler() {
    return _processingEnv.getFiler();
  }

  private final ProcType _processingType;

  private final ProcessingEnvironment _processingEnv;
  private final ClassFileWriter _classFileWriter;
}
