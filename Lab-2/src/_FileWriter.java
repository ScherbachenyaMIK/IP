import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class _FileWriter {
    private FileWriter File_;
    _FileWriter(String filename) throws IOException {
        File_ = new FileWriter(filename);
    }
    public void CloseFile() throws IOException {
        File_.close();
    }
    public void WriteString(String str) throws IOException {
        File_.write(str + '\n');
    }
}
