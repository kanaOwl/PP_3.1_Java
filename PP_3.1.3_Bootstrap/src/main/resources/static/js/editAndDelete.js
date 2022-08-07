$('document').ready(
    function() {
    $('.table #editeButton').on('click',
        function(event) {

        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href,function(user,status){
            $('#idEdit').val(user.id);
            $('#nameEdit').val(user.name);
            $('#surnameEdit').val(user.surname);
            $('#ageEdit').val(user.age);
            $('#usernameEdit').val(user.username);
            $('#passwordEdit').val(user.password);
        });

        $('#editModal').modal();
    })
});

$('document').ready(
    function() {

    $('.table #deleteButton').on('click',
        function(event) {

        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href,function(user,status){
            $('#idDelete').val(user.id);
            $('#nameDelete').val(user.name);
            $('#surnameDelete').val(user.surname);
            $('#ageDelete').val(user.age);
            $('#usernameDelete').val(user.username);
        });

        $('#deleteModal').modal();
    })

});