package br.com.rodrigo.monteiro.api.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

/**
 * Created by monteiro on 9/15/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
public class KalahBoardControllerTest {

    @Test
    public void testStartGame() {
        //@formatter:off
        given()
            .redirects().follow(false)
            .log().all()
        .when()
            .get("/games/start")
        .then().log().all()
            .assertThat()
                .header("Location", containsString("/games/board/"))
                .statusCode(SC_MOVED_TEMPORARILY);
        //@formatter:on
    }

    @Test
    public void testMove() {
        //@formatter:off
        String kalahId =
                given()
                    .redirects().follow(false)
                    .log().all()
                .when()
                    .get("/games/start")
                .then().log().all()
                    .assertThat()
                        .header("Location", containsString("/games/board/"))
                        .statusCode(SC_MOVED_TEMPORARILY)
                        .extract().header("Location").substring(34);
        //@formatter:on

        //@formatter:off
        given()
            .redirects().follow(false)
            .log().all()
        .when()
            .get("/games/" + kalahId + "/move/PIT_1")
        .then().log().all()
            .assertThat()
                .header("Location", containsString("/games/board/"))
                .statusCode(SC_MOVED_TEMPORARILY);
        //@formatter:on
    }

    @Test
    public void testStart_Board() {
        //@formatter:off
        given()
            .redirects().follow(true)
            .log().all()
        .when()
            .get("/games/start")
        .then().log().all()
            .assertThat()
                .statusCode(SC_OK)
                .body(containsString("Current Player: PLAYER_1"));
        //@formatter:on
    }

    @Test
    public void testMove_Board() {
        //@formatter:off
        String kalahId =
                given()
                    .redirects().follow(false)
                    .log().all()
                .when()
                    .get("/games/start")
                .then().log().all()
                    .assertThat()
                        .header("Location", containsString("/games/board/"))
                        .statusCode(SC_MOVED_TEMPORARILY)
                        .extract().header("Location").substring(34);
        //@formatter:on

        //@formatter:off
        given()
            .redirects().follow(true)
            .log().all()
        .when()
            .get("/games/" + kalahId + "/move/PIT_3")
        .then().log().all()
            .assertThat()
                .statusCode(SC_OK)
                .body(containsString(kalahId))
                .body(containsString("Current Player: PLAYER_2"));
        //@formatter:on
    }
}