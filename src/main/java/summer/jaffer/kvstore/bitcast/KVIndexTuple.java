package summer.jaffer.kvstore.bitcast;

public class KVIndexTuple {
    private long tstamp;

    private int keySize;

    private long valueSize;

    private String key;

    private long start;

    private String filePos;

    public KVIndexTuple(long tstamp, int keySize, long valueSize, String key,  String filePos, long start) {
        this.tstamp = tstamp;
        this.keySize = keySize;
        this.valueSize = valueSize;
        this.key = key;
        this.start = start;
        this.filePos = filePos;
    }

    public static KVIndexTuple getKVIndexTuple(KVTuple kvTuple) {
        return new KVIndexTuple(kvTuple.getTstamp(), kvTuple.getKeySize(), kvTuple.getValueSize(), (String) kvTuple.getKey(),
                kvTuple.getFilePos(), kvTuple.getLinePos());
    }

    public byte[] getHintData() {
        return (tstamp + "," + keySize + "," + valueSize + "," + key + "," + filePos + "," + start + "\n").getBytes();
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public String getFilePos() {
        return filePos;
    }

    public void setFilePos(String filePos) {
        this.filePos = filePos;
    }
}
