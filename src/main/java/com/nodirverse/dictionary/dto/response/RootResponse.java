package com.nodirverse.dictionary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RootResponse {
    private UUID rootId;
    private String eng;
    private String uz;
}
