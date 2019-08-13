package summer.jaffer.kvstore.bitcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import summer.jaffer.kvstore.bitcast.file.HintFile;
import summer.jaffer.kvstore.bitcast.utils.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class HashIndex {

    private ConcurrentHashMap hashIndex;

    private HintFile hintFile;

    @Autowired
    HashIndex(HintFile hintFile) {
        this.hintFile = hintFile;
        hashIndex = new ConcurrentHashMap<>();
        restore();
    }

    public void put(KVTuple kvTuple) {
        KVIndexTuple kvi = KVIndexTuple.getKVIndexTuple(kvTuple);
        hashIndex.put(kvTuple.getKey(), kvi);
        hintFile.write(kvi);
    }

    public KVIndexTuple get(String key) {
        return ((KVIndexTuple) hashIndex.get(key));
    }


    public void restore() {
        byte[] data = hintFile.getData();
        if (data != null && data.length != 0) {
            String[] dataStrs = hintFile.getDataStrs(data);
            for (String kviStr : dataStrs) {
                String[] kvit = kviStr.split(",");
                KVIndexTuple kvi = new KVIndexTuple(StringUtils.str2Long(kvit[0]), StringUtils.str2Int(kvit[1]),
                        StringUtils.str2Long(kvit[2]), kvit[3], kvit[4], StringUtils.str2Long(kvit[5]));
                hashIndex.put(kvit[3], kvi);
            }
        }
    }

    public void delete(String key) {

    }
}
