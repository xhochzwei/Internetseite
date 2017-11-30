/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.qreator.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;

public class VertxWebFormular {
    
    public static void main(String[] args) {
     
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
     
        SessionStore store = LocalSessionStore.create(vertx);
        SessionHandler sessionHandler = SessionHandler.create(store);
        router.route().handler(sessionHandler);
        router.route("/anfrage").handler(routingContext -> {
            String typ = routingContext.request().getParam("typ");
            String name = routingContext.request().getParam("name");
            String name1 = routingContext.request().getParam("name2");
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");
            JsonObject jo = new JsonObject();
            String Passwort="yeay";
            String user="Jan";
            //Boolean angemeldet= Boolean.FALSE;
            Session session = routingContext.session();
            
            
            
          

            if (typ.equals("namenKnopf")) {
                jo.put("typ", "antwort");
                if (name1.equals(Passwort)&& name.equals(user)){
                jo.put("text", "Der Name war " + name +" und das passwort ist gültig "+ name1);
                jo.put("angemeldet",true);
                session.put("angemeldet", Boolean.TRUE);
      
                
                }else{
                jo.put("text", "Name oder Passwort ist ungültig ");    
                jo.put("angemeldet",false);
                session.put("angemeldet", Boolean.FALSE);
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
//http://localhost:8080/static/formular.html