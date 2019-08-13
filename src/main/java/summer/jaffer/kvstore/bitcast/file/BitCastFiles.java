package summer.jaffer.kvstore.bitcast.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import summer.jaffer.kvstore.bitcast.KVIndexTuple;
import summer.jaffer.kvstore.bitcast.KVTuple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@Service
public class BitCastFiles {

    private File file;

    private FileChannel fileChannel;

    private RandomAccessFile accessFile;

    private RecordFile recordFile;

    @Value("${fileDirectory}")
    private String currentBCPath;

    private long nextStart;

    private File readFile;

    @Value("${fileDirectory}")
    private String currentReadFile;

    BitCastFiles(@Value("${fileDirectory}") String bcPath) {
        file = new File(bcPath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            accessFile = new RandomAccessFile(file, "rw");
            fileChannel = accessFile.getChannel();
            nextStart = accessFile.length();
//            nextLine = getLineNum(file) + 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(KVTuple kvTuple) {
        kvTuple.setFilePos(currentBCPath);
        kvTuple.setLinePos(nextStart);
        nextStart = nextStart + kvTuple.size();
        try {
            fileChannel.write(ByteBuffer.wrap(kvTuple.toString().getBytes()), accessFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String read(KVIndexTuple kvi) {
        String kvTupleStr = read(kvi.getFilePos(), kvi.getStart(), kvi.getKeySize(), kvi.getValueSize());
        if (kvTupleStr!=null) {
            String kvt = kvTupleStr.split("\n")[0];
            return kvt.split(",")[5];
        }
        return null;
    }

    private String read(String filePos, long start, int keySize, long ValueSize) {
        int size = 64 + 32 + 64 + keySize + filePos.getBytes().length + 64 + 16;
        if (fileChannel != null) {
            byte[] data = new byte[size];
            try {
                MappedByteBuffer mmap = fileChannel.map(FileChannel.MapMode.READ_ONLY, start, size);
                ByteBuffer buffer = mmap.slice();
                buffer.position(0);
                buffer.get(data);
                return new String(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private long getLineNum(File file) {
        if (file.exists()) {
            LineNumberReader lnr = null;
            try {
                lnr = new LineNumberReader(new FileReader(file));
                lnr.skip(file.length());
                return lnr.getLineNumber();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (lnr != null) {
                    try {
                        lnr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return 0;
    }

    public void onClose(FileChannel fileChannel, RandomAccessFile accessFile) {
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (accessFile != null) {
            try {
                accessFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
