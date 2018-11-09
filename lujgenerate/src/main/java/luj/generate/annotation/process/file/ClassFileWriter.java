package luj.generate.annotation.process.file;

import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.annotation.processing.Filer;

public interface ClassFileWriter {

  interface Factory {

    static ClassFileWriter create(Filer filer) {
      return new ClassFileWriterImpl(filer);
    }
  }

  void write(String packageName, TypeSpec classSpec) throws IOException;
}
