package dictionary_application;

import com.darkprograms.speech.synthesiser.SynthesiserV2;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.IOException;
import java.io.InputStream;

public class GoogleAPI {

    /** Truy cập API. */
    private final SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    private static final String lang = "vi";
    public InputStream is = null;

    public GoogleAPI() {
        synthesizer.setLanguage(lang);
    }

    public void speak(String text) {
        Thread thread = new Thread(() -> {
            try {
                is = synthesizer.getMP3Data(text);
                Player player = new Player(is);
                player.play();
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(false);
        thread.start();
    }
}