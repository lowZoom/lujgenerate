package luj.generate.annotation.processing;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

final class LogImpl implements AnnoProc.Log {

  LogImpl(ProcessingEnvironment processingEnv) {
    _messager = processingEnv.getMessager();
  }

  @Override
  public void warn(String msg) {
    _messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, msg);
  }

  @Override
  public void error(String msg) {
    _messager.printMessage(Diagnostic.Kind.ERROR, msg);
  }

  private final Messager _messager;
}
