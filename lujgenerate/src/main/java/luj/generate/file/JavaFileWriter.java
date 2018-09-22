package luj.generate.file;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.annotation.processing.Filer;
import org.springframework.stereotype.Service;

@Service
public class JavaFileWriter {

  /**
   * 将生成文件的编码统一成UTF-8
   *
   * @see JavaFile#writeTo(Filer)
   * @see <a href="https://github.com/square/javapoet/pull/577">起因</a>
   */
  public void writeTo(String packageName, TypeSpec classSpec, Filer filer) throws IOException {
    JavaFile.builder(packageName, classSpec)
        .build()
        .writeTo(filer);
  }
}
