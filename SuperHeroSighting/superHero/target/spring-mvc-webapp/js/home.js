/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('document').ready(function () {
    $("#add").submit(function(e) {
    e.preventDefault();
});
    $('#lat,#lng ').attr('readonly', true);


    $("#toHide, .toHide").hide();

    $('#button').click(function () {
        $('#toHide').show();
    });

    var deleteLinks = document.querySelectorAll('.delete');
    for (var i = 0; i < deleteLinks.length; i++) {
        deleteLinks[i].addEventListener('click', function (event) {
            event.preventDefault();
            var choice = confirm(this.getAttribute('data-confirm'));
            if (choice) {
                window.location.href = this.getAttribute('href');
            }
        });
    }
    ;

});
 
   function initMap() {
       
        var lat =document.getElementById('lat').value;
        var lng =document.getElementById('lng').value;
        var mapCenter = new google.maps.LatLng(lat, lng);
       
       var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          center: mapCenter,
        });
        // var mapCenter = new google.maps.LatLng(lat, lng);
         new google.maps.Marker({
          position: mapCenter,
          map: map
        });
        map.setCenter(mapCenter);
      }

//var maxLat = Math.atan(Math.sinh(Math.PI)) * 180 / Math.PI;
//function initMap() {
//
//    var center = new google.maps.LatLng(0, 0);
//
//    var mapOptions = {
//        zoom: 3,
//        center: center,
//        mapTypeId: google.maps.MapTypeId.ROADMAP
//    };
//
//    var map = new google.maps.Map(document.getElementById("map"), mapOptions);
//    
//    // DOM event listener for the center map form submit
//    google.maps.event.addDomListener(document.getElementById('mapCenterForm'), 'submit', function(e) {
//
//        
//        e.preventDefault();
//        
//        // Get lat and lng values from input fields
//        var lat =document.getElementById('lat').value;
//        var lng =document.getElementById('lng').value;
//
//        // Validate user input as numbers
//        lat = (!isNumber(lat) ? 0 : lat);
//        lng = (!isNumber(lng) ? 0 : lng);
//
//        // Validate user input as valid lat/lng values
//        lat = latRange(lat);
//        lng = lngRange(lng);
//
//        // Replace input values
//        document.getElementById('lat').value = lat;
//        document.getElementById('lng').value = lng;
//
//        // Create LatLng object
//       var mapCenter = new google.maps.LatLng(lat, lng);
//       // var mapCenter = new google.maps.LatLng(44.961850, -93.113619);
//        new google.maps.Marker({
//        
//            position: mapCenter,
//            title: 'Marker title',
//            map: map
//        });
////         var mapCenter1 = new google.maps.LatLng(44.961850, -93.113619);
////         new google.maps.Marker({
////        
////            position: mapCenter1,
////            title: 'Marker title1',
////            map: map
////        });
//
//        // Center map
//        map.setCenter(mapCenter);
//    });
//}
//
//function isNumber(n) {
//    return !isNaN(parseFloat(n)) && isFinite(n);
//}
//
//function latRange(n) {
//    return Math.min(Math.max(parseInt(n), -maxLat), maxLat);
//}
//
//function lngRange(n) {
//    return Math.min(Math.max(parseInt(n), -180), 180);
//}
//
//initialize();