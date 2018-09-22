package luj.generate.spring;

@Deprecated
public abstract class GenerateSpringContext {

  protected abstract Class<?>[] beans();

  public <T> T getBean(Class<T> beanType) {
    _contextInitializer.init(CACHE, this::beans);
    return CACHE.getSpringCtx().getBean(beanType);
  }

  private static final ContextCache CACHE = new ContextCache();

  private final ContextInitializer _contextInitializer = new ContextInitializer();
}
