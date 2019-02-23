package com.owen.algorithm.nomura;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class _7GroupFileSize {

    private Map<FileType, String> patterns = new LinkedHashMap<>();
    {
        patterns.put(FileType.music, "^.*(\\.mp3|\\.aac|\\.flac)$");
        patterns.put(FileType.image, "^.*(\\.jpg|\\.bmp|\\.gif)$");
        patterns.put(FileType.movie, "^.*(\\.mp4|\\.avi|\\.mkv)$");
    }

    public static void main(String[] args){
        String input = "my.song.mp3 11b\n" +
                "greatSong.flac 1000b\n" +
                "not3.txt 5b\n" +
                "video.mp4 200b\n" +
                "game.exe 100b\n" +
                "mov!e.mkv 10000b";
        _7GroupFileSize app = new _7GroupFileSize();
        System.out.println(app.groupFileSize(input));
    }

    public String groupFileSize(String input){
        String[] lines = split(input, "\\n");
        Map<FileType, Integer> group = new HashMap<>();
        for(String line : lines)
        {
            String[] items = split(line, " ");
            FileType fileType = getFileType(items[0]);

            Integer count = group.get(fileType);
            if(count == null){ count = 0; }
            count = count + getByte(items[1]);

            group.put(fileType, count);
        }
        StringBuilder result = new StringBuilder();
        for(Map.Entry entry : patterns.entrySet()){
            FileType type = (FileType)entry.getKey();
            Integer count = group.get(type);
            if(count == null){ count = 0; }
            result.append(type.name()).append(" ").append(count).append("b\n");
        }
        Integer count = group.get(FileType.other);
        if(count == null){ count = 0; }
        return result.append(FileType.other.name()).append(" ").append(count).append("b").toString();
    }

    private int getByte(String number){
        int result = 0;
        for(int i = 0; i < number.length() - 1; i++){
            result = result * 10 + (number.charAt(i) - '0');
        }
        return result;
    }

    private String[] split(String originString, String delimeter){
        return Arrays.stream(originString.split(delimeter)).collect(Collectors.toList()).toArray(new String[0]);
    }

    private FileType getFileType(String fileName){
        return patterns.entrySet().stream()
                .filter(entry -> Pattern.compile(entry.getValue()).matcher(fileName).matches())
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(FileType.other);
    }

    enum FileType{
        music, image, movie, other
    }
}
