package summer.jaffer.java.proxy;

import summer.jaffer.log.LogUtils;

public class ProfessorSpeechImpl implements Speech {
    @Override
    public void giveSpeach() {
        LogUtils.sout("教授在演讲");
    }
}
