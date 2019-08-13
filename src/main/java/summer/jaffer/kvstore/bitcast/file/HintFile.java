package summer.jaffer.kvstore.bitcast.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import summer.jaffer.kvstore.bitcast.KVIndexTuple;
import summer.jaffer.kvstore.bitcast.KVTuple;
import summer.jaffer.kvstore.bitcast.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@Service
public class HintFile {

    private KVTuple[] kvTuples;

    private RecordFile recordFile;

    private File file;

    private FileChannel fileChannel;

    private RandomAccessFile accessFile;


    HintFile(@Value("${hintFilePath}") String path) {
        file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            accessFile = new RandomAccessFile(file, "rw");
            fileChannel = accessFile.getChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void write(KVIndexTuple kvi) {
        try {
            fileChannel.write(ByteBuffer.wrap(kvi.getHintData()), accessFile.length());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public byte[] getData() {
        if (file.exists()) {

            try {
                int size = (int) fileChannel.size();
                if (size > 0) {
                    byte[] data = new byte[size];
                    MappedByteBuffer mmap = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
                    ByteBuffer buffer = mmap.slice();
                    buffer.position(0);
                    buffer.get(data);
                    return data;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String[] getDataStrs(byte[] data) {
        return new String(data).split("\n");
    }
}
