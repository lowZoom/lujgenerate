package luj.generate.annotation.processing;

import com.squareup.javapoet.TypeName;
import javax.lang.model.element.TypeElement;

public interface ProcType {

  TypeElement toElement();

  TypeName toTypeName();

  String getPackageName();

  AnnoProc.Log getLogger();
}
