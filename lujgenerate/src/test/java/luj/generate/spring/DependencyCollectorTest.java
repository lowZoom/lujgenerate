package luj.generate.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class DependencyCollectorTest {

  DependencyCollector _sut;

  Class<?> _beanType;

  @Before
  public void setUp() throws Exception {
    _sut = new DependencyCollector();
  }

  @Test
  public void collect_() {
    //-- Arrange --//
    _beanType = TestBean1.class;

    //-- Act --//
    Set<Class<?>> result = collect();

    //-- Assert --//
    assertThat(result).containsOnly(
        TestBean1.class,
        TestBean2.class,
        TestBean3.class,
        TestBean4.class
    );
  }

  Set<Class<?>> collect() {
    return _sut.collect(_beanType);
  }
}
