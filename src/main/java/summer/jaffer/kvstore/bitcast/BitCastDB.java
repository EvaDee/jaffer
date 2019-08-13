package summer.jaffer.kvstore.bitcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import summer.jaffer.kvstore.Database;
import summer.jaffer.kvstore.bitcast.file.BitCastFiles;
import summer.jaffer.kvstore.bitcast.file.RecordFile;

@Service
public class BitCastDB implements Database<String, String> {
    private BitCastFiles bitCastFiles;

    private RecordFile recordFile;

    private HashIndex hashIndex;

    @Autowired
    public BitCastDB(BitCastFiles bitCastFiles, RecordFile recordFile, HashIndex hashIndex) {
        this.bitCastFiles = bitCastFiles;
        this.recordFile = recordFile;
        this.hashIndex = hashIndex;
    }

    @Override
    public void put(String key, String value) {
        KVTuple<String, String> kvTuple = new KVTuple<>(System.currentTimeMillis(), key.length(), value.length(), key, value);
        // write to db
        bitCastFiles.write(kvTuple);
        // update index
        hashIndex.put(kvTuple);
        // update
//        recordFile.update(recordFile.getOffset() + kvTuple.size());
    }

    @Override
    public String get(String key) {
        KVIndexTuple kvi =  hashIndex.get(key);
        return bitCastFiles.read(kvi);
    }

    @Override
    public void delete(String key) {

    }

}
