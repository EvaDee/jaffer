package summer.jaffer.kvstore.bitcast;

import summer.jaffer.kvstore.bitcast.utils.StringUtils;

import java.util.zip.CRC32;

public class KVTuple<K, V> {
    private long crc;

    private long tstamp;

    private int keySize;

    private long valueSize;

    private String filePos;

    private long linePos;

    K key;

    V value;

    public KVTuple(long tstamp, int keySize, long valueSize, K key, V value) {
        this.crc = getCrc(tstamp, keySize, valueSize, key, value);
        this.tstamp = tstamp;
        this.keySize = keySize;
        this.valueSize = valueSize;
        this.key = key;
        this.value = value;
    }

    public long size() {
        return toString().getBytes().length;
    }

    public long getCrc() {
        return crc;
    }

    public void setCrc(long crc) {
        this.crc = crc;
    }

    public long getTstamp() {
        return tstamp;
    }

    public void setTstamp(long tstamp) {
        this.tstamp = tstamp;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    public long getValueSize() {
        return valueSize;
    }

    public void setValueSize(long valueSize) {
        this.valueSize = valueSize;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public String getFilePos() {
        return filePos;
    }

    public void setFilePos(String filePos) {
        this.filePos = filePos;
    }

    public long getLinePos() {
        return linePos;
    }

    public void setLinePos(long linePos) {
        this.linePos = linePos;
    }

    private long getCrc(long tstamp, int keySize, long valueSize, K key, V value) {
        CRC32 crc32 = new CRC32();
        String str = tstamp + " " + keySize + " " + valueSize + " " + key + " " + value;
        crc32.update(str.getBytes());
        return crc32.getValue();
    }

    public static KVTuple getInstance(String str) {
        String[] ss = str.split(",");
        return new KVTuple(StringUtils.str2Long(ss[0]), StringUtils.str2Int(ss[2]), StringUtils.str2Int(ss[3]), ss[4], ss[5]);
    }

    @Override
    public String toString() {
        return crc + "," + tstamp + "," + keySize + "," + valueSize + "," + key + "," + value + "\n";
    }

}
