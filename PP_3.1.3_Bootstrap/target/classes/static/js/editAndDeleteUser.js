$('document').ready(
    function() {
    $('.table #editeButton').on('click',
        function(event) {

        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href,function(user,status){
            $('#idEdite').val(user.id);
            $('#nameEdite').val(user.name);
            $('#surnameEdite').val(user.surname);
            $('#usernameEdite').val(user.username);
            $('#passwordEdite').val(user.password);
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
            $('#usernameDelete').val(user.username);
        });

        $('#deleteModal').modal();
    })

});