package br.com.rodrigo.monteiro.api.repository;

import br.com.rodrigo.monteiro.api.domain.KalahBoard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.util.Assert.*;

/**
 * Created by monteiro on 9/15/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class KalahRepositoryTest {

    @Autowired
    private KalahRepository kalahRepository;

    @Test
    public void testSave_noError() {
        KalahBoard saved = this.kalahRepository.save(new KalahBoard().setId(UUID.randomUUID().toString()));
        notNull(saved.getId());
    }

    @Test
    public void testFindById_notFound() {
        KalahBoard kalahBoard = this.kalahRepository.findById("1234-1234-1233");
        isNull(kalahBoard);
    }

    @Test
    public void testFindById_found() {
        KalahBoard saved = this.kalahRepository.save(new KalahBoard().setId(UUID.randomUUID().toString()));
        KalahBoard kalahBoard = this.kalahRepository.findById(saved.getId());
        notNull(kalahBoard);
        isTrue(kalahBoard.getId().equals(saved.getId()));
    }
}