package com.jinhe.modules.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PairChildren extends Pair {
    private List<Pair> Children;
}
