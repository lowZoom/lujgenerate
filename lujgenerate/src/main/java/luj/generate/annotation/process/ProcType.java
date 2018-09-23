package luj.generate.annotation.process;

import com.squareup.javapoet.TypeName;

import javax.lang.model.element.TypeElement;

public interface ProcType {

  TypeElement toElement();

  TypeName toTypeName();

  String getPackageName();

  AnnoProc.Log getLogger();
}
