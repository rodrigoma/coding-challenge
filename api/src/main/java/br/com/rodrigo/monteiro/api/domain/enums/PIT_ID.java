package br.com.rodrigo.monteiro.api.domain.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by monteiro on 9/14/16.
 */
public enum PIT_ID {

    PIT_1, PIT_2, PIT_3, PIT_4, PIT_5, PIT_6;

    static Map<Integer, PIT_ID> lookup;

    public static PIT_ID getByOrdinal(int ordinal) {
        if (lookup == null) {
            lookup = Arrays.stream(PIT_ID.values())
                    .collect(Collectors.toMap(Enum::ordinal, s -> s));
        }

        return lookup.get(ordinal);
    }

    public static PIT_ID getOpposite(PIT_ID pitId) {
        switch (pitId) {
            case PIT_1: return PIT_6;
            case PIT_2: return PIT_5;
            case PIT_3: return PIT_4;
            case PIT_4: return PIT_3;
            case PIT_5: return PIT_2;
            case PIT_6: return PIT_1;
        }
        return null;
    }
}