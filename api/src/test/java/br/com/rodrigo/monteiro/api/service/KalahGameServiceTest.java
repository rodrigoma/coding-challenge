package br.com.rodrigo.monteiro.api.service;

import br.com.rodrigo.monteiro.api.domain.KalahBoard;
import br.com.rodrigo.monteiro.api.domain.enums.PLAYER_ID;
import br.com.rodrigo.monteiro.api.exception.KalahGameNotFoundException;
import br.com.rodrigo.monteiro.api.repository.KalahRepository;
import br.com.rodrigo.monteiro.api.service.impl.KalahGameServiceImpl;
import br.com.rodrigo.monteiro.api.utils.Utils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static br.com.rodrigo.monteiro.api.domain.enums.PIT_ID.PIT_1;
import static br.com.rodrigo.monteiro.api.domain.enums.PIT_ID.PIT_3;
import static br.com.rodrigo.monteiro.api.domain.enums.PIT_ID.PIT_6;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.util.Assert.*;

/**
 * Created by monteiro on 9/15/16.
 */
public class KalahGameServiceTest {

    @Mock
    private KalahRepository kalahRepository;

    @Mock
    private KalahRules kalahRules;

    @InjectMocks
    private KalahGameService kalahGameService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        kalahGameService = new KalahGameServiceImpl();
        initMocks(this);
    }

    @Test
    public void testStart_sucess() {
        KalahBoard kalahBoard = Utils.createBoard();
        when(kalahRepository.save(any(KalahBoard.class))).thenReturn(kalahBoard);

        KalahBoard kalahBoardSaved = kalahGameService.start();
        notNull(kalahBoardSaved);
        isTrue(kalahBoard.getId().equals(kalahBoardSaved.getId()));
    }

    @Test
    public void testFind_found() {
        KalahBoard kalahBoard1 = Utils.createBoard();
        KalahBoard kalahBoard2 = Utils.createBoard();
        when(kalahRepository.save(any(KalahBoard.class))).thenReturn(kalahBoard1).thenReturn(kalahBoard2);
        when(kalahRepository.findById(anyString())).thenReturn(kalahBoard1);

        KalahBoard kalahBoardSaved1 = kalahGameService.start();
        kalahGameService.start();

        KalahBoard kalahBoardFind1 = kalahGameService.find(kalahBoard1.getId());

        notNull(kalahBoardFind1);

        isTrue(kalahBoardSaved1.getId().equals(kalahBoardFind1.getId()));
    }

    @Test
    public void testFind_notFound() {
        when(kalahRepository.findById(anyString())).thenReturn(null);

        KalahBoard kalahBoardFind = kalahGameService.find("1234-1233-12333");

        isNull(kalahBoardFind);
    }

    @Test
    public void testMove_returnError() {
        expectedEx.expect(KalahGameNotFoundException.class);

        when(kalahRepository.findById(any(String.class))).thenReturn(null);

        kalahGameService.move(UUID.randomUUID().toString(), PIT_6);
    }

    @Test
    public void testMove_newCurrentPlayer_Player1() {
        KalahBoard kalahBoard = Utils.createBoard();

        when(kalahRepository.findById(anyString())).thenReturn(kalahBoard);
        when(kalahGameService.move(anyString(), PIT_1)).thenCallRealMethod();

        KalahBoard kalahBoardMove = kalahGameService.move(anyString(), PIT_1);

        notNull(kalahBoardMove);
        isTrue(kalahBoardMove.getCurrentPlayerId().equals(PLAYER_ID.PLAYER_1));
    }

    @Test
    public void testMove_newCurrentPlayer_Player2() {
        KalahBoard kalahBoard = Utils.createBoard();

        when(kalahRepository.findById(anyString())).thenReturn(kalahBoard);
        when(kalahGameService.move(anyString(), PIT_3)).thenCallRealMethod();

        KalahBoard kalahBoardMove = kalahGameService.move(anyString(), PIT_3);

        notNull(kalahBoardMove);
        isTrue(kalahBoardMove.getCurrentPlayerId().equals(PLAYER_ID.PLAYER_2));
    }
}