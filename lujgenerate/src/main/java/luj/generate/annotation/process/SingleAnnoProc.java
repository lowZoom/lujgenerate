package luj.generate.annotation.process;

import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.Set;

public abstract class SingleAnnoProc extends AnnoProc {

  public interface Context {

    ProcType getProcessingType();

    AnnoProc.Log getLogger();

    void writeToFile(String packageName, TypeSpec classSpec) throws IOException;
  }

  public abstract Class<? extends Annotation> supportedAnnotationType();

  public abstract void processElement(Context ctx) throws Exception;

  @Override
  public void init(ProcessingEnvironment env) {
    super.init(env);

    _supportAnno = supportedAnnotationType();
    _supportSet = ImmutableSet.of(_supportAnno.getCanonicalName());
  }

  @Override
  public boolean process(Set<? extends TypeElement> annoSet, RoundEnvironment env) {
    for (Element elem : env.getElementsAnnotatedWith(_supportAnno)) {
      tryProcessElem(new ProcTypeImpl((TypeElement) elem, processingEnv));
    }
    return true;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return _supportSet;
  }

  private void tryProcessElem(ProcType type) {
    try {
      processElement(new ContextImpl(type, processingEnv));

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
