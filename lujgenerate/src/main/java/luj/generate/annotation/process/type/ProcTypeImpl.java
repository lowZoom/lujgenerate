package luj.generate.annotation.process.type;

import com.squareup.javapoet.TypeName;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import luj.generate.annotation.process.AnnoProc;
import luj.generate.annotation.process.file.ClassFileWriter;

final class ProcTypeImpl implements ProcType {

  ProcTypeImpl(TypeElement element, ProcessingEnvironment processingEnv,
      ClassFileWriter classFileWriter) {
    _element = element;

    _processingEnv = processingEnv;
    _classFileWriter = classFileWriter;
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
  public Package getPackage() {
    PackageElement packageElem = _processingEnv.getElementUtils().getPackageOf(_element);
    return new PackageImpl(packageElem.getQualifiedName().toString(), _classFileWriter);
  }

  @Override
  public AnnoProc.Log getLogger() {
    return new ProcTypeLog(_processingEnv.getMessager(), _element);
  }

  @Override
  public String getPackageName() {
    throw new UnsupportedOperationException("该方法已废弃->ProcTypeImpl.getPackageName");
  }

  private final TypeElement _element;

  private final ProcessingEnvironment _processingEnv;
  private final ClassFileWriter _classFileWriter;
}
