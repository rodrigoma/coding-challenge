package br.com.rodrigo.monteiro.api.rest;

import br.com.rodrigo.monteiro.api.domain.KalahBoard;
import br.com.rodrigo.monteiro.api.domain.enums.PIT_ID;
import br.com.rodrigo.monteiro.api.service.KalahGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by monteiro on 9/14/16.
 */
@Controller
@RequestMapping("/games")
public class KalahBoardController {

    @Autowired
    private KalahGameService kalahGameService;

    @GetMapping("/start")
    public String start() {
        KalahBoard board = this.kalahGameService.start();

        return "redirect:/games/board/" + board.getId();
    }

    @GetMapping("/{kalahId}/move/{chosenPitId}")
    public String move(@PathVariable String kalahId, @PathVariable PIT_ID chosenPitId) {
        KalahBoard board = this.kalahGameService.move(kalahId, chosenPitId);

        return "redirect:/games/board/" + board.getId();
    }

    @GetMapping("/board/{kalahId}")
    public String board(@PathVariable String kalahId, Map<String, Object> model) {
        KalahBoard board = this.kalahGameService.find(kalahId);

        model.put("kalahId", board.getId());
        model.put("current", board.getCurrentPlayerId().name());
        model.put("player1", board.getPlayerNumber1());
        model.put("player2", board.getPlayerNumber2());

        return "board";
    }
}