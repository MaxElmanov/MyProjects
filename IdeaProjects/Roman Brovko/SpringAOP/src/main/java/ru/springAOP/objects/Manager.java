package ru.springAOP.objects;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public interface Manager {
    Map<String, Integer> getExtensionCount(String folder);

    Set<String> getExtensionList(String folder);
}
