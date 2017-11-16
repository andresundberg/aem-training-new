function initMap() {
        var sthlm = {lat: 59.334591, lng: 18.063240};
        var map = new google.maps.Map(document.getElementById('googleMap'), {
          zoom: 12,
          center: sthlm
        });
        var marker = new google.maps.Marker({
          position: sthlm,
          map: map
        });
      }


