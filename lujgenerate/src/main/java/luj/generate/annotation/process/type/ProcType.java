package luj.generate.annotation.process.type;

import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import luj.generate.annotation.process.AnnoProc;
import luj.generate.annotation.process.file.ClassFileWriter;

public interface ProcType {

  interface Factory {

    static ProcType create(TypeElement elem,
        ProcessingEnvironment processingEnv, ClassFileWriter classFileWriter) {
      return new ProcTypeImpl(elem, processingEnv, classFileWriter);
    }
  }

  interface Package {

    String getName();

    void writeToFile(TypeSpec classSpec) throws IOException;
  }

  TypeElement toElement();

  TypeName toTypeName();

  Package getPackage();

  AnnoProc.Log getLogger();

  /**
   * @see Package#getName
   */
  @Deprecated
  String getPackageName();
}
