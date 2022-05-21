package com.example.morze;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.util.Log;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class MusicPlayer {

    private static final String TAG = "MusicPlayer";
    private static final String SOUND_FOLDER = "sounds"; // Имя папки с аудиодорожками
    private static final int MAX_SOUNDS = 26;            // Максимальное число одновременно воспроизводимых дорожек

    private AssetManager assets;                                  // Наши ресурсы
    private HashMap<String, Sound> sounds;                     // Лист с нашими аудиодорожками
    private HashMap<String, Integer> streamsId = new HashMap<>(); // Лист с позывной-streamId
    private SoundPool soundPool = new SoundPool.Builder()         // Наш проигрыватель
            .setMaxStreams(MAX_SOUNDS)
            .build();


    public MusicPlayer(AssetManager assets) {
        this.assets = assets;
        sounds = loadSounds();
    }

    // Заполнение ключами (именами файлов)
    private void fillSounds(){
        try {
            String[] Files = assets.list(SOUND_FOLDER); // массив имён файлов
            for (String nameFileKey: Files){
                String key = nameFileKey.split(".")[0]; // Сохраняем имя файла без разширения
                sounds.put(key, new Sound(SOUND_FOLDER + nameFileKey));  // Сохраняем сигналы по ключам - названия файла
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Функция загрузки аудиодорожек
    private HashMap<String, Sound> loadSounds() {
        Log.d(TAG, "START LOAD SOUNDS");
        HashMap<String, Sound> sounds = new HashMap<String, Sound>();

        /*String[] soundNames = {};
        try {
            soundNames = assets.list(SOUND_FOLDER);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        sounds.put("с", new Sound(SOUND_FOLDER + "/nss.mp3"));   // Сигнал - русская с
        sounds.put("ш", new Sound(SOUND_FOLDER + "/cbp.mp3")); // Шум - русская ш

        fillSounds(); // Заполняем ключами (названия файлов)

        //Добавляем в проигрыватель и присваиваем id
        for(Map.Entry<String, Sound> sound: sounds.entrySet()) {
            try {
                AssetFileDescriptor afd = assets.openFd(sound.getValue().getPath()); // Берем мелодию
                int id = soundPool.load(afd, 1);
                sounds.get(sound.getKey()).setId(id); // Добавляем id
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "ERROR LOAD SOUND " + sound.getValue().getPath());
            }
        }

        Log.d(TAG, "END_LOAD_SOUNDS");
        return sounds;
    }


//    public void play(String character, String name) {
//        if(sounds.get(character) != null) {
//            Log.d(TAG, "start id sound: " + sounds.get(character).getId().toString());
//            int streamId = soundPool.play(sounds.get(character).getId(),
//                    1.0f,
//                    1.0f,
//                    1,
//                    -1,
//                    1.0f);
//            streamsId.put(name, streamId);
//            Log.d(TAG, "stream id = " + String.valueOf(streamId));
//        } else {
//            Log.d(TAG, "NOT FOUND IN LIST SOUND WITH CHARACTER " + character.toString());
//        }
//    }


    //Остановка мелодии
    public void stop(Character character, String name) {
        if(sounds.get(character) != null) {
            Log.d(TAG, "before pause");
            soundPool.stop(streamsId.get(name));
            //streamsId.remove(name);
        } else {
            Log.d(TAG, "NOT FOUND IN LIST SOUND WITH CHARACTER " + character.toString());
        }
    }


    //Функция освобождения ресурсов
    public void release() {
        soundPool.release();
    }

}
