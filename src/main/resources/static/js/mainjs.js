let map;
let markers = [];

$.ajax({
    url: "/main/hotPlaceJson",
    type: "POST",
    success: function (data) {
        const body = $("#tab1");
        body.empty();
        let tag = "";

        if (data.length > 0) {
            for (let i = 0; i < data.length; i++) {
                tag += `<div class="border-bottom mt-2">
                                    <a class="text-decoration-none text-dark" href="#" onclick="createMarker(${data[i].lat}, ${data[i].lng}, '${data[i].main_TITLE}','${i}')">
                                        <b>${data[i].main_TITLE}</b>
                                    </a>
                                    <span class="toggleButton noHeart" id="toggleButton${i}" onclick="toggleHeart(${i})"></span>
                                    <div class="mt-2">
                                        <p>주소 : ${data[i].addr1}
                                        <br>메뉴 : ${data[i].rprsntv_MENU}<br></p>
                                        <a class="text-decoration-none text-dark" href="/main/detail?idx=${i}" id="details">상세보기</a>
                                        <br>
                                    </div>
                                </div>`;
            }
        }
        body.append(tag);
        myMap(data);
    },
    error: function () {
        alert("통신 중 오류가 발생했습니다.");
    }
});

function myMap(data) {
    var mapOptions = {
        center: new google.maps.LatLng(35.19184, 129.01126),
        zoom: 11
    };

    map = new google.maps.Map(
        document.getElementById("googleMap"),
        mapOptions);

    addMarkers(data);
}

function addMarkers(data) {
    clearMarkers();

    for (let i = 0; i < data.length; i++) {
        const latitude = parseFloat(data[i].lat);
        const longitude = parseFloat(data[i].lng);
        const title = data[i].main_TITLE;
        var idx = i;

        addMarker(latitude, longitude, title, idx);
    }
}

function addMarker(latitude, longitude, title, i) {
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(latitude, longitude),
        map: map,
        title: title
    });

    marker.addListener('click', function() {
        var modalBody = $("#markerModalBody");
        modalBody.empty();

        modalBody.append(`<a class="text-decoration-none" href="/main/detail?idx=${i}">${title}</a>`);

        $("#markerModal").modal("show");
        $("#markerModal .btn-close").click(function() {
            $("#markerModal").modal("hide");
        });
    });

    markers.push(marker);
}

function createMarker(latitude, longitude, title,i) {
    clearMarkers();

    addMarker(latitude, longitude, title,i);
}

function clearMarkers() {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }

    markers = [];
}
function toggleHeart(buttonIndex) {
    var button = document.getElementById(`toggleButton${buttonIndex}`);

    // 토글 버튼의 클래스를 변경하여 스타일이 적용되도록 함
    button.classList.toggle('heart');
    button.classList.toggle('noHeart');

    // 여기서 버튼이 클릭되었을 때의 추가 작업을 수행합니다.
    console.log(`버튼 ${buttonIndex}이(가) 클릭되었습니다.`);

    // 여기에 추가로 원하는 작업을 수행할 수 있습니다.
}


document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('tab1').classList.add('active');
});

function changeTab(tabIndex) {
    document.querySelectorAll('.tab-content').forEach(function(content) {
        content.classList.remove('active');
    });
    document.getElementById('tab' + tabIndex).classList.add('active');
}

