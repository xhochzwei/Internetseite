/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.qreator.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class VertxWebFormular {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);


        
        router.route("/anfrage").handler(routingContext -> {
            String typ = routingContext.request().getParam("typ");
            String name = routingContext.request().getParam("name");
            String name1 = routingContext.request().getParam("name2");
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");
            JsonObject jo = new JsonObject();

            if (typ.equals("namenKnopf")) {
                jo.put("typ", "antwort");
                if (name1=="yeay"){
                jo.put("text", "Der Text war " + name +" und das passwort ist gültig "+ name1);
                }else{
                jo.put("text", "Der Text war " + name +"und das passwort ist ungültig ");    
                }
            }
            response.end(Json.encodePrettily(jo));
        });

        // statische html-Dateien werden über den Dateipfad static ausgeliefert
      
        router.route("/static/*").handler(StaticHandler.create().setDefaultContentEncoding("UTF-8"));

        // alle Anfragen, die mit /daten beginnen werden von diesem Handler beantwortet
        

        // router::accept akzeptiert eine Anfrage und leitet diese an den Router weiter
        server.requestHandler(router::accept).listen(8080);
    }
}
