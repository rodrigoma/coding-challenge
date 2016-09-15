package br.com.rodrigo.monteiro.api.service;

import br.com.rodrigo.monteiro.api.domain.KalahBoard;
import br.com.rodrigo.monteiro.api.domain.enums.PIT_ID;

/**
 * Created by monteiro on 9/14/16.
 */
public interface KalahGameService {

    KalahBoard start();

    KalahBoard find(String kalahId);

    KalahBoard move(String kalahId, PIT_ID chosenPitId);
}