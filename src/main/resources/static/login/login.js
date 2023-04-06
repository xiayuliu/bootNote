$(function(){

   dianJi();
   select();

});
function dianJi(){
    $("#btn").click(function(){
        var loginName=$("#name").val();
        var password=$("#password").val();
        $.post("/loginController","loginName="+loginName+"&Password="+password,function(data){
                console.log('data:', data);
         },'json')
    })
}

function select(){
    $("#btn2").click(function(){
        //desiredToKen
        let userName=$("#name").val();
        let password=$("#password").val();
        $.post("/enrollController","userName="+userName+"&password="+password,function(data){
             console.log('data:', data);
        },'json')
    })
}