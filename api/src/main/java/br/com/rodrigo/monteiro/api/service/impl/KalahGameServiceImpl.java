package br.com.rodrigo.monteiro.api.service.impl;

import br.com.rodrigo.monteiro.api.domain.KalahBoard;
import br.com.rodrigo.monteiro.api.domain.enums.PIT_ID;
import br.com.rodrigo.monteiro.api.exception.KalahGameNotFoundException;
import br.com.rodrigo.monteiro.api.repository.KalahRepository;
import br.com.rodrigo.monteiro.api.service.KalahGameService;
import br.com.rodrigo.monteiro.api.service.KalahRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.rodrigo.monteiro.api.utils.Utils.createBoard;

/**
 * Created by monteiro on 9/14/16.
 */
@Service
public class KalahGameServiceImpl implements KalahGameService {

    @Autowired
    private KalahRepository kalahRepository;

    @Autowired
    private KalahRules kalahRules;

    @Override
    public KalahBoard start() {
        return kalahRepository.save(createBoard());
    }

    @Override
    public KalahBoard find(String kalahId) {
        return kalahRepository.findById(kalahId);
    }

    @Override
    public KalahBoard move(final String kalahId, final PIT_ID chosenPitId) {
        Optional<KalahBoard> kalahBoard = Optional.ofNullable(kalahRepository.findById(kalahId));
        kalahBoard.orElseThrow(KalahGameNotFoundException::new);

        kalahRules.referee(kalahBoard.get());

        kalahRules.moveStones(kalahBoard.get(), chosenPitId);

        return kalahBoard.get();
    }
}