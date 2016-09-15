package br.com.rodrigo.monteiro.api.repository.impl;

import br.com.rodrigo.monteiro.api.domain.KalahBoard;
import br.com.rodrigo.monteiro.api.repository.KalahRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by monteiro on 9/14/16.
 */
@Repository
public class KalahRepositoryImpl implements KalahRepository {

    private static final Map<String, KalahBoard> kalahDB = new ConcurrentHashMap();

    @Override
    public KalahBoard findById(String kalahId) {
        return kalahDB.get(kalahId);
    }

    @Override
    public KalahBoard save(KalahBoard board) {
        kalahDB.put(board.getId(), board);
        return board;
    }
}