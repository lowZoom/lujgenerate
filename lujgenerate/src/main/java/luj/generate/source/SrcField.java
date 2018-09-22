package luj.generate.source;

import com.squareup.javapoet.FieldSpec;
import javax.lang.model.element.ExecutableElement;

final class SrcField {

  public SrcField(ExecutableElement element, FieldSpec spec) {
    _element = element;
    _spec = spec;
  }

  public ExecutableElement getElement() {
    return _element;
  }

  public FieldSpec getSpec() {
    return _spec;
  }

  private final ExecutableElement _element;

  private final FieldSpec _spec;
}
