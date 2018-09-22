package luj.generate.spring;

import com.google.common.collect.ImmutableSet;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

class DependencyCollector {

  public Set<Class<?>> collect(Class<?> beanType) {
    Set<Class<?>> result = new HashSet<>();
    collectImpl(beanType, result);
    return result;
  }

  private void collectImpl(Class<?> type, Set<Class<?>> output) {
    Set<? extends Class<?>> depSet = Arrays.stream(type.getDeclaredFields())
        .map(Field::getType)
        .filter(this::isSpringBean)
        .filter(c -> !output.contains(c))
        .collect(Collectors.toSet());

    output.addAll(depSet);

    for (Class<?> dep : depSet) {
      collectImpl(dep, output);
    }
  }

  private boolean isSpringBean(AnnotatedElement type) {
    return Arrays.stream(type.getAnnotations()).anyMatch(this::isComponent);
  }

  private boolean isComponent(Annotation anno) {
    return ImmutableSet.<Annotation>builder()
        .add(anno)
        .addAll(ImmutableSet.copyOf(anno.annotationType().getAnnotations()))
        .build().stream()
        .map(Annotation::annotationType)
        .anyMatch(Predicate.isEqual(Component.class));
  }
}
