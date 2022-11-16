// (1) 회원정보 수정
function update(userId, event) {
    //alert("update");
    event.preventDefault(); //폼태그 액션을 막음
    let data = $("#profileUpdate").serialize();

    console.log(data);
//ajax는 데이터를 응답함
    $.ajax({
        type: "PUT",
        url:"/api/user/" + userId + "",
        data:data,
        contentType:"application/x-www-form-urlencoded;charset=urf-8",
        dataType:"json"
    }).done(res=>{//Http Status 상태코드 200번
        console.log("성공", res);
        location.href ="/user/" + userId + "";
    }).fail(error=>{//Http Status 상태코드 200번아닐 때
        console.log("실패",error);
        if(error.data == null)
            alert(error.responseJSON.message);
        else
            alert(JSON.stringify(error.responseJSON.data));
    });
}