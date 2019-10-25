package luj.generate.annotation.process;

import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.Set;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import luj.generate.annotation.process.file.ClassFileWriter;
import luj.generate.annotation.process.type.ProcType;

/**
 * jdk要求子类必须为public
 */
public abstract class SingleAnnoProc extends AnnoProc {

  public interface Context {

    ProcType getProcessingType();

    AnnoProc.Log getLogger();

    void writeToFile(String packageName, TypeSpec classSpec) throws IOException;

    Filer getFiler();
  }

  protected abstract Class<? extends Annotation> supportedAnnotationType();

  protected abstract void processElement(Context ctx) throws Exception;

  @Override
  public void init(ProcessingEnvironment env) {
    super.init(env);

    _supportAnno = supportedAnnotationType();
    _supportSet = ImmutableSet.of(_supportAnno.getCanonicalName());
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    ClassFileWriter fileWriter = ClassFileWriter.Factory.create(processingEnv.getFiler());
    for (Element elem : roundEnv.getElementsAnnotatedWith(_supportAnno)) {
      tryProcessElem(createProcessingType(elem, fileWriter), fileWriter);
    }
    return true;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return _supportSet;
  }

  private ProcType createProcessingType(Element elem, ClassFileWriter fileWriter) {
    return ProcType.Factory.create((TypeElement) elem, processingEnv, fileWriter);
  }

  private void tryProcessElem(ProcType type, ClassFileWriter fileWriter) {
    try {
      processElement(new ContextImpl(type, processingEnv, fileWriter));

    } catch (Exception e) {
      logException(type.getLogger(), e);
    }
  }

  private void logException(Log log, Exception ex) {
    try (StringWriter writer = new StringWriter()) {
      ex.printStackTrace(new PrintWriter(writer));
      log.error(writer.toString());

    } catch (IOException ignored) {
      // NOOP
    }
  }

  private Class<? extends Annotation> _supportAnno;

  private Set<String> _supportSet;
}
