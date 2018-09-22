package luj.generate.spring;

import org.springframework.context.support.GenericApplicationContext;

class ContextCache {

  public GenericApplicationContext getSpringCtx() {
    return _springCtx;
  }

  public void setSpringCtx(GenericApplicationContext springCtx) {
    _springCtx = springCtx;
  }

  private GenericApplicationContext _springCtx;
}
