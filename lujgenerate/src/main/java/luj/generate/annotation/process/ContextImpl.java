package luj.generate.annotation.process;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.ProcessingEnvironment;
import java.io.IOException;

final class ContextImpl implements SingleAnnoProc.Context {

  ContextImpl(ProcType processingType, ProcessingEnvironment processingEnv) {
    _processingType = processingType;
    _processingEnv = processingEnv;
  }

  @Override
  public ProcType getProcessingType() {
    return _processingType;
  }

  @Override
  public AnnoProc.Log getLogger() {
    return new LogImpl(_processingEnv);
  }

  @Override
  public void writeToFile(String packageName, TypeSpec classSpec) throws IOException {
    JavaFile file = JavaFile.builder(packageName, classSpec).build();
    file.writeTo(_processingEnv.getFiler());
  }

  private final ProcType _processingType;

  private final ProcessingEnvironment _processingEnv;
}
