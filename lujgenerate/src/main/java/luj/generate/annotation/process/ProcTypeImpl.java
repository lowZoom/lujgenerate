package luj.generate.annotation.process;

import com.squareup.javapoet.TypeName;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

final class ProcTypeImpl implements ProcType {

  ProcTypeImpl(TypeElement element, ProcessingEnvironment processingEnv) {
    _element = element;
    _elemUtil = processingEnv.getElementUtils();

    _messager = processingEnv.getMessager();
  }

  @Override
  public TypeElement toElement() {
    return _element;
  }

  @Override
  public TypeName toTypeName() {
    return TypeName.get(_element.asType());
  }

  @Override
  public String getPackageName() {
    return _elemUtil.getPackageOf(_element).getQualifiedName().toString();
  }

  @Override
  public AnnoProc.Log getLogger() {
    return new ProcTypeLog(_messager, _element);
  }

  private final TypeElement _element;
  private final Elements _elemUtil;

  private final Messager _messager;
}
