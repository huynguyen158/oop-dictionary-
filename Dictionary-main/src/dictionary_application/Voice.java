package dictionary_application;

import com.sun.speech.freetts.VoiceManager;
public class Voice {
    private final com.sun.speech.freetts.Voice voice;

    /** Khởi tạo Voice với giọng đọc kevin16. */
    public Voice() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        this.voice = VoiceManager.getInstance().getVoice("kevin16");
        this.voice.allocate();
    }

    /** Hàm nói. */
    public void say(String st) {
        this.voice.speak(st);
        this.voice.deallocate();
    }
}
