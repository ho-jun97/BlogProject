package com.fastcampus.biz.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DisplayType {
    TITLE("TITLE","제목"),
    MIXED("MIXED","제목 + 내용");

    private final String key;
    private final String title;
}
