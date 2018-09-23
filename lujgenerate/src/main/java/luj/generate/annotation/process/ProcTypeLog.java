package luj.generate.annotation.process;

import javax.annotation.processing.Messager;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

final class ProcTypeLog implements AnnoProc.Log {

  ProcTypeLog(Messager messager, TypeElement element) {
    _messager = messager;
    _element = element;
  }

  @Override
  public void warn(String msg) {
    _messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, msg, _element);
  }

  @Override
  public void error(String msg) {
    _messager.printMessage(Diagnostic.Kind.ERROR, msg, _element);
  }

  private final Messager _messager;

  private final TypeElement _element;
}
