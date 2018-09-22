package luj.generate.spring;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

class ContextInitializer {

  public void init(ContextCache cache, Supplier<Class<?>[]> beans) {
    if (isInit(cache)) {
      return;
    }

    List<Class<?>> beanList = ImmutableList.copyOf(beans.get());
    GenericApplicationContext ctx = createSpringContext(beanList);
    cache.setSpringCtx(ctx);
  }

  private boolean isInit(ContextCache cache) {
    return cache.getSpringCtx() != null;
  }

  private GenericApplicationContext createSpringContext(Collection<Class<?>> beanList) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

    beanList.stream()
        .map(this::withDep)
        .flatMap(Collection::stream)
        .distinct()
        .map(this::toBeanDef)
        .forEach(def -> register(ctx, def));

    ctx.refresh();
    return ctx;
  }

  private Set<Class<?>> withDep(Class<?> type) {
    return ImmutableSet.<Class<?>>builder()
        .add(type)
        .addAll(_dependencyCollector.collect(type))
        .build();
  }

  private GenericBeanDefinition toBeanDef(Class<?> beanType) {
    GenericBeanDefinition def = new GenericBeanDefinition();
    def.setBeanClass(beanType);
    def.setLazyInit(true);
    return def;
  }

  private void register(BeanDefinitionRegistry ctx, GenericBeanDefinition def) {
    ctx.registerBeanDefinition(def.getBeanClass().getCanonicalName(), def);
  }

  private final DependencyCollector _dependencyCollector = new DependencyCollector();
}
