package com.example.scalesisocrates;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class TriangularWave {

    private static final int sampleRate = 44100;
    private static final double amplitude = 2.0 / Math.PI;
    private final AudioTrack at;
    private Thread m_PlayThread = null;

    public TriangularWave() {
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        at = new AudioTrack(
                new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build(),
                new AudioFormat.Builder()
                        .setSampleRate(sampleRate)
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO).build(),
                15 * sampleRate * 2,
                AudioTrack.MODE_STATIC,
                AudioManager.AUDIO_SESSION_ID_GENERATE);
        /*} else {
            //support for Android KitKat
            at = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT,
                    15 * sampleRate * 2, AudioTrack.MODE_STATIC);
        }*/
    }

    public void setVolume(float volume) {
        at.setVolume(volume);
    }

    public synchronized short[] getSound(double tone) {
        final double twoPiF = 2.0 * Math.PI * Math.round(tone);

        short[] buffer = new short[sampleRate];

        for (int sample = 0; sample < buffer.length; sample++) {
            double time = ((double) sample) / sampleRate;
            buffer[sample] = (short) (amplitude * Math.asin(Math.sin(twoPiF * time)) * Short.MAX_VALUE);
        }

        return buffer;
    }

    public synchronized void stop() {
        if (at.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
            at.stop();
        }
        if (m_PlayThread != null) {
            try {
                m_PlayThread.interrupt();
                m_PlayThread.join();
                m_PlayThread = null;
            } catch (Exception ignored) {
            }
        }
    }

    public synchronized void play(double tone) {
        m_PlayThread = new Thread() {
            public void run() {
                try {
                    short[] buffer = getSound(tone);
                    at.setLoopPoints(0,sampleRate,-1);
                    at.write(buffer, 0, buffer.length);
                    at.play();
                } catch (Exception | OutOfMemoryError ignored) {
                }

            }
        };
        m_PlayThread.start();
    }

    private synchronized void arrayCopy(short[] buffer, short[] bais, int offset){
        System.arraycopy(buffer, 0, bais, offset, buffer.length);
    }

    public synchronized void playDiapaswn(double b, String nhpa, String pabou, String bouga, String gadi, String dike, String kezw,
                                          String klimaka) {
        m_PlayThread = new Thread() {
            public void run() {
                try {
                    at.setLoopPoints(0,sampleRate*15,0);

                    short[] bais = new short[sampleRate*15];

                    short[] buffer = getSound(b);
                    arrayCopy(buffer,bais,0);

                    int i = Integer.parseInt(nhpa);
                    double d = Math.pow(2.0, (double) i / Integer.parseInt(klimaka)) * b;
                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate);

                    i += Integer.parseInt(pabou);
                    d = Math.pow(2.0, (double) i / Integer.parseInt(klimaka)) * b;
                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate*2);

                    i += Integer.parseInt(bouga);
                    d = Math.pow(2.0, (double) i / Integer.parseInt(klimaka)) * b;
                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate*3);

                    i += Integer.parseInt(gadi);
                    d = Math.pow(2.0, (double) i / Integer.parseInt(klimaka)) * b;
                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate*4);

                    i += Integer.parseInt(dike);
                    d = Math.pow(2.0, (double) i / Integer.parseInt(klimaka)) * b;
                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate*5);

                    i += Integer.parseInt(kezw);
                    d = Math.pow(2.0, (double) i / Integer.parseInt(klimaka)) * b;
                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate*6);

                    buffer = getSound(2 * b);
                    arrayCopy(buffer,bais,sampleRate*7);

                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate*8);

                    i -= Integer.parseInt(kezw);
                    d = Math.pow(2.0, (double) i / Integer.parseInt(klimaka)) * b;
                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate*9);

                    i -= Integer.parseInt(dike);
                    d = Math.pow(2.0, (double) i / Integer.parseInt(klimaka)) * b;
                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate*10);

                    i -= Integer.parseInt(gadi);
                    d = Math.pow(2.0, (double) i / Integer.parseInt(klimaka)) * b;
                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate*11);

                    i -= Integer.parseInt(bouga);
                    d = Math.pow(2.0, (double) i / Integer.parseInt(klimaka)) * b;
                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate*12);

                    i -= Integer.parseInt(pabou);
                    d = Math.pow(2.0, (double) i / Integer.parseInt(klimaka)) * b;
                    buffer = getSound(d);
                    arrayCopy(buffer,bais,sampleRate*13);

                    buffer = getSound(b);
                    arrayCopy(buffer,bais,sampleRate*14);

                    at.write(bais,0,bais.length);
                    at.play();

                } catch (Exception | OutOfMemoryError ignored) {
                }
            }
        };
        m_PlayThread.start();
    }

}
