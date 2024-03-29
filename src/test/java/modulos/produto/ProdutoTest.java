package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured . RestAssured . *;
import static io.restassured.matcher . RestAssuredMatchers . *;
import static org.hamcrest . Matchers . *;

@DisplayName("Testes de API Rest do modulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach (){
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";
        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.loginUsuarioAdmin())
            .when()
                .post("/v2/login")
            .then()
                .extract()
                .path("data.token");
    }

    @Test
    @DisplayName("Validar o limites proibido do valor do produto")
    void testValidarLimitesProibidosValorProduto () {
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarUmProdutoComValorIgualA(0.00))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                    .body("error",  equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }

    @Test
    @DisplayName("Validar o limite minimo aceito para o valor do produto")
    void testValidarLimitesAceitosValorProduto () {
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarUmProdutoComValorIgualA(3.15))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("message",  equalTo("Produto adicionado com sucesso"))
                    .statusCode(201);
    }

    @Test
    @DisplayName("Validar o limite maximo não permitido para o valor do produto")
    void testValidarLimitesMaximoProibidoValorProduto () {
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarUmProdutoComValorIgualA(7000.01))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error",  equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }
}
