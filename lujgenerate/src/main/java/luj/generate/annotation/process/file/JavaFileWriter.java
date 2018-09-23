package luj.generate.annotation.process.file;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.springframework.stereotype.Service;

import javax.annotation.processing.Filer;
import java.io.IOException;

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
