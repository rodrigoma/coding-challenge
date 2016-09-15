package br.com.rodrigo.monteiro.api.repository;

import br.com.rodrigo.monteiro.api.domain.KalahBoard;

/**
 * Created by monteiro on 9/14/16.
 */
public interface KalahRepository {

    KalahBoard findById(String kalahId);

    KalahBoard save(KalahBoard board);
}