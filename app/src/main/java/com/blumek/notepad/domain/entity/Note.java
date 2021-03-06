package com.blumek.notepad.domain.entity;

import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class Note {
    String id;
    String title;
    String content;
    String password;
}
