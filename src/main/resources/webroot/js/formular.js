$(document).ready(function () {
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
                        $("body").append("<div>Daten: " + data.text+"<div>");
                    }else if(data.angemeldet==true){
                        $("body").append("<div>Daten: " + data.text+"<div>");
                       //$("body").html(type="button" id="ausloggKnopf" value="OK");
                    } 
                }
            });
    });
});