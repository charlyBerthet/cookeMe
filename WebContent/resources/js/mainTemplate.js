$(function(){


    // Set the title color
   var divTitle = $("#activityTitle");

    switch(divTitle.text().trim().toLowerCase()){
        case "administration" : {
            divTitle.css("backgroundColor", "red"); break;
        }
        case "recipes" : {
            divTitle.css("backgroundColor", "blue"); break;
        }
        case "emergency" : {
            divTitle.css("backgroundColor", "orange"); break;
        }
        case "shop" : {
            divTitle.css("backgroundColor", "green");break;
        }
    }
});