package luj.generate.annotation.process;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

final class LogImpl implements AnnoProc.Log {

  LogImpl(Messager messager) {
    _messager = messager;
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
