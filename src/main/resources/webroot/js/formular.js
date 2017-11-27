$(document).ready(function () {
    
    $("body").html('<h1>Formular-Beispiel</h1>Name: <input type="text" id="eingabeName"/><br>\n\
Passwort:<input type="text" id="pwt"/><br><input type="button" id="eingabeKnopf" value="OK" />');
    
    
    $("#eingabeKnopf").click(function () {
        $.ajax({url:"../anfrage", data:
                {
                    typ: "namenKnopf",
                    name: $("#eingabeName").val(), 
                    
                    //typ: "namenKnopf",
                    name2: $("#pwt").val()
                },
                success: function (data) {
                    if(data.angemeldet==false){ 
                        $("body").html("<div>Daten: " + data.text+"<div>");
                    }else if(data.angemeldet==true){
                        $("body").html("Du bist schon angemeldet");
                        
                       $("body").append('<input type="button" id="ausloggKnopf" value="OK" /input>');
                    } 
                }
            });
    });
});