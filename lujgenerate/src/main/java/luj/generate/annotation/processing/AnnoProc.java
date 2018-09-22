package luj.generate.annotation.processing;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;

public abstract class AnnoProc extends AbstractProcessor {

  public interface Log {

    void warn(String msg);

    void error(String msg);
  }

  public Messager getMsg() {
    return _msg;
  }

  public Filer getFiler() {
    return _filer;
  }

  public Elements getElemUtil() {
    return _elemUtil;
  }

  @SuppressWarnings("NonSynchronizedMethodOverridesSynchronizedMethod")
  @Override
  public void init(ProcessingEnvironment env) {
    super.init(env);

    // 最先初始化日志，以便后续方法可以使用
    _msg = env.getMessager();

    _filer = env.getFiler();
    _elemUtil = env.getElementUtils();
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.RELEASE_8;
  }

  private Messager _msg;
  private Filer _filer;

  private Elements _elemUtil;
}
