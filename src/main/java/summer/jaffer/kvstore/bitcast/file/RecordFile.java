package summer.jaffer.kvstore.bitcast.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import summer.jaffer.kvstore.bitcast.utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 记录当前位置文件
 * 格式：filename,offset，如：1.bc,0
 */
@Service
public class RecordFile {
    private String recordFilePath;

    private String dataFile;

    private long offset;

    RecordFile(@Value("${recordFilePath}") String recordFilePath) {
        this.recordFilePath = recordFilePath;
        String[] two = readFile(recordFilePath);
        dataFile = two[0];
        offset = StringUtils.str2Long(two[1]);
    }

    public long getOffset() {
        return offset;
    }

    public void update(long nextOffset) {
        this.offset = nextOffset;
        writeFile(recordFilePath);
    }

    private String[] readFile(String path) {
        File file = new File(path);
        FileInputStream fis = null;
        FileChannel fileChannel = null;
        try {
            if (!file.exists()) {
                dataFile = "1.bc";
                offset = 0L;
                writeFile(path);
            }
            fis = new FileInputStream(file);
            fileChannel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(16);
            fileChannel.read(buffer);
            buffer.flip();
            byte[] dst = new byte[buffer.limit()];
            buffer.get(dst, 0, buffer.limit());
            buffer.clear();
            String sb = new String(dst);
            return sb.split(",");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fileChannel != null) fileChannel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void writeFile(String path) {
        File file = new File(path);
        FileChannel fileChannel = null;
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
                fos = new FileOutputStream(file);
                fileChannel = fos.getChannel();
                fileChannel.write(ByteBuffer.wrap(toString().getBytes()));
            } else {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fileChannel != null) fileChannel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return dataFile + "," + offset;
    }

}
