package ru.springAOP.objects;

import org.springframework.stereotype.Component;
import ru.springAOP.MyAnnotation.ShowTime;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Component
public class FileManager implements Manager{

    @Override
    @ShowTime
    public Set<String> getExtensionList(String folder){
        File dir = new File(folder);

        Set<String> extList = new TreeSet<>();

        for(String filename : dir.list()){

            File file = new File(dir.getAbsolutePath() + "//" + filename);

            int i = filename.lastIndexOf(".");
            if(file.isFile() && i != -1) {
                extList.add(filename.substring(i+1, filename.length()).toLowerCase());
            }
        }

        return extList;
    }

    @Override
    @ShowTime
    public Map<String, Integer> getExtentionCount(String folder){
        File dir = new File(folder);

        String nameFolder = folder.substring(folder.lastIndexOf("\\") + 1, folder.length());
        System.out.println("Folder name: "+nameFolder);

        Map<String, Integer> map = new HashMap<>();

        for(String extension : getExtensionList(folder)) {
            FilenameFilter filter = new CustomFileFilter(extension);
            map.put(extension, dir.listFiles(filter).length);
        }

        return map;
    }
}
